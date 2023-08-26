package com.movies.vewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movies.models.MovieModel;

import java.util.List;

public class MovieViewModel extends ViewModel {



    //ctor
    public MovieViewModel(MutableLiveData<List<MovieModel>> mutableLiveData) {

    }

    public MutableLiveData<List<MovieModel>> getMutableLiveData() {
        return null;
    }
}
