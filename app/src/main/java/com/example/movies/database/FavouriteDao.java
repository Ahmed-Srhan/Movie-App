package com.example.movies.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.movies.model.Favourite;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface FavouriteDao {
    @Insert
    Single<Long> insertMovieToFav(Favourite favourite);

    @Query("select * from Favourite")
    Single<List<Favourite>> getMovieFromFav();
}
