package com.android.application.newsapp.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.application.newsapp.api.news;
import com.android.application.newsapp.api.newsApi;
import com.android.application.newsapp.api.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class repository {
    final String API_KEY = "09cddace147644b38c5c5e161b2f0189";
    MutableLiveData<news> newsLiveData= new MutableLiveData<>();
    MutableLiveData<news> newHeadlines=new MutableLiveData<>();
    newsApi newsApi;
    Application application;
    //Constructor for passing application and creating instance of retrofit
   public repository(Application application){
       this.application=application;
       Retrofit instance= retrofit.getRetrofitInstance();
       newsApi=instance.create(com.android.application.newsapp.api.newsApi.class);

   }
    //Method to get data from server asynchronously and posting the data into liveData
  public MutableLiveData<news> getNewsLiveData(String query){
       if (query==null){
           query="all";
       }
       Call<news> newsCall = newsApi.fetchEverything(query, 50, API_KEY);
       newsCall.enqueue(new Callback<news>() {
           @Override
           public void onResponse(Call<news> call, Response<news> response) {
               if(!response.isSuccessful()){
                   Toast.makeText(application,""+response.code(),Toast.LENGTH_LONG).show();
                   return;
               }
               news news=response.body();
               newsLiveData.postValue(news);

           }
           @Override
           public void onFailure(Call<news> call, Throwable t) {
               Toast.makeText(application,""+t.getMessage(),Toast.LENGTH_LONG).show();
           }
       });
       return newsLiveData;
  }
    public MutableLiveData<news> getHeadlines(){
       Call<news> newsCall = newsApi.fetchNews("in",50, API_KEY);
        newsCall.enqueue(new Callback<news>() {
            @Override
            public void onResponse(Call<news> call, Response<news> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(application,""+response.code(),Toast.LENGTH_LONG).show();
                    return;
                }
                news news=response.body();
                newHeadlines.postValue(news);

            }
            @Override
            public void onFailure(Call<news> call, Throwable t) {
                Toast.makeText(application,""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return newHeadlines;
    }

}
