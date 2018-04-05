package com.itrexgroup.test.moovies.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Moovie implements Serializable {

    private String image;
    private String name;
    @SerializedName("name_eng")
    private String nameEng;
    private String premiere;
    private String description;

    public Moovie() {
    }

    public Moovie(String image, String name, String nameEng, String premiere, String description) {
        this.image = image;
        this.name = name;
        this.nameEng = nameEng;
        this.premiere = premiere;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getPremiere() {
        return premiere;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
