package com.bacter.customwallpaper.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.bacter.customwallpaper.R;
import com.bacter.customwallpaper.adapter.RecyclerViewAdapter;
import com.bacter.customwallpaper.items.customItems;

import java.util.ArrayList;
import java.util.List;

public class WallpaperActivity extends AppCompatActivity {
    List<customItems>itemsList;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ProgressBar progressBar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallpaper);
        this.context=this;
        this.recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        this.recyclerView.setHasFixedSize(true);
        this.progressBar=findViewById(R.id.progressbar);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        this.itemsList= new ArrayList();
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/TauGammaSigmaI.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/TauGammaSigmaII.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/TauGammaSigmaIII.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/TauGammaSigmaIV.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/UrgelloChapter.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/UrgelloChapterSince1993.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/WeAreStrong.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/WeAreStrongII.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/TauGammaSigmaV.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/Greek.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/Greek_Letters.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/blue.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/true_blooded.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/Transkelion.png"));
        this.itemsList.add(new customItems("https://raw.githubusercontent.com/Bacter777/TauGammaSigma-Wallpapers/master/Sigmalupet.png"));
        RecyclerViewAdapter recyclerViewAdapter2 = new RecyclerViewAdapter(this,itemsList,this);
        this.adapter = recyclerViewAdapter2;
        this.recyclerView.setAdapter(recyclerViewAdapter2);
    }
}