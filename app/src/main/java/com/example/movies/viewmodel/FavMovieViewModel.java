package com.example.movies.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies.model.Favourite;
import com.example.movies.repository.FavRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavMovieViewModel extends ViewModel {
    private static final String TAG = "FavViewModelError";
    private FavRepository repository;
    private MutableLiveData<List<Favourite>> mutableLiveDataFav = new MutableLiveData<>();

    @ViewModelInject
    public FavMovieViewModel(FavRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Favourite>> getFavMutableLiveData() {
        return mutableLiveDataFav;
    }


    @SuppressLint("CheckResult")
    public void insertMovieToFav(Favourite favourite) {
        repository.insertMovieToFav(favourite).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> Log.d(TAG, "insertMovie data: " + o), e -> Log.d(TAG, "insertMovie: " + e.getMessage()));


    }

    @SuppressLint("CheckResult")
    public void showMovieFromFav() {
        repository.showMovieFromFav().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fav -> mutableLiveDataFav.postValue(fav), e -> Log.d(TAG, "showAllMovie: " + e.getMessage()));


    }
}
