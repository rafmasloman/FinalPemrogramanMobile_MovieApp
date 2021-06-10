package com.rafmasloman.tugasfinalmobile.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;


import com.rafmasloman.tugasfinalmobile.activities.DetailActivity;
import com.rafmasloman.tugasfinalmobile.R;

import com.rafmasloman.tugasfinalmobile.adapters.FavoriteAdapter;

import com.rafmasloman.tugasfinalmobile.utils.OnItemClick;
import com.rafmasloman.tugasfinalmobile.db.AppDatabase;
import com.rafmasloman.tugasfinalmobile.db.entities.Favorite;

import java.util.List;

public class FavoriteFragment extends Fragment implements OnItemClick, SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private AppDatabase database;
    private TextView tvNotFound;
    private List<Favorite> favoriteList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        database = AppDatabase.getInstance(getActivity().getApplicationContext());

        tvNotFound = view.findViewById(R.id.tv_no_favorite_found);
        tvNotFound.setVisibility(View.INVISIBLE);

        recyclerView = view.findViewById(R.id.rv_tv_show);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        loadData();
        return view;
    }

    private void loadData() {
        tvNotFound.setVisibility(View.INVISIBLE);
        database.favoriteDao().getAll().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                favoriteList = favorites;
                recyclerView.setAdapter(new FavoriteAdapter(favorites, FavoriteFragment.this));
                if(favorites.size()==0){
                    tvNotFound.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.movie_fragment_toolbar,menu);
        MenuItem item = menu.findItem(R.id.menu_item_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setFilteredFavorites(String s){
        tvNotFound.setVisibility(View.INVISIBLE);
        database.favoriteDao().getFiltered(s).observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                favoriteList = favorites;
                    recyclerView.setAdapter(new FavoriteAdapter(favorites, FavoriteFragment.this));
                if(favorites.size()==0){
                    tvNotFound.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(int pos) {
        Intent detailActivity = new Intent(getActivity(), DetailActivity.class);
        detailActivity.putExtra("ID", String.valueOf(favoriteList.get(pos).getId()));
        startActivity(detailActivity);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if(s.length()>0){
            s = "%"+s+"%";
            setFilteredFavorites(s);
        }else{
            loadData();
        }
        return true;
    }
}