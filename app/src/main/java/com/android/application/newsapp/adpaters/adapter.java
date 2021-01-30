package com.android.application.newsapp.adpaters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.application.newsapp.R;
import com.android.application.newsapp.api.articles;
import com.bumptech.glide.Glide;

import java.util.List;

public class adapter extends RecyclerView.Adapter<viewHolder>{
List<articles> articlesList;
Context context;
onItemClicked onItemClicked;

    public adapter(Context context,onItemClicked onItemClicked){
        this.context=context;
        this.onItemClicked=onItemClicked;
    }
    public void setArticlesList(List<articles> articlesList) {
        this.articlesList = articlesList;
        notifyDataSetChanged();
        Log.d("IRONMAN","Notified data changes");
}

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(context).inflate(R.layout.view,parent,false);
    viewHolder viewHolder=new viewHolder(view);
    view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onItemClicked.clicked(articlesList.get(viewHolder.getAdapterPosition()));
        }
    });
    return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if(articlesList!=null){
            holder.author.setText(articlesList.get(position).getAuthor());
            holder.description.setText(articlesList.get(position).getDescription());
            holder.title.setText(articlesList.get(position).getTitle());
            String imageUrl=articlesList.get(position).getUrlToImage();
            if(imageUrl==null){}else {
                Glide.with(context).load(imageUrl)
                        .centerCrop().into(holder.imageView);
            }
        }
    }

    @Override
    public int getItemCount() {
       if (articlesList==null){
           return 0;
       }
       else {
           return articlesList.size();
       }
    }
}



