package com.android.application.newsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.application.newsapp.api.news;
import com.android.application.newsapp.repository.repository;

public class MainViewModel extends AndroidViewModel {
   private repository repository;
   private LiveData<news> newsLiveData;

   String query="all";
//Constructor to get application and creating object for repository and calling method of getting data from repository
    public MainViewModel(@NonNull Application application) {
        super(application);
        repository=new repository(application);
        newsLiveData=repository.getNewsLiveData("all");

    }
//method to access liveData of list of articles
    public LiveData<news> getNewsLiveData() {
        return newsLiveData;
    }
    //method to handle search queries
    public void search(String query){
        if(query==null){
            this.query="all";
        }
        else {
            this.query=query;
        }
        newsLiveData=repository.getNewsLiveData(query);
    }


}
