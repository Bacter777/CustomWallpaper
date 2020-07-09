package com.bacter.customwallpaper.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bacter.customwallpaper.R;
import com.bacter.customwallpaper.fragment.AboutFragment;
import com.bacter.customwallpaper.fragment.FavoriteFragment;
import com.bacter.customwallpaper.fragment.SettingsFragment;
import com.bacter.customwallpaper.fragment.WallpaperFragment;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Fragment fragment;
    Class fragmentClass;
    SNavigationDrawer sNavigationDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        this.sNavigationDrawer = (SNavigationDrawer)findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Wallpaper",R.drawable.wallpaper_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Favorite",R.drawable.favorite_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Settings",R.drawable.settings_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("About",R.drawable.about_bg));
        this.sNavigationDrawer.setMenuItemList(menuItems);
        Class<WallpaperFragment>cls=WallpaperFragment.class;
        this.fragmentClass=cls;
        try {
            fragment = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment !=null){
            getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out).replace(R.id.frameLayout,fragment).commit();
        }
        this.sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            public void onMenuItemClicked(final int position) {
                final PrintStream printStream = System.out;
                printStream.println("Position"+position);
                if (position==0){
                    MainActivity.this.fragmentClass = WallpaperFragment.class;
                }else if (position==1){
                    MainActivity.this.fragmentClass = FavoriteFragment.class;
                }else if (position==2){
                    MainActivity.this.fragmentClass = SettingsFragment.class;
                }else if (position==3){
                    MainActivity.this.fragmentClass = AboutFragment.class;
                }
                MainActivity.this.sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
                    public void onDrawerOpening() {
                    }
                    public void onDrawerClosing() {
                        try {
                            MainActivity.fragment = (Fragment)MainActivity.this.fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (MainActivity.fragment !=null){
                            MainActivity.this.getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out).replace(R.id.frameLayout,MainActivity.fragment).commit();
                        }
                    }
                    public void onDrawerOpened() {
                    }
                    public void onDrawerClosed() {
                    }
                    public void onDrawerStateChanged(int newState) {
                        PrintStream printStream1 = System.out;
                        printStream.println("State" + position);
                    }
                });
            }
        });
    }
}