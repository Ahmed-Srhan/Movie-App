package com.example.movies.di;

import android.app.Application;

import androidx.room.Room;

import com.example.movies.database.MovieDao;
import com.example.movies.database.MovieDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {

    @Singleton
    @Provides
    public static MovieDatabase provideMovieDatabase(Application application) {

        return Room.databaseBuilder(application, MovieDatabase.class, "Movie_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public static MovieDao provideMovieDao(MovieDatabase movieDatabase) {
        return movieDatabase.getMovieDao();
    }


}
