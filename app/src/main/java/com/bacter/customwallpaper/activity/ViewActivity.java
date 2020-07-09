package com.bacter.customwallpaper.activity;

import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.bacter.customwallpaper.R;
import com.bacter.customwallpaper.helper.SaveImageHelper;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_CODE=1000;
    FloatingActionMenu floatingActionMenu;
    FloatingActionButton btn_download;
    FloatingActionButton btn_set;
    FloatingActionButton btn_share;
    ImageView imageView;

    public void onRequestPermissionResult(int requestCode,String[]permissions,int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode !=1000){
            return;
        }
        if (grantResults.length<=0 || grantResults[0]!=0){
            Toast.makeText(this,"Sis Dianne Permission Granted",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Dianne Permission Denied",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        if (ActivityCompat.checkSelfPermission(this,"android.permission.WRITE_EXTERNAL_STORAGE")!=0 && Build.VERSION.SDK_INT>=23){
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},1000);
        }
        this.floatingActionMenu=findViewById(R.id.floatingActionMenu);
        this.btn_set=findViewById(R.id.setWallpaper);
        this.btn_share=findViewById(R.id.shareWallpaper);
        this.btn_download=findViewById(R.id.downloadWallpaper);
        this.imageView=findViewById(R.id.fullImage);
        //set button click
        this.btn_download.setOnClickListener(this);
        this.btn_share.setOnClickListener(this);
        this.btn_set.setOnClickListener(this);
        //picasso
        Picasso.get().load(getIntent().getStringExtra("images")).into(this.imageView);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.downloadWallpaper:
                saveImage();
                this.floatingActionMenu.close(true);
                return;
            case R.id.setWallpaper:
                setBackground();
                this.floatingActionMenu.close(true);
                return;
            case R.id.shareWallpaper:
                shareImage();
                this.floatingActionMenu.close(true);
                return;
            default:
                return;
        }
    }
    private void shareImage(){
        Bitmap bitmap = getBitmapFromView(this.imageView);
        try {
            File file = new File(getExternalCacheDir(),"black.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true,false);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(this,"com.bacter.customwallpaper.provider",file));
            intent.setType("image/*");
            startActivity(Intent.createChooser(intent,"Share Image Via"));
            Toast.makeText(this,"Share Wallpaper",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private Bitmap getBitmapFromView(View view){
        Bitmap returnBitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable !=null){
            bgDrawable.draw(canvas);
        }else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnBitmap;
    }
    private void setBackground(){
        try {
            WallpaperManager.getInstance(getApplicationContext()).setBitmap(((BitmapDrawable)this.imageView.getDrawable()).getBitmap());
            Toast.makeText(this,"Set Wallpaper Successfully",Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this,"Oops..Something Went Wrong!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private void saveImage(){
        if (ActivityCompat.checkSelfPermission(this,"android.permission.WRITE_EXTERNAL_STORAGE")!=0){
            Toast.makeText(this,"You Should Grant This Permission You IDIOT!",Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT>=23){
                requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},1000);
                return;
            }
            return;
        }
        RequestCreator load = Picasso.get().load(getIntent().getStringExtra("images"));
        Context baseContext = getBaseContext();
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        load.into((Target)new SaveImageHelper(baseContext,contentResolver, UUID.randomUUID().toString()+"jpg","Image Description"));
    }
}