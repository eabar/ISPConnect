package com.example.ispconnect.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.ispconnect.utils.NewsItem;
import com.example.ispconnect.R;
import com.example.ispconnect.activities.NewsDetailActivity;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<NewsItem> newsList;
    private Context context;

    public NewsAdapter(ArrayList<NewsItem> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsItem newsItem = newsList.get(position);

        holder.title.setText(newsItem.getTitle());
        holder.description.setText(newsItem.getDescription());

        if (newsItem.getImageUrl() != null && !newsItem.getImageUrl().isEmpty()) {
            Glide.with(context).load(newsItem.getImageUrl()).into(holder.image);
            holder.image.setVisibility(View.VISIBLE);
        } else {
            holder.image.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewsDetailActivity.class);
            intent.putExtra("link", newsItem.getLink());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, description;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.newsImage);
            title = itemView.findViewById(R.id.newsTitle);
            description = itemView.findViewById(R.id.newsDescription);
        }
    }
}
