package com.example.tabwithpv2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class picFocus extends AppCompatActivity {

    public PhotoView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_pic_focus);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int img = bundle.getInt("img");
        Uri uri=null;
        if(img==0){
            uri=Uri.parse(bundle.getString("uri"));
        }
        imgView = findViewById(R.id.focusViewer);
        if(img!=0)imgView.setImageResource(img);
        else{
            Glide.with(this).load(uri).into(imgView);
            //holder.imageView1.setImageURI(imgid.getUri());
        }
    }
}