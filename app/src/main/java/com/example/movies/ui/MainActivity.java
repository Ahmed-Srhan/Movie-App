package com.example.movies.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.movies.R;
import com.example.movies.adapters.MovieAdapter;
import com.example.movies.adapters.OnMovieClickListener;
import com.example.movies.databinding.ActivityMainBinding;
import com.example.movies.model.Result;
import com.example.movies.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements OnMovieClickListener {
    private ActivityMainBinding binding;
    private MovieViewModel viewModel;
    private ArrayList<Result> resultArrayList;
    private ArrayList<Result> offlineArrayList;
    private MovieAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            showPopularMovies();
        } else {
            showOfflineMovies();
        }

    }

    private void showOfflineMovies() {
        viewModel.showAllMovie();
        viewModel.getMutableLiveDataOffline().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                offlineArrayList = (ArrayList<Result>) results;
                showRecyclerViewOffline(offlineArrayList);

            }
        });
    }

    private void showRecyclerViewOffline(ArrayList<Result> offlineList) {
        adapter = new MovieAdapter(this, offlineList);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.RVMOVIES.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            binding.RVMOVIES.setLayoutManager(new GridLayoutManager(this, 3));

        }
        binding.RVMOVIES.setItemAnimator(new DefaultItemAnimator());
        binding.RVMOVIES.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void showPopularMovies() {
        viewModel.getMovieFromService();
        viewModel.getMutableLiveData().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                resultArrayList = (ArrayList<Result>) results;

                for (Result result : resultArrayList) {
                    viewModel.insertMovie(result);
                }
                showRecyclerView(resultArrayList);
            }
        });
    }

    private void showRecyclerView(ArrayList<Result> arrayList) {
        adapter = new MovieAdapter(this, arrayList);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.RVMOVIES.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            binding.RVMOVIES.setLayoutManager(new GridLayoutManager(this, 3));

        }
        binding.RVMOVIES.setItemAnimator(new DefaultItemAnimator());
        binding.RVMOVIES.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onMovieClicked(Result result) {
        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
        intent.putExtra("movieDetails", result);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.FavoritesMenu) {

            startActivity(new Intent(MainActivity.this, FavouriteActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
}