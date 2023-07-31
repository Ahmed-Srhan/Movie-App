package com.example.movies.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.movies.model.Result;

@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao getMovieDao();

}
