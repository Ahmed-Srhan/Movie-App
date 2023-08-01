package com.example.movies.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.movies.R;
import com.example.movies.adapters.FavouriteAdapter;
import com.example.movies.adapters.OnaFavMovieClickListener;
import com.example.movies.adapters.OnaFavMovieLongClickListener;
import com.example.movies.databinding.ActivityFavouriteBinding;
import com.example.movies.model.Favourite;
import com.example.movies.viewmodel.FavMovieViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavouriteActivity extends AppCompatActivity implements OnaFavMovieClickListener, OnaFavMovieLongClickListener {
    private ActivityFavouriteBinding binding;
    private FavMovieViewModel viewModel;
    private List<Favourite> favouriteArrayList;
    private FavouriteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite);
        viewModel = new ViewModelProvider(this).get(FavMovieViewModel.class);
        adapter = new FavouriteAdapter(this, this);
        showFavMovies();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Favorites");

    }

    private void showFavMovies() {
        viewModel.showMovieFromFav();
        viewModel.getFavMutableLiveData().observe(this, new Observer<List<Favourite>>() {
            @Override
            public void onChanged(List<Favourite> favourites) {
                favouriteArrayList = favourites;
                showRecyclerViewFav(favouriteArrayList);
            }
        });
    }


    private void showRecyclerViewFav(List<Favourite> favourite) {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.RVMOVIES.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            binding.RVMOVIES.setLayoutManager(new GridLayoutManager(this, 3));

        }
        binding.RVMOVIES.setItemAnimator(new DefaultItemAnimator());
        adapter.setFavouriteArrayList(favourite);
        binding.RVMOVIES.setAdapter(adapter);
    }


    @Override
    public void onFavMovieClicked(Favourite favourite) {
        Intent intent = new Intent(FavouriteActivity.this, FavouriteDetailActivity.class);
        intent.putExtra("fav_detail", favourite);
        startActivity(intent);
    }

    @Override
    public void onFavMovieLongClicked(Favourite favourite) {
        showCustomDialog(favourite);
    }

    private void showCustomDialog(Favourite favourite) {

        AlertDialog builder = new AlertDialog.Builder(this).create();
        View v = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null, false);
        Button btn_delete = v.findViewById(R.id.btn_delete);
        Button btn_cancel = v.findViewById(R.id.btn_Cancel);

        builder.setView(v);
        builder.show();
        btn_delete.setOnClickListener(view -> {
            viewModel.deleteMovieFromFav(favourite);
            Toast.makeText(FavouriteActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
            showFavMovies();
            builder.dismiss();
        });
        btn_cancel.setOnClickListener(view -> builder.dismiss());


    }
}