package com.movies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.movies.models.MovieModel;


public class SelectedItemActivity extends AppCompatActivity {

    TextView selectedTitle, selectedOverview;
    MovieModel movieModel;
    Intent intent;
    ImageView selectedPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);
        selectedTitle = findViewById(R.id.selected_title_txt);
        selectedOverview = findViewById(R.id.selected_overview_txt);
        selectedPoster = findViewById(R.id.selected_poster);
        intent = getIntent();

        if(intent != null)
        {
            movieModel = (MovieModel) intent.getParcelableExtra("data");

            if(movieModel == null)
            {
                Log.v("Tag", "this is null");
            }
            selectedTitle.setText("Title: " + movieModel.getTitle()+"\n");
            selectedOverview.setText(movieModel.getOverview()+"\n");
            Glide.with(this)
                    .load( "https://image.tmdb.org/t/p/w500/"
                            +movieModel.getPosterPath())
                    .into(selectedPoster);
        }

    }
}