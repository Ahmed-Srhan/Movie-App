package com.example.movies.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.movies.model.Favourite;

@Database(entities = {Favourite.class}, version = 1, exportSchema = false)
public abstract class FavouriteDatabase extends RoomDatabase {
    public abstract FavouriteDao getFavouriteDao();

}
