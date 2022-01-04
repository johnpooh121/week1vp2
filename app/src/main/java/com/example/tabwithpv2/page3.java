package com.example.tabwithpv2;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    int sz,stage,life=3,l,maxscore_easy=0,maxscore_hard=0,ansx=0,ansy=0;
    Boolean isEasy=true;
    String bg,col;
    public page3() {
        stage=1;life=3;l=2;
    }

    int calculatel(int stage){
        return Integer.min(10,2+stage/5);
    }
    public String conv(int x){
        x=x%256;
        return Integer.toHexString(x/16)+""+Integer.toHexString(x%16);
    }

    public int change(int x,int d){
        if(x+d>255) return x-d;
        if(x+d<0) return x-d;
        return x+d;
    }
    public int myrd(int l,int r){
        Random rd =new Random();
        int k = rd.nextInt(r-l+1)+l;
        return k;
    }

//    public Pair<String,String> colorgen(){
    public void colorgen() {
        Random rd = new Random();
        int R = rd.nextInt(256);
        int G = rd.nextInt(256);
        int B = rd.nextInt(256);
        int dr, dg, db,range;
        range = Integer.max((25 - stage / 4), 8);
        if (isEasy) {
            int theta = myrd(-180, 180);
            int alpha = myrd(-90, 90);
            dr = (int) (range * Math.sin(Math.toRadians(alpha)));
            dg = (int) (range * Math.cos(Math.toRadians(alpha) * Math.sin(Math.toRadians(theta))));
            db = (int) (range * Math.cos(Math.toRadians(alpha) * Math.cos(Math.toRadians(theta))));
        }
        else{
            dr = myrd(-range, range);
            range -= Math.abs(dr);
            dg = myrd(-range, range);
            range -= Math.abs(dg);
            db = myrd(-range, range);
        }

//        if (isEasy) {
//            range = Integer.max((16 - stage / 4), 6);
//            int r1 = (myrd(0,1)*2)-1;
//            int r2 = (myrd(0,1)*2)-1;
//            int r3= (myrd(0,1)*2)-1;
//            dr = range*r1;
//            dg = range*r2;
//            db = range*r3;
//        }
//        else{
//            range = Integer.max((12 - stage / 3), 4);
//            int r1 = (myrd(0,1)*2)-1;
//            int r2 = (myrd(0,1)*2)-1;
//            int r3= (myrd(0,1)*2)-1;
//            dr = range*r1;
//            dg = range*r2;
//            db = range*r3;
//        }

        int nR = change(R, dr), nG = change(G, dg), nB = change(B, db);
        bg = "#"+conv(R)+conv(G)+conv(B);
        col = bg;
        col = "#"+conv(nR)+conv(nG)+conv(nB);

        SharedPreferences sharedPreferences;
        sharedPreferences = getContext().getSharedPreferences("maxscore",getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("bg", bg);
        editor.putString("col",col);
        editor.commit();

//        return new Pair(bg,col);
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

        maxscore_easy = sharedPreferences.getInt("maxscore_easy",1);
        maxscore_hard = sharedPreferences.getInt("maxscore_hard",1);
        life = sharedPreferences.getInt("life",999);
        stage = sharedPreferences.getInt("stage",0);
        isEasy = sharedPreferences.getBoolean("isEasy",true);

        if(life == 999){
            life = 3;
        }
        if(stage == 0){
            stage = 1;
        }


        if(isEasy){
            ((TextView)rootview.findViewById(R.id.maxscore)).setText(""+maxscore_easy);
            ((TextView)rootview.findViewById(R.id.mode)).setText("Stage (Easy)");
        }
        else {
            ((TextView)rootview.findViewById(R.id.maxscore)).setText(""+maxscore_hard);
            ((TextView)rootview.findViewById(R.id.mode)).setText("Stage (Hard)");
        }
        ((TextView)rootview.findViewById(R.id.life)).setText(""+life);
        ((TextView)rootview.findViewById(R.id.stage)).setText(""+stage);
        l = calculatel(stage);

        FragmentManager fragmentManager = getChildFragmentManager();
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

        Button cont = rootview.findViewById(R.id.btn_continue);
        page3 me=this;
        cont.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();

                gameover fragment = new gameover(stage,me);
                fragmentManager.beginTransaction().replace(R.id.gridcontainer,fragment).commit();
            } });

        if(life>0) {
            Random rd = new Random();
            bg= sharedPreferences.getString("bg","#000000");col=sharedPreferences.getString("col","#FFFFFF");
            if(bg=="#000000" && col=="#FFFFFF"){
                colorgen();
                bg= sharedPreferences.getString("bg","#000000");col=sharedPreferences.getString("col","#FFFFFF");
            }
            ansx = rd.nextInt(l) + 1;ansy=rd.nextInt(l) + 1;
            gamegrid fragment = new gamegrid(l, ansx, ansy, this,bg,col);
            fragmentManager.beginTransaction().replace(R.id.gridcontainer, fragment).commit();
        }
        else{
            gameover fragment = new gameover(stage,this);
            fragmentManager.beginTransaction().replace(R.id.gridcontainer, fragment).commit();
        }
