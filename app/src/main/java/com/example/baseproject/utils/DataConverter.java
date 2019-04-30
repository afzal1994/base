package com.example.baseproject.utils;

import android.arch.persistence.room.TypeConverter;

import com.example.baseproject.models.MovieListBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class DataConverter implements Serializable {
 
 @TypeConverter // note this annotation
    public String fromOptionValuesList(List<MovieListBean> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<MovieListBean>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }
 
    @TypeConverter // note this annotation
    public List<MovieListBean> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<MovieListBean>>() {
        }.getType();
        List<MovieListBean> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }
 
}