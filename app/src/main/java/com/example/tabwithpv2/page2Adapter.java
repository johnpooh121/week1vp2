package com.example.tabwithpv2;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class page2Adapter extends RecyclerView.Adapter<page2Adapter.ViewHolder> {

    private ArrayList<pic> pics = null ;
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
    public page2Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        this.parent=parent;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.pic_layout, parent, false) ;
        page2Adapter.ViewHolder vh = new page2Adapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(page2Adapter.ViewHolder holder, int position) {
        pic imgid = pics.get(position);
        if(imgid.getImage()!=0)holder.imageView1.setImageResource(imgid.getImage());
        else{
            Glide.with(parent.getContext()).load(imgid.uri).into(holder.imageView1);
            //holder.imageView1.setImageURI(imgid.getUri());
        }
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return pics.size() ;
    }


}