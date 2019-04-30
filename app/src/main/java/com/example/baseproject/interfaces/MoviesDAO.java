package com.example.baseproject.interfaces;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.baseproject.models.MoviesEntity;

import java.util.List;

@Dao
public interface MoviesDAO {


    @Query("SELECT * FROM movies")
    List<MoviesEntity> getAll();



    @Insert
    void insertAll(MoviesEntity... users);

    @Query("DELETE FROM movies")
     void nukeTable();
}