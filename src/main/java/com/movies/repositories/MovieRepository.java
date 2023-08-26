package com.movies.repositories;

import androidx.lifecycle.MutableLiveData;

import com.movies.models.MovieModel;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;

    private MutableLiveData<List<MovieModel>> mutableLiveData = new MutableLiveData<>();



}
