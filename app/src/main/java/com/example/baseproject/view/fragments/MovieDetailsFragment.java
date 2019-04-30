package com.example.baseproject.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.example.baseproject.R;
import com.example.baseproject.base.BaseFragment;
import com.example.baseproject.databinding.FragmentMovieDetailsBinding;
import com.example.baseproject.models.MovieListBean;
import com.example.baseproject.viewmodels.MovieDetailsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel> {
    private MovieDetailsViewModel mViewModel;
    private FragmentMovieDetailsBinding mBinding;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initBinding(inflater, container);
        mBinding = getViewDataBinding();
        if (getArguments() != null) {
            MovieListBean movieListBean = getArguments().getParcelable("list");
            if (getActivity() != null && movieListBean != null) {
                mBinding.description.setText(movieListBean.getDescription());
                Glide.with(getActivity())
                        .load(movieListBean.getBannerUrl()).
                        into(mBinding.image);
/////glide uses automatic caching so storage is not done instead directly we have supplied image url here
            }


        }

        return view;
    }

    @Override
    public MovieDetailsViewModel getViewModel() {
        mViewModel = new MovieDetailsViewModel();
        return mViewModel;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_movie_details;
    }

    @Override
    public int getBindingVariable() {
        return BR.movieDetailsViewModel;
    }




}
