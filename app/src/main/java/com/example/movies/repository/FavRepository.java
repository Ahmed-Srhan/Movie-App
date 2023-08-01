package com.example.movies.repository;

import com.example.movies.database.FavouriteDao;
import com.example.movies.model.Favourite;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class FavRepository {
    private FavouriteDao favouriteDao;

    @Inject
    public FavRepository(FavouriteDao favouriteDao) {
        this.favouriteDao = favouriteDao;
    }

    public Single<Long> insertMovieToFav(Favourite favourite) {
        return favouriteDao.insertMovieToFav(favourite);
    }

    public void deleteMovieFromFav(Favourite favourite) {
        favouriteDao.deleteMovieFromFav(favourite);
    }

    public Single<List<Favourite>> showMovieFromFav() {
        return favouriteDao.getMovieFromFav();
    }


}
