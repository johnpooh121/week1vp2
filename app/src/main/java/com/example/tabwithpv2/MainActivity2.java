package com.example.tabwithpv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    public ImageView face;
    public TextView name1;
    public TextView number1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int img = bundle.getInt("imageId");
        String name = bundle.getString("name");
        String number = bundle.getString("number");

        face = findViewById(R.id.faceInDetail);
        name1 = findViewById(R.id.nameDetail);
        number1 = findViewById(R.id.numberDetail);

        face.setImageResource(img);
        name1.setText(name);
        number1.setText(number);

    }
}