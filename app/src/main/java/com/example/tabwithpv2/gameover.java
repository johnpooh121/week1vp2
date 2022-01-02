package com.example.tabwithpv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link gameover#newInstance} factory method to
 * create an instance of this fragment.
 */
public class gameover extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int stg;
    page3 parent;
    public gameover(int stg,page3 par) {
        this.stg=stg;
        this.parent=par;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment gameover.
     */
    // TODO: Rename and change types and number of parameters
    public static gameover newInstance(String param1, String param2) {
        gameover fragment = new gameover(0,null);
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
        View rootview =  inflater.inflate(R.layout.fragment_gameover, container, false);
        TextView tv = rootview.findViewById(R.id.txtid);
        tv.setText(stg+"단계에서 종료되었습니다.");
        Button btn = rootview.findViewById(R.id.restart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                parent.pressRestart();

            } });
        return rootview;
    }
}