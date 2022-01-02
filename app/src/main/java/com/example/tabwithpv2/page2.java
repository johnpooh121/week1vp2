package com.example.tabwithpv2;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tabwithpv2.databinding.FragmentPage2Binding;
import com.github.chrisbanes.photoview.PhotoView;
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

//        for (int i=0; i<8; i++) {
//            list.add(new pic(R.drawable.ic_launcher_foreground,null)) ;
//        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = rootview.findViewById(R.id.page2RecyclerViewId) ;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
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

        if(data == null){   // 어떤 이미지도 선택하지 않은 경우
            Toast.makeText(getContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
        }
        else{   // 이미지를 하나라도 선택한 경우
            if(data.getClipData() == null){     // 이미지를 하나만 선택한 경우
                Log.e("single choice: ", String.valueOf(data.getData()));
                Uri imguri = data.getData();
                adapter.addItem(new pic(0,imguri));
                adapter.notifyDataSetChanged();
            }
            else{      // 이미지를 여러장 선택한 경우
                ClipData clipData = data.getClipData();
                Log.e("clipData", String.valueOf(clipData.getItemCount()));

                if(clipData.getItemCount() > 10){   // 선택한 이미지가 11장 이상인 경우
                    Toast.makeText(getContext(), "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_LONG).show();
                }
                else{   // 선택한 이미지가 1장 이상 10장 이하인 경우

                    for (int i = 0; i < clipData.getItemCount(); i++){
                        Uri imguri = clipData.getItemAt(i).getUri();  // 선택한 이미지들의 uri를 가져온다.
                        try {
                            adapter.addItem(new pic(0,imguri));  //uri를 list에 담는다.

                        } catch (Exception e) {
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }

    }

    public static class page2Adapter extends RecyclerView.Adapter<page2Adapter.ViewHolder> {

        public ArrayList<pic> pics = null ;
        ViewGroup parent;
        public void addItem(pic nw){
            pics.add(nw);
        }

        // 아이템 뷰를 저장하는 뷰홀더 클래스.
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView1 ;

            ViewHolder(View itemView) {
                super(itemView) ;

                // 뷰 객체에 대한 참조. (hold strong reference)
                imageView1 = itemView.findViewById(R.id.imageView) ;
            }
        }

        // 생성자에서 데이터 리스트 객체를 전달받음.
        page2Adapter(ArrayList<pic> list) {
            pics = list ;
        }

        // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext() ;
            this.parent=parent;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

            View view = inflater.inflate(R.layout.pic_layout, parent, false) ;
            ViewHolder vh = new ViewHolder(view) ;

            return vh ;
        }

        // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            pic imgid = pics.get(position);
            if(imgid.getImage()!=0)holder.imageView1.setImageResource(imgid.getImage());
            else{
                Glide.with(parent.getContext()).load(imgid.uri).into(holder.imageView1);
                //holder.imageView1.setImageURI(imgid.getUri());
            }
            holder.imageView1.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    Toast.makeText(parent.getContext(),""+imgid.getImage(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(parent.getContext(),picFocus.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundle = new Bundle();
                    bundle.putInt("img",imgid.image);
                    bundle.putString("uri",imgid.uri.toString());
                    intent.putExtras(bundle);
                    parent.getContext().getApplicationContext().startActivity(intent);
                }
            });
        }

        // getItemCount() - 전체 데이터 갯수 리턴.
        @Override
        public int getItemCount() {
            return pics.size() ;
        }


    }

    public static class pic {
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

    public static class picFocus extends AppCompatActivity {

        public PhotoView imgView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pic_focus);
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();

            int img = bundle.getInt("img");
            Uri uri = Uri.parse(bundle.getString("uri"));
            imgView = findViewById(R.id.focusViewer);
            if(img!=0)imgView.setImageResource(img);
            else{
                Glide.with(this).load(uri).into(imgView);
                //holder.imageView1.setImageURI(imgid.getUri());
            }
        }
    }
}