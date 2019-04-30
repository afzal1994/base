package com.example.baseproject.view.activities;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.Toast;
import com.example.baseproject.BR;
import com.example.baseproject.R;
import com.example.baseproject.base.BaseActivity;
import com.example.baseproject.databinding.ActivityMoviesListBinding;
import com.example.baseproject.models.APIResponse;
import com.example.baseproject.models.MoviesEntity;
import com.example.baseproject.utils.AppDatabase;
import com.example.baseproject.utils.DatabaseInitializer;
import com.example.baseproject.view.adapters.ExpandableListAdapter;
import com.example.baseproject.viewmodels.MoviesListViewModel;
import java.util.ArrayList;
import java.util.List;

public class MoviesListActivity extends BaseActivity<ActivityMoviesListBinding, MoviesListViewModel> {

    private ActivityMoviesListBinding mBinding;
    private MoviesListViewModel mViewModel;
    private List<MoviesEntity> list;
    private ExpandableListAdapter expandableListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        list = new ArrayList<>();
        expandableListAdapter = new com.example.baseproject.view.adapters.ExpandableListAdapter(this, list);
        mBinding.moviesRecycler.setAdapter(expandableListAdapter);

        DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));
        if (isNetworkConnected()) {
            deletePreviousEntries(AppDatabase.getAppDatabase(this));
            mViewModel.getMoviesList();
        } else {
           list.addAll(getAllUsers(AppDatabase.getAppDatabase(this)));
        }
        mViewModel.getResponse().observe(this, new Observer<APIResponse>() {
            @Override
            public void onChanged(@Nullable APIResponse apiResponse) {
                if (apiResponse != null) {
             parseResponse(apiResponse);
                }
            }
        });
    }

    private void parseResponse(APIResponse apiResponse) {
        if (apiResponse.getStatus().equals("Success")) {
            if (apiResponse.getResponse() != null) {
                list.addAll((List<MoviesEntity>) apiResponse.getResponse());
                for (int i = 0; i < list.size(); i++) {
                    MoviesEntity moviesEntity = new MoviesEntity();
                    moviesEntity.setGenre(list.get(i).getGenre());
                    moviesEntity.setMovieList(list.get(i).getMovieList());
                    addUser(AppDatabase.getAppDatabase(MoviesListActivity.this), moviesEntity);
                }
                expandableListAdapter.notifyDataSetChanged();

            }


        } else {
            Toast.makeText(MoviesListActivity.this, getString(R.string.check_internet), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public MoviesListViewModel getViewModel() {
        mViewModel = new MoviesListViewModel();
        return mViewModel;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_movies_list;
    }

    @Override
    public int getBindingVariable() {
        return BR.moviewsListVM;
    }

    private static void addUser(final AppDatabase db, MoviesEntity user) {
        db.userDao().insertAll(user);
    }

    private static List<MoviesEntity> getAllUsers(final AppDatabase db) {
        return db.userDao().getAll();
    }
    private static void deletePreviousEntries(final AppDatabase db) {
         db.userDao().nukeTable();
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
