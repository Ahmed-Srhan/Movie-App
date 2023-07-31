package com.example.movies.di;

import android.app.Application;

import androidx.room.Room;

import com.example.movies.database.FavouriteDao;
import com.example.movies.database.FavouriteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class FavDatabaseModule {

    @Singleton
    @Provides
    public static FavouriteDatabase provideFavMovieDatabase(Application application) {

        return Room.databaseBuilder(application, FavouriteDatabase.class, "favourite_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public static FavouriteDao provideFavMovieDao(FavouriteDatabase favouriteDatabase) {
        return favouriteDatabase.getFavouriteDao();
    }
}
