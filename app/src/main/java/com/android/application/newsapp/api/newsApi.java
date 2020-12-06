package com.android.application.newsapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface newsApi {
    @GET("v2/top-headlines")
    Call<news> fetchNews( @Query("country") String country,@Query("pageSize") int pageSize, @Query("apiKey") String apiKey);
    @GET("v2/everything")
    Call<news> fetchEverything(@Query("q") String query, @Query("pageSize") int pageSize, @Query("apiKey") String apiKey);

}
