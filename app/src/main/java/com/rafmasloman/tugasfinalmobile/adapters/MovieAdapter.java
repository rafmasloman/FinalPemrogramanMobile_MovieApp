package com.rafmasloman.tugasfinalmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.rafmasloman.tugasfinalmobile.R;
import com.rafmasloman.tugasfinalmobile.models.Movies;
import com.rafmasloman.tugasfinalmobile.network.Const;
import com.rafmasloman.tugasfinalmobile.utils.OnItemClick;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movies> nowPlayings;
    private OnItemClick onItemClick;


    public MovieAdapter(List<Movies> nowPlayings, OnItemClick onItemClick){
        this.nowPlayings = nowPlayings;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_movies, parent, false);
        return new ViewHolder(view, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(Const.IMG_URL_200 + nowPlayings.get(position).getCover())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(25)))
                .into(holder.ivCover);
        holder.tvTitle.setText(nowPlayings.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return nowPlayings.size();
    }

    public void appendList(List<Movies> extraMovie){
        nowPlayings.addAll(extraMovie);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClick onItemClick;
        ImageView ivCover;
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView, OnItemClick onItemClick) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivCover = itemView.findViewById(R.id.iv_show_cover);
            tvTitle = itemView.findViewById(R.id.show_title);
            this.onItemClick = onItemClick;
        }

        @Override
        public void onClick(View view) {
            onItemClick.onClick(getAdapterPosition());
        }
    }
}

