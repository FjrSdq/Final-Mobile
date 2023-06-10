package com.example.h071211078_finalmobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.h071211078_finalmobile.data.model.Movie;
import com.example.h071211078_finalmobile.data.model.MovieResponse;


import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private final ArrayList<MovieResponse> movie = new ArrayList<>();
    private ClickListener clickListener;

    public MovieAdapter(List<Movie> movies){this.movies = movies;}

    public interface ClickListener {
        void onUserClicked(MovieResponse movieResponse);
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_film, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Movie movie = movies.get(position);
        holder.setData(movie, context);
    }

    @Override
    public int getItemCount(){return movies   != null ? movies.size() : 0;}

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImageView;
        private TextView titleTextView, yearTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.poster_iv);
            titleTextView = itemView.findViewById(R.id.title_tv);
            yearTextView = itemView.findViewById(R.id.year_tv);
        }

        public void setData(Movie movie, Context context){
            String title = movie.getTitle();
            String year = movie.getReleaseDate();
            String poster = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getPosterPath();
            titleTextView.setText(title);
            yearTextView.setText(year);
            Glide.with(context)
                    .load(poster)
                    .into(posterImageView);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), Film_Details.class);
                    intent.putExtra("movie",movie);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
