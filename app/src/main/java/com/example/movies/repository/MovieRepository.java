package com.example.movies.repository;

import android.annotation.SuppressLint;

import com.example.movies.database.MovieDao;
import com.example.movies.model.Info;
import com.example.movies.model.Result;
import com.example.movies.network.MovieApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MovieRepository {
    public static final String API_KEY = "b133c1180758351bf9a6631f15a7457f";
    private static final String TAG = "errorFromApi";
    private MovieDao movieDao;
    private MovieApiService movieApiService;


    @Inject
    public MovieRepository(MovieDao movieDao, MovieApiService movieApiService) {
        this.movieDao = movieDao;
        this.movieApiService = movieApiService;
    }

    @SuppressLint("CheckResult")
    public Observable<Info> getMovieFromService() {
        return movieApiService.getMovieFromTMDB(API_KEY);
    }


    public Single<Long> insertMovie(Result result) {
        return movieDao.insertMovie(result);
    }

    public Observable<List<Result>> showAllMovie() {
        return movieDao.showAllMovie();
    }


}
