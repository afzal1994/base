package com.example.baseproject.base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    private static ApiClient mInstance;
    private static Retrofit retrofit = null;
    private static Retrofit retrofitImage = null;

    public static Retrofit getClient(String URL){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public ApiInterface getApiInterface(){
        return  retrofit.create(ApiInterface.class);
    }

}
