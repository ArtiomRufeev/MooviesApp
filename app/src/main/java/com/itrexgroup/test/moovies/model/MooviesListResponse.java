package com.itrexgroup.test.moovies.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class MooviesListResponse implements Serializable {
    @SerializedName("list")
    private ArrayList<Moovie> moovies;

    public ArrayList<Moovie> getMoovies() {
        return moovies;
    }

    public void setMoovies(ArrayList<Moovie> moovies) {
        this.moovies = moovies;
    }
}
