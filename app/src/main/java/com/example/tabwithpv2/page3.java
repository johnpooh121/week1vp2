package com.example.tabwithpv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tabwithpv2.databinding.FragmentPage2Binding;
import com.example.tabwithpv2.databinding.FragmentPage3Binding;

import java.util.ArrayList;

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
    page3Adapter adapter;
    FragmentPage3Binding binding;
    int sz;
    public page3() {

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
        View rootview = inflater.inflate(R.layout.fragment_page3, container, false);
        RecyclerView recyclerView = rootview.findViewById(R.id.page3RecyclerViewId) ;
        float l= 80.0F;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            l = Float.min((float) recyclerView.getWidth(), (float) recyclerView.getHeight());
        }
        Toast.makeText(getContext(), "!!"+recyclerView.getWidth(), Toast.LENGTH_SHORT).show();
        ArrayList<cell> list = new ArrayList<>();
        sz=5;
        for (int i=0; i<sz*sz; i++) {
            list.add(new cell(i/sz,i%sz,l/(float)sz)) ;
        }
        // 리사이클러뷰에 LinearLayoutManager 객체 지정.

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),sz,GridLayoutManager.VERTICAL,false));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        adapter = new page3Adapter(list) ;
        recyclerView.setAdapter(adapter) ;

        binding = FragmentPage3Binding.inflate(getLayoutInflater());
        return rootview;
    }
}