package com.android.application.newsapp.adpaters;

import com.android.application.newsapp.api.articles;

public interface onItemClicked{
    default void clicked(articles articles){}
}
