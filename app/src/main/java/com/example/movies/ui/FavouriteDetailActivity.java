package com.example.movies.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movies.R;
import com.example.movies.databinding.ActivityFavouriteDetailBinding;
import com.example.movies.model.Favourite;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavouriteDetailActivity extends AppCompatActivity {
    private ActivityFavouriteDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite_detail);
        Favourite favourite = getIntent().getParcelableExtra("fav_detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(favourite.getOriginalTitle());
        binding.setFavourite(favourite);
    }
}