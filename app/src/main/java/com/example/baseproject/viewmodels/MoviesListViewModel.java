package com.example.baseproject.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.view.View;
import com.example.baseproject.base.ApiClient;
import com.example.baseproject.base.ApiInterface;
import com.example.baseproject.base.BaseViewModel;
import com.example.baseproject.models.APIResponse;
import com.example.baseproject.models.MoviesEntity;
import com.example.baseproject.utils.Constants;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListViewModel extends BaseViewModel {

    private MutableLiveData<APIResponse> responseData = new MutableLiveData<>();
    public LiveData<APIResponse> getResponse() {
        return responseData;
    }

    public void getMoviesList() {
        ApiInterface apiInterface = ApiClient.getClient(Constants.BASE_URL).create(ApiInterface.class);
        Call<List<MoviesEntity>> call = apiInterface.getMoviesList();
        call.enqueue(new Callback<List<MoviesEntity>>() {
            @Override

            public void onResponse(Call<List<MoviesEntity>> call, Response<List<MoviesEntity>> response) {
                responseData.setValue(new APIResponse(response.body(), "Success"));
                showLoader.set(View.GONE);

            }

            @Override
            public void onFailure(Call<List<MoviesEntity>> call, Throwable t) {
                responseData.setValue(new APIResponse(t, "Error"));
                showLoader.set(View.GONE);

            }
        });
    }


}
