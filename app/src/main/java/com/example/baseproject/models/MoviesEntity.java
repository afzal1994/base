package com.example.baseproject.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.baseproject.utils.DataConverter;

import java.util.List;

@Entity(tableName = "movies")
public class MoviesEntity {

    @ColumnInfo(name = "genre")
    private String genre;

    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "movies_list")
    private List<MovieListBean> movieList;

    @PrimaryKey(autoGenerate = true)
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<MovieListBean> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieListBean> movieList) {
        this.movieList = movieList;
    }

}
