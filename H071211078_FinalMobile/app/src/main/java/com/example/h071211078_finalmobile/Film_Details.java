package com.example.h071211078_finalmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.h071211078_finalmobile.data.model.Favorite;
import com.example.h071211078_finalmobile.data.model.Movie;
import com.example.h071211078_finalmobile.data.model.Tv;
import com.example.h071211078_finalmobile.database.DatabaseHelper;

public class Film_Details extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ImageView backdropImageView, backButton, favButton, posterImageView, typeImageView;
    private TextView titleTextView, releaseDateTextView, ratingTextView, synopsisTextView;
    private boolean favoriteBoolean = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);

        backdropImageView = findViewById(R.id.backdrop_iv);
        backButton = findViewById(R.id.btn_back);
        favButton = findViewById(R.id.btn_fav);
        posterImageView = findViewById(R.id.poster_iv);
        releaseDateTextView = findViewById(R.id.release_date_tv);
        titleTextView = findViewById(R.id.title_tv);
        typeImageView = findViewById(R.id.type_iv);
        ratingTextView = findViewById(R.id.rating_tv);
        synopsisTextView = findViewById(R.id.synopsis_tv);
        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if(intent.getParcelableExtra("movie") != null){
            Movie movie = intent.getParcelableExtra("movie");
            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getBackdropUrl();
            titleTextView.setText(movie.getTitle());
            ratingTextView.setText(movie.getVoteAvg().toString());
            releaseDateTextView.setText(movie.getReleaseDate());
            Glide.with(this)
                    .load(posterUrl)
                    .into(posterImageView);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(backdropImageView);
            synopsisTextView.setText(movie.getOverview());

            if (dbHelper.isMovieInFavorites(movie.getTitle())) {
                favoriteBoolean = !favoriteBoolean;
            }

            if (favoriteBoolean) {
                favButton.setImageResource(R.drawable.ic_favorite_full);
            }

            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dbHelper.isMovieInFavorites(movie.getTitle())) {
                        favoriteBoolean = !favoriteBoolean;
                        favButton.setImageResource(R.drawable.ic_favorite_full);
                        addMovieToFavorites(movie.getId(), movie.getOverview(), posterUrl, movie.getReleaseDate(), movie.getTitle(), movie.getVoteAvg(), backdropUrl);
                    } else {
                        favoriteBoolean = !favoriteBoolean;
                        favButton.setImageResource(R.drawable.ic_favorite_border);
                        deleteMovieFromFavorites(movie.getTitle());
                    }
                }
            });

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {onBackPressed();}
            });


        } else if (intent.getParcelableExtra("show")  != null) {
            Tv show = intent.getParcelableExtra("show");
            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + show.getPosterUrl();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + show.getBackdropUrl();
            titleTextView.setText(show.getName());
            ratingTextView.setText(show.getVoteAvg().toString());
            releaseDateTextView.setText(show.getFirstAirDate());
            Glide.with(this)
                    .load(posterUrl)
                    .into(posterImageView);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(backdropImageView);
            synopsisTextView.setText(show.getOverview());

            if (dbHelper.isMovieInFavorites(show.getName())) {
                favoriteBoolean = !favoriteBoolean;
            }

            if (favoriteBoolean) {
                favButton.setImageResource(R.drawable.ic_favorite_full);
            }

            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dbHelper.isMovieInFavorites(show.getName())){
                        favoriteBoolean = !favoriteBoolean;
                        favButton.setImageResource(R.drawable.ic_favorite_full);
                        addMovieToFavorites(show.getId(), show.getOverview(), posterUrl, show.getName(), show.getName(), show.getVoteAvg(), backdropUrl);
                    }else {
                        favoriteBoolean = !favoriteBoolean;
                        favButton.setImageResource(R.drawable.ic_favorite_border);
                        deleteMovieFromFavorites(show.getName());
                    }
                }
            });

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {onBackPressed();}
            });

        } else {
            Favorite favorite = intent.getParcelableExtra("favorite");
            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getBackdropUrl();
            titleTextView.setText(favorite.getTitle());
            ratingTextView.setText(favorite.getVoteAvg().toString());
            releaseDateTextView.setText(favorite.getReleaseDate());
            Glide.with(this)
                    .load(posterUrl)
                    .into(posterImageView);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(backdropImageView);
            synopsisTextView.setText(favorite.getOverview());

            if (dbHelper.isMovieInFavorites(favorite.getTitle())) {
                favoriteBoolean = !favoriteBoolean;
            }

            if (favoriteBoolean) {
                favButton.setImageResource(R.drawable.ic_favorite_full);
            }

            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dbHelper.isMovieInFavorites(favorite.getTitle())) {
                        favoriteBoolean = !favoriteBoolean;
                        favButton.setImageResource(R.drawable.ic_favorite_full);
                        addMovieToFavorites(favorite.getId(), favorite.getOverview(), posterUrl, favorite.getTitle(), favorite.getTitle(), favorite.getVoteAvg(), backdropUrl);
                    } else {
                        favoriteBoolean = !favoriteBoolean;
                        favButton.setImageResource(R.drawable.ic_favorite_border);
                        deleteMovieFromFavorites(favorite.getTitle());
                    }
                }
            });

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {onBackPressed();}
            });
        }
    }

    private void addMovieToFavorites(int id, String overview, String posterUrl, String releaseDate, String title, double voteAvg, String backdropUrl){
        Movie movie = new Movie(id, overview, posterUrl, releaseDate, title, voteAvg, backdropUrl);
        long result = dbHelper.insertMovie(movie);
        if(result != -1){
            Toast.makeText(this, "Movie successfully added to favorites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Movie failed to be added to favorites", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteMovieFromFavorites(String name){
        long result = dbHelper.deleteMovie(name);
        if (result != -1){
            Toast.makeText(this, "Movie successfully deleted from favorites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Movie failed to be removed from favorites", Toast.LENGTH_SHORT).show();
        }
    }
}