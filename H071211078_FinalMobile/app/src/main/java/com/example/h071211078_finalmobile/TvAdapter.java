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
import com.example.h071211078_finalmobile.data.model.Tv;
import com.example.h071211078_finalmobile.data.model.TvResponse;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {
    private List<Tv> tvShows;
    private MovieAdapter.ClickListener clickListener;

    public TvAdapter(List<Tv> tvShows){this.tvShows = tvShows;}

    public interface ClickListener {
        void onUserClicked(TvResponse tvResponse);
    }

    public void setClickListener(MovieAdapter.ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_film, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position){
        Context context = holder.itemView.getContext();
        Tv tvShow = tvShows.get(position);
        holder.setData(tvShow, context);
    }

    @Override
    public int getItemCount(){return tvShows != null ? tvShows.size() : 0;}

    static class TvViewHolder extends RecyclerView.ViewHolder{
        private ImageView posterImageView;
        private TextView titleTextView, yearTextView;

        public TvViewHolder(@NonNull View itemView){
            super(itemView);
            posterImageView = itemView.findViewById(R.id.poster_iv);
            titleTextView = itemView.findViewById(R.id.title_tv);
            yearTextView = itemView.findViewById(R.id.year_tv);
        }

        public void setData(Tv tvShow, Context context){
            String title = tvShow.getName();
            String year = tvShow.getFirstAirDate();
            String poster = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/" + tvShow.getPosterUrl();
            titleTextView.setText(title);
            yearTextView.setText(year);
            Glide.with(context)
                    .load(poster)
                    .into(posterImageView);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), Film_Details.class);
                    intent.putExtra("show",tvShow);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}

