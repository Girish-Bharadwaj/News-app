package com.android.application.newsapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class news {
    @SerializedName("articles")
    private List<articles> articlesList;

    public  List<articles> getArticlesList() {
        return articlesList;
    }
}
