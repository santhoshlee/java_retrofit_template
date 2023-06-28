package com.sanmobi.java_retrofit_template.network;

import com.sanmobi.java_retrofit_template.model.Photo;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/photos")
    Call<List<Photo>> getMovies();

}
