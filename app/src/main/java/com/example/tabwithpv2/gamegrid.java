package com.example.tabwithpv2;

import static android.view.ViewGroup.LayoutParams.*;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.GridLayout;
import android.widget.Button;
import android.widget.GridLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link gamegrid#newInstance} factory method to
 * create an instance of this fragment.
 */
public class gamegrid extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int l=10,x=2,y=2,stg;
    page3 parent;
    public gamegrid(int l,int x,int y,page3 pg3) {
        this.l=l;
        this.x=x;
        this.y=y;
        this.parent=pg3;
        stg=parent.stage;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment gamegrid.
     */
    // TODO: Rename and change types and number of parameters
    public static gamegrid newInstance(String param1, String param2) {
        gamegrid fragment = new gamegrid(1,1,1,null);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_gamegrid, container, false);
        GridLayout grid = rootview.findViewById(R.id.grid);
        grid.setColumnCount(l);
        grid.setRowCount(l);
        Point pt = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getRealSize(pt);

        LinearLayout.LayoutParams params;

        int sz = (int)(((float)pt.x)*0.95);
        int cellsz = (int)(((float)(sz-10*(l+1)))/l);
        params = new LinearLayout.LayoutParams(cellsz,cellsz);
        params.setMargins(5,5,5,5);
        params.weight=1;
        //LayoutParams layoutParams = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
        Random rd = new Random();

        int R = rd.nextInt(256);
        int G = rd.nextInt(256);
        int B = rd.nextInt(256);
        float range=Integer.max((25-stg/5),8);
        int theta =myrd(-180,180);
        int alpha =myrd(-90,90);
        int dr=(int)(range*Math.sin(Math.toRadians(alpha)));
        int dg =(int)(range*Math.cos(Math.toRadians(alpha)*Math.sin(Math.toRadians(theta))));
        int db =(int)(range*Math.cos(Math.toRadians(alpha)*Math.cos(Math.toRadians(theta))));
        int nR=change(R,dr),nG=change(G,dg),nB=change(B,db);

        String bg = "#"+conv(R)+conv(G)+conv(B);
        String col = bg;
        col = "#"+conv(nR)+conv(nG)+conv(nB);
        //String col = "#"+Integer.toHexString((R+d)%256)+Integer.toHexString((G+d)%256)+Integer.toHexString((B+d)%256);
        for(int i=1;i<=l;i++){
            for(int j=1;j<=l;j++){
                Button v = new Button(getContext());
                v.setBackgroundColor(Color.parseColor(bg));
                if(i==x&&j==y){
                    v.setBackgroundColor(Color.parseColor(col));
                }
                v.setLayoutParams(params);
                int finali = i;
                int finalj = j;
                v.setOnClickListener(new View.OnClickListener() {
                    int vx= finali,vy=finalj;
                    @Override public void onClick(View v) {

                        if(vx==x&&vy==y){
                            parent.handletouch(true);
                        }
                        else{
                            parent.handletouch(false);
                        }

                    } });

                grid.addView(v);
            }
        }
        return rootview;
    }
}