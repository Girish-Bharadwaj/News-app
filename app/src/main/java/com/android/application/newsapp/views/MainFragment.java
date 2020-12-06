package com.android.application.newsapp.views;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.application.newsapp.R;
import com.android.application.newsapp.adpaters.adapter;
import com.android.application.newsapp.adpaters.onItemClicked;
import com.android.application.newsapp.api.articles;
import com.android.application.newsapp.api.news;
import com.android.application.newsapp.viewmodel.MainViewModel;

import java.util.List;


public class MainFragment extends Fragment implements onItemClicked {
    RecyclerView recyclerView;
    List<articles> articlesList;
    MainViewModel viewModel;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    com.android.application.newsapp.adpaters.adapter adapter;
    SearchView searchView;
    SwipeRefreshLayout refreshLayout;
    String Query="all";
public MainFragment(){
    super(R.layout.fragment_main);
}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        searchView=view.findViewById(R.id.search_view);
        refreshLayout=view.findViewById(R.id.swipe);
        //Creating layout manager for recyclerView
        layoutManager=new LinearLayoutManager(getContext());
        //Setting layout manager to recyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //Creating object adapter
        adapter=new adapter(getContext(),this);
        //Creating viewModel object
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MainViewModel.class);
        //Observing liveData of list of articles from viewModel
        viewModel.getNewsLiveData().observe(getViewLifecycleOwner(), new Observer<news>() {
            @Override
            public void onChanged(@Nullable news news) {
                articlesList=news.getArticlesList();
                if(articlesList!=null){
                    adapter.setArticlesList(articlesList);
                    progressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.search(Query);
                searchView.setQuery("",false);
                refresh();
                searchView.clearFocus();
                refreshLayout.setRefreshing(false);
            }
        });
        //Setting adapter to recyclerView
        recyclerView.setAdapter(adapter);
        //Searching queries for articles
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Query=query;
                viewModel.search(query);
                searchView.setQuery("",false);
                refresh();
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
    public void refresh(){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        layoutManager.scrollToPosition(0);
    }

    @Override
    public void clicked(articles articles) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(getContext(), Uri.parse(articles.getUrl()));
    }
}