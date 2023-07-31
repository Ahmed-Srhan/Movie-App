package com.example.movies.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.movies.R;
import com.example.movies.adapters.FavouriteAdapter;
import com.example.movies.adapters.OnaFavMovieClickListener;
import com.example.movies.databinding.ActivityFavouriteBinding;
import com.example.movies.model.Favourite;
import com.example.movies.viewmodel.FavMovieViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavouriteActivity extends AppCompatActivity implements OnaFavMovieClickListener {
    private ActivityFavouriteBinding binding;
    private FavMovieViewModel viewModel;
    private ArrayList<Favourite> favouriteArrayList;
    private FavouriteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite);
        viewModel = new ViewModelProvider(this).get(FavMovieViewModel.class);
        showFavMovies();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Favorites");

    }

    private void showFavMovies() {
        viewModel.showMovieFromFav();
        viewModel.getFavMutableLiveData().observe(this, new Observer<List<Favourite>>() {
            @Override
            public void onChanged(List<Favourite> favourites) {
                favouriteArrayList = (ArrayList<Favourite>) favourites;
                showRecyclerViewFav(favouriteArrayList);
            }
        });
    }


    private void showRecyclerViewFav(ArrayList<Favourite> favourite) {
        adapter = new FavouriteAdapter(favourite, this
        );

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
    public void onFavMovieClicked(Favourite favourite) {
        Intent intent = new Intent(FavouriteActivity.this, FavouriteDetailActivity.class);
        intent.putExtra("fav_detail", favourite);
        startActivity(intent);
    }
}