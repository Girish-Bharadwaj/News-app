package com.android.application.newsapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofit {
private  static Retrofit retrofit;
private static String BASE_URL="http://newsapi.org";
public static Retrofit getRetrofitInstance() {
    if (retrofit == null) {
        retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    }
     return retrofit;
    }

}

