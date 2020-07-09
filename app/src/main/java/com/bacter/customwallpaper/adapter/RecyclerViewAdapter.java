package com.bacter.customwallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bacter.customwallpaper.R;
import com.bacter.customwallpaper.activity.ViewActivity;
import com.bacter.customwallpaper.activity.WallpaperActivity;
import com.bacter.customwallpaper.items.customItems;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ImageViewHolder> {
    Context context;
    List<customItems>itemsList;

    public RecyclerViewAdapter(WallpaperActivity wallpaperActivity, List<customItems> itemsList2, Context context2){
        this.itemsList=itemsList2;
        this.context=context2;
    }
    public ImageViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        return new ImageViewHolder(LayoutInflater.from(this.context).inflate(R.layout.custom_layout,parent,false));
    }
    public void onBindViewHolder(ImageViewHolder holder,int position){
        Picasso.get().load(this.itemsList.get(position).getUrl()).into(holder.imageView);
    }
    public int getItemCount() {
        return this.itemsList.size();
    }
    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        public ImageViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            this.imageView=(ImageView)itemView.findViewById(R.id.img);
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(RecyclerViewAdapter.this.context, ViewActivity.class);
            intent.putExtra("images",RecyclerViewAdapter.this.itemsList.get(getAdapterPosition()).getUrl());
            RecyclerViewAdapter.this.context.startActivity(intent);
        }
    }
}
