package com.afrosurv.ICGC_Hamburg;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ItemList extends RecyclerView.Adapter<ItemList.ViewHolder> {

    ImageView image;

    ArrayList<String> urls;
    Context context;

    //constructor
    public ItemList(ArrayList<String> ImgUrl, Context context_) {
        this.urls = ImgUrl;
        this.context = context_;
    }

    @Override
    public ItemList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_list, parent, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(700, 700));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(this.context)
                .load(urls.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getImage());
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public ViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.img);
        }

        public ImageView getImage() {
            return this.image;
        }
    }

}