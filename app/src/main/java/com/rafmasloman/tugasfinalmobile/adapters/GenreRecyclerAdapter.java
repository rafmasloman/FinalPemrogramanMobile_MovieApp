package com.rafmasloman.tugasfinalmobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafmasloman.tugasfinalmobile.R;

import java.util.ArrayList;

public class GenreRecyclerAdapter extends RecyclerView.Adapter<GenreRecyclerAdapter.ViewHolder>
{
    private ArrayList<String> genres;
    private Context mContext;

    public GenreRecyclerAdapter(ArrayList<String> genres, Context context){
        this.genres = genres;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String genre = genres.get(position);

        holder.setGenre(genre);
    }

    @Override
    public int getItemCount() {
        return genres == null ? 0 : genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGenre;

        public ViewHolder(View itemView){
            super(itemView);

            tvGenre = itemView.findViewById(R.id.tv_genre_rv);
        }

        public void setGenre(String genre){
            tvGenre.setText(genre);
        }
    }
}
