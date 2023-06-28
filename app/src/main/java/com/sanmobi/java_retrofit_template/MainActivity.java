package com.sanmobi.java_retrofit_template;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.sanmobi.java_retrofit_template.adapter.PhotoListAdapter;
import com.sanmobi.java_retrofit_template.model.Photo;
import com.sanmobi.java_retrofit_template.network.ApiClient;
import com.sanmobi.java_retrofit_template.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Photo> movieList;

    ProgressDialog progressDialog;

    RecyclerView recyclerView;
    PhotoListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        getData();
    }

    //functions
    private void initUi() {
        movieList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        //dialog
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Its loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //initRecycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        movieListAdapter = new PhotoListAdapter(getApplicationContext(),movieList);
        recyclerView.setAdapter(movieListAdapter);

    }

    //get data from network
    private void getData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Photo>> call = apiService.getMovies();

        progressDialog.show();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                movieList = response.body();
                Log.d("TAG","Response = "+movieList);
                movieListAdapter.setPhotoList(movieList);
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
                progressDialog.dismiss();
            }
        });
    }

}