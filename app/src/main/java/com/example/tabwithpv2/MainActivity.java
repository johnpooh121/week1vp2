package com.example.tabwithpv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //< create >
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //</ create >

        //< get elements >
        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        //</ get elements >

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position){
                            case 0:{
                                tab.setText("Contacts"); //텝레이아웃 상단 타이틀 제목 설정
                                break;
                            }
                            case 1:{
                                tab.setText("Album");
                                break;
                            }
                            case 2:{
                                tab.setText("Game");
                                break;
                            }
                        }
                    }
                }).attach();
        //
    }
}