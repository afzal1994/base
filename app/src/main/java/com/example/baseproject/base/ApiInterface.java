package com.example.baseproject.base;

import com.example.baseproject.models.MoviesEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("movielist")
    Call<List<MoviesEntity>> getMoviesList();

}
