package com.example.h071211078_finalmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.h071211078_finalmobile.data.TvService;
import com.example.h071211078_finalmobile.data.model.Tv;
import com.example.h071211078_finalmobile.data.model.TvResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvFragment extends Fragment {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "c7d3c0e31076dda58656ac4e35d44bd3";
    ProgressBar progressBar;
    TextView alertTv;
    ImageView btnRefresh;
    private RecyclerView recyclerView;
    private TvAdapter tvAdapter;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);

        progressBar =  view.findViewById(R.id.progressBar);
        alertTv = view.findViewById(R.id.alertTv);
        btnRefresh = view.findViewById(R.id.refreshBtn);
        recyclerView = view.findViewById(R.id.rv_tv);

        showLoading();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TvService tvService = retrofit.create(TvService.class);

        Call<TvResponse> call = tvService.getAiringTodayTV(API_KEY);

        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                if (response.isSuccessful()){
                    hideLoading();
                    TvResponse tvResponse = response.body();
                    List<Tv> tvShow = tvResponse.getTvShows();
                    tvAdapter =  new TvAdapter(tvShow);
                    recyclerView.setAdapter(tvAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    showAlert();
                    Toast.makeText(getActivity(), "Error : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        alertTv.setVisibility(View.INVISIBLE);
        btnRefresh.setVisibility(View.INVISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        alertTv.setVisibility(View.INVISIBLE);
        btnRefresh.setVisibility(View.INVISIBLE);
    }

    private void showAlert() {
        alertTv.setVisibility(View.VISIBLE);
        btnRefresh.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }
}