//        Random rd = new Random();
//        gamegrid fragment = new gamegrid(l, rd.nextInt(l) + 1, rd.nextInt(l) + 1, this);
//        ConstraintLayout gc = rootview.findViewById(R.id.gridcontainer);
//        if(gc!=null)fragmentManager.beginTransaction().replace(R.id.gridcontainer, fragment).commit();
        return rootview;
    }

    @SuppressLint("NewApi")
    public void handletouch(Boolean iscorrect){

        SharedPreferences sharedPreferences;
        sharedPreferences = getContext().getSharedPreferences("maxscore",getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(iscorrect){
            stage++;
            ((TextView)rootview.findViewById(R.id.stage)).setText(""+stage);

            if(isEasy) {
                maxscore_easy = sharedPreferences.getInt("maxscore", 1);
                if (life > 0) {
                    maxscore_easy = Integer.max(maxscore_easy, stage);
                    editor.putInt("maxscore_easy", maxscore_easy);
                }
                ((TextView)rootview.findViewById(R.id.maxscore)).setText(""+maxscore_easy);
            }
            else {
                maxscore_hard = sharedPreferences.getInt("maxscore_hard", 1);
                if (life > 0) {
                    maxscore_hard = Integer.max(maxscore_hard, stage);
                    editor.putInt("maxscore_hard", maxscore_hard);
                }
                ((TextView)rootview.findViewById(R.id.maxscore)).setText(""+maxscore_hard);
            }
            editor.putInt("stage",stage);
            editor.putInt("life",life);
            editor.putBoolean("isEasy",isEasy);
            editor.commit();

            l = calculatel(stage);
            Random rd = new Random();
            FragmentManager fragmentManager = getChildFragmentManager();
            colorgen();
            bg= sharedPreferences.getString("bg","#000000");col=sharedPreferences.getString("col","#FFFFFF");
            ansx = rd.nextInt(l) + 1;ansy=rd.nextInt(l) + 1;
            gamegrid fragment = new gamegrid(l, ansx, ansy, this,bg,col);
            fragmentManager.beginTransaction().replace(R.id.gridcontainer,fragment).commit();
        }
        else{
            life--;
            editor.putInt("life",life);
            editor.putInt("stage",stage);
            editor.putBoolean("isEasy",isEasy);
            editor.commit();

            ((TextView)rootview.findViewById(R.id.life)).setText(""+life);
            if(life<=0){
                Button cont = rootview.findViewById(R.id.btn_continue);
                cont.setVisibility(View.VISIBLE);
                ToggleButton ans = rootview.findViewById(R.id.btn_answer);

                ans.setTextOn("row : "+ansx+"\ncol : "+ansy);
                ans.setTextOff("bg : "+bg+"\ncol : "+col);
                ans.setText("눌러보세요");
                ans.setVisibility(View.VISIBLE);
                LinearLayout grid = rootview.findViewById(R.id.outside);
                grid.setBackgroundColor(Color.parseColor(bg));
            }
        }
    }
    public void pressRestart(){
        Random rd = new Random();
        SharedPreferences sharedPreferences;
        sharedPreferences = getContext().getSharedPreferences("maxscore",getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        l=2;stage=1;life=3;
        FragmentManager fragmentManager = getChildFragmentManager();
        colorgen();
        bg= sharedPreferences.getString("bg","#000000");col=sharedPreferences.getString("col","#FFFFFF");
        ansx = rd.nextInt(l) + 1;ansy=rd.nextInt(l) + 1;
        gamegrid fragment = new gamegrid(l, ansx, ansy, this,bg,col);
        fragmentManager.beginTransaction().replace(R.id.gridcontainer,fragment).commit();

        editor.putInt("stage",stage);
        editor.putInt("life",life);
        editor.putBoolean("isEasy",isEasy);
        editor.commit();

        ((TextView)rootview.findViewById(R.id.stage)).setText(""+1);
        ((TextView)rootview.findViewById(R.id.life)).setText(""+3);
        if(isEasy){
            ((TextView)rootview.findViewById(R.id.maxscore)).setText(""+maxscore_easy);
            ((TextView)rootview.findViewById(R.id.mode)).setText("Stage (Easy)");
        }
        else {
            ((TextView)rootview.findViewById(R.id.maxscore)).setText(""+maxscore_hard);
            ((TextView)rootview.findViewById(R.id.mode)).setText("Stage (Hard)");
        }
        Button cont = rootview.findViewById(R.id.btn_continue);
        cont.setVisibility(View.GONE);
        Button ans = rootview.findViewById(R.id.btn_answer);
        ans.setVisibility(View.GONE);
        LinearLayout grid = rootview.findViewById(R.id.outside);
        grid.setBackgroundColor(getResources().getColor(R.color.white));
    }

}