package com.movies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movies.models.MovieModel;
import com.movies.response.MovieSearchResponse;
import com.movies.util.MovieApi;
import com.movies.vewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainFeedActivity extends AppCompatActivity  implements ItemsAdapter.UserClickListener {

    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter;
    private List<MovieModel> moviesList;
    private String BaseUrl = "https://api.themoviedb.org/";
    private String API_KEY = "abc6125d20f8b0f8a685a67ff80c41bd";
    MovieApi movieApi; //my be needed from the beginning
    Retrofit retrofit;
    //MovieViewModel movieViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        setSupportActionBar(findViewById(R.id.toolbar));

        //movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getResponse();

    }
/*
    private void ObserveAnyChange() {
        movieViewModel.getMutableLiveData().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data change
               /* if (movieModels != null){
                    for (MovieModel movieModel: movieModels){
                        // Get the data in log
                        movieRecyclerAdapter.setmMovies(movieModels);

                    }
                }


            }
        });
    }
*/
    public void prepareRecyclerView(){
        LinearLayoutManager manager = new LinearLayoutManager(MainFeedActivity.this , RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
    }

    public void preAdapter() {
        itemsAdapter = new ItemsAdapter(moviesList,this, this::selectedUser);
        recyclerView.setAdapter(itemsAdapter);
    }

    @Override
    public void selectedUser(MovieModel userMovieModel) {
        startActivity(new Intent(this, SelectedItemActivity.class).putExtra("data", userMovieModel));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.searchView){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               itemsAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void getResponse()
    {
        movieApi = retrofit.create(MovieApi.class);

        Call<MovieSearchResponse> arrayListCall = movieApi.searchMovie(API_KEY,
                "Action",
                "1");


        arrayListCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.isSuccessful()) {

                    moviesList = new ArrayList<>(response.body().getMovies());
                    for(MovieModel movie : moviesList)
                    {
                        prepareRecyclerView();
                        preAdapter();
                    }

                } else {
                    Toast.makeText(getParent(),"not response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Toast.makeText(getParent(),"Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getResponseByID()
    {
        movieApi = retrofit.create(MovieApi.class);

        Call<MovieModel> responseCall =movieApi.getMovie(550,API_KEY);

        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

}