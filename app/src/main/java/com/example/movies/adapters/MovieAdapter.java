package com.example.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.databinding.MovieListBinding;
import com.example.movies.model.Result;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private OnMovieClickListener onMovieClickListener;
    private ArrayList<Result> resultArrayList;

    public MovieAdapter(OnMovieClickListener onMovieClickListener, ArrayList<Result> resultArrayList) {
        this.onMovieClickListener = onMovieClickListener;
        this.resultArrayList = resultArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_list, parent, false);


        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Result result = resultArrayList.get(position);
        holder.binding.setResult(result);

    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MovieListBinding binding;

        public MovieViewHolder(@NonNull MovieListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Result result = resultArrayList.get(getAbsoluteAdapterPosition());
            onMovieClickListener.onMovieClicked(result);

        }
    }
}
