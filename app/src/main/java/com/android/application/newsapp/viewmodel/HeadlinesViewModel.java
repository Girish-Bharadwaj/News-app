package com.android.application.newsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.application.newsapp.api.news;
import com.android.application.newsapp.repository.repository;

public class HeadlinesViewModel extends AndroidViewModel {
    private repository repository;
    private LiveData<news> newsHeadlines;
    public HeadlinesViewModel(@NonNull Application application) {
        super(application);
        repository=new repository(application);
        newsHeadlines=repository.getHeadlines();
    }

    public LiveData<news> getNewsHeadlines() {
        return newsHeadlines;
    }
    public void refresh(){
        newsHeadlines=repository.getHeadlines();
    }
}
