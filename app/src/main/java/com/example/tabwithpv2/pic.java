package com.example.tabwithpv2;

import android.net.Uri;

public class pic {
    int image;
    Uri uri;

    pic(int img,Uri u){
        this.image = img;
        this.uri=u;
    }
    int getImage(){
        return image;
    }
    Uri getUri(){return uri;}
}
