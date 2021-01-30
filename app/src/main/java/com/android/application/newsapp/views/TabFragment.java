package com.android.application.newsapp.views;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class TabFragment extends Fragment implements onItemClicked {
   RecyclerView recyclerView;
   HeadlinesViewModel viewModel;
   List<articles> articlesList;
   adapter adapter;
   ProgressBar progressBar;
   SwipeRefreshLayout refreshLayout;
   String query;
   LinearLayoutManager layoutManager;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        progressBar=view.findViewById(R.id.progress_bar);
        refreshLayout=view.findViewById(R.id.swipe_down);
        layoutManager=new LinearLayoutManager(getContext());
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        query=getArguments().getString("query");
        if(query.equals("india")){
            query="general";
        }
        adapter=new adapter(getContext(),this);
        viewModel=new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(HeadlinesViewModel.class);
        viewModel.refresh(query);
        adapter.notifyDataSetChanged();
        viewModel.getNewsHeadlines().observe(getViewLifecycleOwner(), new Observer<news>() {
            @Override
            public void onChanged(news news) {
                articlesList=news.getArticlesList();
                if(articlesList!=null){
                    adapter.setArticlesList(articlesList);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    refreshLayout.setRefreshing(false);

                }
            }
        });
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh(query);
            }
        });
        return view;
    }
    @Override
    public void clicked(articles articles) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(getContext(), Uri.parse(articles.getUrl()));
    }
}


