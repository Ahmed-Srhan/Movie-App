package com.example.movies.network;

import com.example.movies.model.Info;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("movie/popular")
    Observable<Info> getMovieFromTMDB(@Query("api_key") String api_key);
}
