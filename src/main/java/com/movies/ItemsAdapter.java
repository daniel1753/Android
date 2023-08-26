package com.movies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.movies.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> implements Filterable {

    private List<MovieModel> localDataSet;
    Context context;
    private List<MovieModel> filterdList;
    public UserClickListener userClickListener;

    public ItemsAdapter(List<MovieModel> localDataSet, Context context, UserClickListener userClickListener) {
        this.localDataSet = localDataSet;
        this.filterdList = new ArrayList<>(localDataSet);
        this.userClickListener = userClickListener;
        this.context = context;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length() == 0) {
                    filterResults.values = filterdList;
                    filterResults.count = filterdList.size();
                }else{
                    String searchStr = charSequence.toString().toLowerCase();
                    List<MovieModel> movieModelArrayList = new ArrayList<>();

                    for(MovieModel movie : filterdList)
                    {
                        //TODO: add more search ops
                        if(movie.getTitle().toLowerCase().contains(searchStr) ){
                            movieModelArrayList.add(movie);

                        }
                    }
                    filterResults.values = movieModelArrayList;
                    filterResults.count = movieModelArrayList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                localDataSet = (List<MovieModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }

    public interface UserClickListener
    {
        void selectedUser(MovieModel userMovieModel);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MovieModel movieModel = localDataSet.get(position);
        holder.titleTxt.setText("Title: " + movieModel.getTitle()+"\n");
        holder.overviewTxt.setText(movieModel.getOverview()+"\n");
        Glide.with(holder.itemView.getContext())
                .load( "https://image.tmdb.org/t/p/w500/"
                        +localDataSet.get(position).getPosterPath())
                .into((holder).poster);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Tag","thie title is " + movieModel.getTitle());

                userClickListener.selectedUser(movieModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, overviewTxt;
        ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster_img);
            titleTxt = itemView.findViewById(R.id.title_txt);
            overviewTxt= itemView.findViewById(R.id.overview_txt);

        }
    }

}
