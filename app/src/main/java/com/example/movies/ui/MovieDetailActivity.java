package com.example.movies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.movies.R;
import com.example.movies.databinding.ActivityMovieDetailBinding;
import com.example.movies.model.Favourite;
import com.example.movies.model.Result;
import com.example.movies.viewmodel.FavMovieViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MovieDetailActivity extends AppCompatActivity {
    private ActivityMovieDetailBinding binding;
    private Result result;
    private FavMovieViewModel viewModel;
    private Favourite favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        viewModel = new ViewModelProvider(this).get(FavMovieViewModel.class);
        result = getIntent().getParcelableExtra("movieDetails");
        binding.setResult(result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(result.getOriginalTitle());

        binding.favbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                favourite = new Favourite(result.getOverview(), result.getPosterPath(), result.getVoteAverage(), result.getOriginalTitle());
                viewModel.insertMovieToFav(favourite);
                Intent intent = new Intent(MovieDetailActivity.this, FavouriteActivity.class);
                startActivity(intent);
            }
        });

    }
}