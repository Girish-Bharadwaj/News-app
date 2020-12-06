package com.android.application.newsapp.adpaters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.application.newsapp.R;

public class viewHolder extends RecyclerView.ViewHolder{
TextView author,title,description;
ImageView imageView;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        author=itemView.findViewById(R.id.author);
        title=itemView.findViewById(R.id.title);
        description=itemView.findViewById(R.id.description);
        imageView=itemView.findViewById(R.id.imageView);

    }
}
