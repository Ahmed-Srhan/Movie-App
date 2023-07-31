package com.example.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.databinding.FavListBinding;
import com.example.movies.model.Favourite;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.favViewHolder> {
    private ArrayList<Favourite> favouriteArrayList;
    private OnaFavMovieClickListener favMovieClickListener;

    public FavouriteAdapter(ArrayList<Favourite> favouriteArrayList, OnaFavMovieClickListener favMovieClickListener) {
        this.favouriteArrayList = favouriteArrayList;
        this.favMovieClickListener = favMovieClickListener;
    }

    @NonNull
    @Override
    public favViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fav_list
                , parent, false);


        return new favViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull favViewHolder holder, int position) {
        Favourite favourite = favouriteArrayList.get(position);
        holder.binding.setFav(favourite);

    }

    @Override
    public int getItemCount() {
        return favouriteArrayList.size();
    }

    class favViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        FavListBinding binding;

        public favViewHolder(@NonNull FavListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Favourite favourite = favouriteArrayList.get(getAbsoluteAdapterPosition());
            favMovieClickListener.onFavMovieClicked(favourite);
        }
    }
}
