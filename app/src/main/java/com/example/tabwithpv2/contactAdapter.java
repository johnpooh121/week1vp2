package com.example.tabwithpv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class contactAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Human_information> sample;

    public contactAdapter(Context context, ArrayList<Human_information> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Human_information getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.contact_component, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.face);
        TextView movieName = (TextView)view.findViewById(R.id.name);
        TextView grade = (TextView)view.findViewById(R.id.phoneNumber);

//        imageView.setImageResource(sample.get(position).getImage());
        Glide.with(parent.getContext()).load(sample.get(position).getImage()).into(imageView);
        movieName.setText(sample.get(position).getName());
        grade.setText(sample.get(position).getNumber());

        return view;
    }
}
