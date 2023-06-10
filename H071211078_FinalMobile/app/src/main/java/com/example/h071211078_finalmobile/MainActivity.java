package com.example.h071211078_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView movieImg,tvImg,favImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieImg = findViewById(R.id.movie_img);
        tvImg = findViewById(R.id.tv_img);
        favImg = findViewById(R.id.fav_img);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, new MovieFragment(),
                        MovieFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();

        movieImg.setOnClickListener(btn -> {
            Drawable drawable = movieImg.getDrawable();
            drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            movieImg.setImageDrawable(drawable);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawable.clearColorFilter();
                    movieImg.setImageDrawable(drawable);
                }
            },200);
            MovieFragment movieFragment = new MovieFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, movieFragment,
                            MovieFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });

        tvImg.setOnClickListener(btn -> {
            Drawable drawable = tvImg.getDrawable();
            drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            tvImg.setImageDrawable(drawable);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawable.clearColorFilter();
                    tvImg.setImageDrawable(drawable);
                }
            },200);
            TvFragment tvShow = new TvFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, tvShow,
                            TvFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });

        favImg.setOnClickListener(btn ->{
            Drawable drawable = favImg.getDrawable();
            drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            favImg.setImageDrawable(drawable);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawable.clearColorFilter();
                    favImg.setImageDrawable(drawable);
                }
            },200);
            FavoriteFragment favorite = new FavoriteFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, favorite,
                            FavoriteFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });
    }
}