package com.example.tabwithpv2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class page3Adapter extends RecyclerView.Adapter<page3Adapter.ViewHolder> {

    public ArrayList<cell> cells = null ;
    ViewGroup parent;
    public void addItem(cell nw){
        cells.add(nw);
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        View view1 ;
        Layout ly;
        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            view1 = itemView.findViewById(R.id.view) ;
            //ly = itemView.findViewById(R.id.outerLayout);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    page3Adapter(ArrayList<cell> list) {
        cells = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public page3Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        this.parent=parent;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.cell_layout, parent, false) ;
        page3Adapter.ViewHolder vh = new page3Adapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(page3Adapter.ViewHolder holder, int position) {
        cell Cell= cells.get(position);
        int w = holder.view1.getWidth();
        holder.view1.getLayoutParams().width= (int)((float)Cell.l*0.9);
        holder.view1.getLayoutParams().height= (int)((float)Cell.l*0.8);
        //Toast.makeText(parent.getContext(),""+Cell.l,Toast.LENGTH_SHORT).show();
        //holder.view1.getLayoutParams().width=10;
        //holder.view1.getLayoutParams().height=10;
        holder.view1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                int pos=position;
                Toast.makeText(parent.getContext(),""+pos,Toast.LENGTH_SHORT).show();
            }
        });
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return cells.size() ;
    }
}