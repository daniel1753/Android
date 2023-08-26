package com.movies.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.movies.models.MovieModel;

import java.util.List;

public class MovieSearchResponse {

    @SerializedName("total_results")
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose()
    private List<MovieModel> movie;

    public int getTotal_count() {
         return total_count;
    }

    public List<MovieModel> getMovies(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_count=" + total_count +
                ", movie=" + movie +
                '}';
    }
}
