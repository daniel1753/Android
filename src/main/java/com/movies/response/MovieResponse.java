package com.movies.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.movies.models.MovieModel;

//provide specific movie
public class MovieResponse {

    @SerializedName("results")
    @Expose
    private MovieModel movie;


    public MovieModel getMovie() {
        return movie;
    }
}
