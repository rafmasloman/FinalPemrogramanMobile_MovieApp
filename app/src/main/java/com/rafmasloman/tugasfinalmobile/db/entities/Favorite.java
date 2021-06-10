package com.rafmasloman.tugasfinalmobile.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "favorites")
public class Favorite implements Serializable {

    public Favorite(){}

    public Favorite(int id, String title, String imgUrl){
        this.id = id;
        this.title = title;
        this.imgUrl = imgUrl;
    }

    @PrimaryKey
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private String title;

    private String imgUrl;

    public Favorite(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
