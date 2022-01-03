package com.example.tabwithpv2;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.tabwithpv2.databinding.FragmentPage2Binding;
import com.example.tabwithpv2.databinding.FragmentPage3Binding;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link page3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class page3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentPage3Binding binding;
    View rootview;
    int sz,stage,life,l,maxscore=0;
    public page3() {
        stage=1;life=3;l=2;
    }

    int calculatel(int stage){
        return Integer.min(10,2+stage/5);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment page3.
     */
    // TODO: Rename and change types and number of parameters
    public static page3 newInstance(String param1, String param2) {
        page3 fragment = new page3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences sharedPreferences;
        sharedPreferences = getContext().getSharedPreferences("maxscore",getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        rootview = inflater.inflate(R.layout.fragment_page3, container, false);

        maxscore = sharedPreferences.getInt("maxscore",1);
        life = sharedPreferences.getInt("life",999);
        stage = sharedPreferences.getInt("stage",0);
        if(life == 999){
            life = 3;
        }
        if(stage == 0){
            stage = 1;
        }


        ((TextView)rootview.findViewById(R.id.maxscore)).setText(""+maxscore);
        ((TextView)rootview.findViewById(R.id.life)).setText(""+life);
        ((TextView)rootview.findViewById(R.id.stage)).setText(""+stage);
        l = calculatel(stage);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Point pt = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getRealSize(pt);
        int csz = (int)((float)pt.x*0.95);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(csz,csz);
        rootview.findViewById(R.id.gridcontainer).setLayoutParams(params);
        LinearLayout grid = rootview.findViewById(R.id.outside);
        grid.setBackgroundColor(getResources().getColor(R.color.white));
        ToggleButton tgbtn = rootview.findViewById(R.id.tgbtn);
        tgbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout grid = rootview.findViewById(R.id.outside);
                if (isChecked) {
                    grid.setBackgroundColor(getResources().getColor(R.color.black));
                } else {
                    grid.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });
        if(life>0) {
            Random rd = new Random();
            gamegrid fragment = new gamegrid(l, rd.nextInt(l) + 1, rd.nextInt(l) + 1, this);
            fragmentManager.beginTransaction().replace(R.id.gridcontainer, fragment).commit();
        }
        else{
            gameover fragment = new gameover(stage,this);
            fragmentManager.beginTransaction().replace(R.id.gridcontainer,fragment).commit();
        }
        return rootview;
    }

    @SuppressLint("NewApi")
    public void handletouch(Boolean iscorrect){
        //Toast.makeText(getContext(),""+iscorrect,Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences;
        sharedPreferences = getContext().getSharedPreferences("maxscore",getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(iscorrect){
            stage++;
            ((TextView)rootview.findViewById(R.id.stage)).setText(""+stage);

            maxscore = sharedPreferences.getInt("maxscore",1);
            maxscore=Integer.max(maxscore,stage);
            editor.putInt("maxscore",maxscore);
            editor.putInt("stage",stage);
            editor.putInt("life",life);
            editor.commit();
            ((TextView)rootview.findViewById(R.id.maxscore)).setText(""+maxscore);

            l = calculatel(stage);
            Random rd = new Random();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            gamegrid fragment = new gamegrid(l,rd.nextInt(l)+1,rd.nextInt(l)+1,this);
            fragmentManager.beginTransaction().replace(R.id.gridcontainer,fragment).commit();
        }
        else{
            life--;

            maxscore = sharedPreferences.getInt("maxscore",1);
            editor.putInt("life",life);
            editor.putInt("stage",stage);
            editor.commit();

            ((TextView)rootview.findViewById(R.id.life)).setText(""+life);
            if(life<=0){
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                gameover fragment = new gameover(stage,this);



                fragmentManager.beginTransaction().replace(R.id.gridcontainer,fragment).commit();
            }
        }
    }
    public void pressRestart(){
        Random rd = new Random();
        l=2;stage=1;life=3;
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        gamegrid fragment = new gamegrid(l,rd.nextInt(l)+1,rd.nextInt(l)+1,this);
        fragmentManager.beginTransaction().replace(R.id.gridcontainer,fragment).commit();
        SharedPreferences sharedPreferences;
        sharedPreferences = getContext().getSharedPreferences("maxscore",getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("stage",stage);
        editor.putInt("life",life);
        editor.commit();

        ((TextView)rootview.findViewById(R.id.stage)).setText(""+1);
        ((TextView)rootview.findViewById(R.id.life)).setText(""+3);
    }

}