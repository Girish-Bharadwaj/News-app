package com.android.application.newsapp.views;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.android.application.newsapp.viewmodel.HeadlinesViewModel;

import java.util.List;

public class HeadlineFragment extends Fragment implements onItemClicked {
    RecyclerView recyclerView;
    List<articles> articlesList;
    HeadlinesViewModel viewModel;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    com.android.application.newsapp.adpaters.adapter adapter;
    SwipeRefreshLayout refreshLayout;

    public HeadlineFragment() {
        super(R.layout.fragment_headline);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerview);
        progressBar=view.findViewById(R.id.progressbar);
        refreshLayout=view.findViewById(R.id.swipeDown);
        //Creating layout manager for recyclerView
        layoutManager=new LinearLayoutManager(getContext());
        //Setting layout manager to recyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //Creating object adapter
        adapter=new adapter(getContext(),this);
        //Creating viewModel object
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(HeadlinesViewModel.class);
        //Observing liveData of list of articles from viewModel
        viewModel.getNewsHeadlines().observe(getViewLifecycleOwner(), new Observer<news>() {
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
                refresh();
                viewModel.refresh();
                refreshLayout.setRefreshing(false);
            }
        });
        //Setting adapter to recyclerView
        recyclerView.setAdapter(adapter);

    }
    public void refresh(){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void clicked(articles articles) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(getContext(), Uri.parse(articles.getUrl()));
    }
}