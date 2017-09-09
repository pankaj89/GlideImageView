package com.master.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.master.glideimageview.GlideImageView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final ImageView img = (ImageView) findViewById(R.id.imageView);

        GlideImageView.getGlide(getApplicationContext())
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Pizigani_1367_Chart_10MB.jpg/800px-Pizigani_1367_Chart_10MB.jpg").asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                img.setImageBitmap(resource);
            }
        });
    }
}