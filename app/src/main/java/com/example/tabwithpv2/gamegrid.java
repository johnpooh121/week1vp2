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
    int l=10,x=2,y=2;
    public gamegrid() {
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
        gamegrid fragment = new gamegrid();
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
        View rootview = inflater.inflate(R.layout.fragment_gamegrid, container, false);
        GridLayout grid = rootview.findViewById(R.id.grid);
        if(grid==null) Toast.makeText(getContext(),"shit",Toast.LENGTH_SHORT).show();
        grid.setColumnCount(l);
        grid.setRowCount(l);
        Point pt = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getRealSize(pt);

        LinearLayout.LayoutParams params;

        int sz = (int)(((float)pt.x)*0.9);
        int cellsz =(int)(((float)(sz-10*(l+1)))/l);
        params = new LinearLayout.LayoutParams(cellsz,cellsz);
        params.setMargins(5,5,5,5);
        params.weight=1;
        Toast.makeText(getContext(),""+grid.getMeasuredWidth(),Toast.LENGTH_SHORT).show();
        //LayoutParams layoutParams = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
        for(int i=1;i<=l;i++){
            for(int j=1;j<=l;j++){
                Button v = new Button(getContext());
                v.setBackgroundColor(Color.parseColor("#00ff00"));
                if(i==x&&j==y){
                    v.setBackgroundColor(Color.parseColor("#000000"));
                }
                v.setLayoutParams(params);

                grid.addView(v);
            }
        }
        return rootview;
    }
}