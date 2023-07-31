package com.example.movies.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies.model.Info;
import com.example.movies.model.Result;
import com.example.movies.repository.MovieRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieViewModel extends ViewModel {
    private static final String TAG = "showMovieErrorInViewModel";
    private MovieRepository repository;
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Result>> mutableLiveDataOffline = new MutableLiveData<>();

    @ViewModelInject
    public MovieViewModel(MovieRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Result>> getMutableLiveDataOffline() {
        return mutableLiveDataOffline;
    }


    public MutableLiveData<List<Result>> getMutableLiveData() {
        return mutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void getMovieFromService() {
        String TAG = "";
        repository.getMovieFromService().subscribeOn(Schedulers.io()).map(new Function<Info, Object>() {
                    @Override
                    public Object apply(Info info) throws Throwable {
                        List<Result> result = info.getResults();
                        return result;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> mutableLiveData.postValue((List<Result>) results), e -> Log.d(TAG, "getMovieFromService: " + e.getMessage()));

    }

    @SuppressLint("CheckResult")
    public void insertMovie(Result result) {
        repository.insertMovie(result).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> Log.d(TAG, "insertMovie data: " + o), e -> Log.d(TAG, "insertMovie: " + e.getMessage()));


    }

    @SuppressLint("CheckResult")
    public void showAllMovie() {
        repository.showAllMovie().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> mutableLiveDataOffline.postValue(results), e -> Log.d(TAG, "showAllMovie: " + e.getMessage()));


    }


}
