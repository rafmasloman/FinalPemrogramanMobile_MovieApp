package com.rafmasloman.tugasfinalmobile.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rafmasloman.tugasfinalmobile.models.Genre;

import java.util.List;

public class Movie {
    @Expose
    private int id;

    @Expose
    private String title;

    @SerializedName("backdrop_path")
    @Expose
    private String backdrop;

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    @SerializedName("overview")
    @Expose
    private String description;

    @SerializedName("poster_path")
    @Expose
    private String cover;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("runtime")
    @Expose
    private int duration;

    @Expose
    private List<Genre> genres;

    @SerializedName("vote_average")
    @Expose
    private double rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
