package com.bacter.customwallpaper.activity;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.bacter.customwallpaper.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        setContentView(R.layout.activity_about);
        //developers github website
        TextView github=(TextView)findViewById(R.id.gitHubID);
        github.setText(Html.fromHtml(getString(R.string.github_website)));
        github.setMovementMethod(new LinkMovementMethod());
        //developers facebook website
        TextView facebook=(TextView)findViewById(R.id.facebookID);
        facebook.setText(Html.fromHtml(getString(R.string.facebook_page)));
        facebook.setMovementMethod(new LinkMovementMethod());
        //clients facebook website
        TextView facebookDianne=(TextView)findViewById(R.id.facebookIDClient);
        facebookDianne.setText(Html.fromHtml(getString(R.string.facebook_dianne_page)));
        facebookDianne.setMovementMethod(new LinkMovementMethod());
    }
}