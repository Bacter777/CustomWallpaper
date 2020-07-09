package com.bacter.customwallpaper.helper;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;

public class SaveImageHelper implements Target {
    private WeakReference<ContentResolver>contentResolverWeakReference;
    private Context context;
    private String desc;
    private String name;
    public SaveImageHelper(Context context2,ContentResolver contentResolver,String name2,String desc2){
        this.context=context2;
        this.contentResolverWeakReference=new WeakReference<>(contentResolver);
        this.name=name2;
        this.desc=desc2;
    }
    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        ContentResolver r = (ContentResolver)this.contentResolverWeakReference.get();
        if (r!=null){
            MediaStore.Images.Media.insertImage(r,bitmap,this.name,this.desc);
            Toast.makeText(this.context,"Downloaded Successfully",Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this.context,"Oops..Something Went Wrong!",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
        Context context2=this.context;
        Toast.makeText(context2,""+e.getMessage(),Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
    }
}
