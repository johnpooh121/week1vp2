package com.example.tabwithpv2;

import static android.app.Activity.RESULT_OK;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabwithpv2.databinding.FragmentPage2Binding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link page2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class page2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    final int GET_GALLERY_IMAGE = 200;
    page2Adapter adapter;
    FragmentPage2Binding binding;

    public page2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment page2.
     */
    // TODO: Rename and change types and number of parameters
    public static page2 newInstance(String param1, String param2) {
        page2 fragment = new page2();
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
        View rootview = inflater.inflate(R.layout.fragment_page2, container, false);
        ArrayList<pic> list = new ArrayList<>();

        list.add(new pic(R.drawable.tzuyu,null)) ;
        list.add(new pic(R.drawable.dahyun,null)) ;
        list.add(new pic(R.drawable.chaeyoung,null)) ;
        list.add(new pic(R.drawable.sana,null)) ;
        list.add(new pic(R.drawable.nayeon,null)) ;
        list.add(new pic(R.drawable.momo,null)) ;
        list.add(new pic(R.drawable.mina,null)) ;
        list.add(new pic(R.drawable.jeongyeon,null)) ;
        list.add(new pic(R.drawable.jihyo,null)) ;
//        for (int i=0; i<8; i++) {
//            list.add(new pic(R.drawable.ic_launcher_foreground,null)) ;
//        }

        // ????????????????????? LinearLayoutManager ?????? ??????.
        RecyclerView recyclerView = rootview.findViewById(R.id.page2RecyclerViewId) ;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));

        // ????????????????????? SimpleTextAdapter ?????? ??????.
        adapter = new page2Adapter(list) ;
        recyclerView.setAdapter(adapter) ;

        binding = FragmentPage2Binding.inflate(getLayoutInflater());
        //FloatingActionButton fab = binding.page2FloatingActionButton;
        FloatingActionButton fab = rootview.findViewById(R.id.page2FloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                startActivityForResult(intent, GET_GALLERY_IMAGE);
                //Toast.makeText(getContext(), getContext().getPackageName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.ad

                intent.setType("image/*");
                //intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2222);
            }
        });


        return rootview;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

//        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//            Uri selectedImageUri = data.getData();
//            adapter.addItem(new pic(0,selectedImageUri));
//            Toast.makeText(getContext(), ""+adapter.getItemCount(), Toast.LENGTH_LONG).show();
//            adapter.notifyDataSetChanged();
//
//        }
//        else{
//            Toast.makeText(getContext(), "load failed!", Toast.LENGTH_LONG).show();
//        }
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null){   // ?????? ???????????? ???????????? ?????? ??????
            Toast.makeText(getContext(), "???????????? ???????????? ???????????????.", Toast.LENGTH_LONG).show();
        }
        else{   // ???????????? ???????????? ????????? ??????
            if(data.getClipData() == null){     // ???????????? ????????? ????????? ??????
                Log.e("single choice: ", String.valueOf(data.getData()));
                Uri imguri = data.getData();
                adapter.addItem(new pic(0,imguri));
                adapter.notifyDataSetChanged();
            }
            else{      // ???????????? ????????? ????????? ??????
                ClipData clipData = data.getClipData();
                Log.e("clipData", String.valueOf(clipData.getItemCount()));

                if(clipData.getItemCount() > 10){   // ????????? ???????????? 11??? ????????? ??????
                    Toast.makeText(getContext(), "????????? 10????????? ?????? ???????????????.", Toast.LENGTH_LONG).show();
                }
                else{   // ????????? ???????????? 1??? ?????? 10??? ????????? ??????

                    for (int i = 0; i < clipData.getItemCount(); i++){
                        Uri imguri = clipData.getItemAt(i).getUri();  // ????????? ??????????????? uri??? ????????????.
                        try {
                            adapter.addItem(new pic(0,imguri));  //uri??? list??? ?????????.

                        } catch (Exception e) {
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }

    }
}