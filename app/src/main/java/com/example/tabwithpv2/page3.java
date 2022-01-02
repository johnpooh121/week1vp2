package com.example.tabwithpv2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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

    Button start;
    FragmentPage3Binding binding;
    int stage = 0, life = 3;
    boolean gameStart= false;



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
        gameStart =false;

        SharedPreferences sharedPreferences;
        sharedPreferences = getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("life", life);
        editor.putInt("stage", stage);
        editor.putBoolean("gamestart", gameStart);
        editor.commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.page3_grid, container, false);
        gameStart = false;
        start = (Button) rootview.findViewById(R.id.page3button) ;
        binding = FragmentPage3Binding.inflate(getLayoutInflater());
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        life = sharedPref.getInt("life", 888);
        stage = sharedPref.getInt("stage", 888);
        gameStart = sharedPref.getBoolean("gamestart", false);
        SharedPreferences.Editor editor = sharedPref.edit();

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                if (stage < 10) {
                    Intent intent = new Intent(getActivity(), game4.class);
                    startActivity(intent);
                    gameStart = true;
//                }
            }
        });
        if(gameStart){
            if (stage < 20){
                Intent intent = new Intent(getActivity(), game9.class);
                startActivity(intent);
            }
            //        else if (stage < 30){
            //            Intent intent = new Intent(getActivity(), game16.class);
            //            startActivity(intent);
            //        }
        }

    }

    public static class game4 extends AppCompatActivity {
        int sz,randomnum,life,stage;
        int cur_stage;
        Button button1,button2,button3,button0;
        ToggleButton checkbutton;
        LinearLayout linearLayout;
        GridLayout gridlayout;
        TextView stagecnt,lifecnt;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_game4);

            button0 = (Button) findViewById(R.id.button2_1) ;
            button1 = (Button) findViewById(R.id.button2_2) ;
            button2 = (Button) findViewById(R.id.button2_3) ;
            button3 = (Button) findViewById(R.id.button2_4) ;

            checkbutton = (ToggleButton) findViewById(R.id.checkBox);
            gridlayout = (GridLayout) findViewById(R.id.grid);
            linearLayout = (LinearLayout) findViewById(R.id.linear);

            sz=2;

            Random random = new Random(System.currentTimeMillis());
            randomnum = random.nextInt(sz*sz);

            setcolor4(randomnum);

            stagecnt = (TextView) findViewById(R.id.stage4);
            lifecnt = (TextView) findViewById(R.id.life4);

            SharedPreferences sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE);
            life = sharedPref.getInt("life", 888);
            stage = sharedPref.getInt("stage", 888);
            cur_stage = 0;
            SharedPreferences.Editor editor = sharedPref.edit();


            (new Thread(new Runnable()
            {

                @Override
                public void run()
                {
                    while (!Thread.interrupted())
                        try
                        {
                            Thread.sleep(100);
                            runOnUiThread(new Runnable() // start actions in UI thread
                            {
                                @Override
                                public void run()
                                {
                                    stagecnt.setText(Integer.toString(stage));
                                    lifecnt.setText(Integer.toString(life));
                                    if (cur_stage != stage) {
                                        cur_stage = stage;
                                        Random random = new Random(System.currentTimeMillis());
                                        randomnum = random.nextInt(sz * sz);
                                        setcolor4(randomnum);
                                    }
                                    if (stage == 10){
                                        finish();
                                        editor.putBoolean("gamestart", true);
                                        editor.commit();
                                    }

                                    if(life==0){
                                        editor.putInt("life", 3);
                                        editor.putInt("stage", 0);
                                        editor.putBoolean("gamestart", false);
                                        editor.commit();
                                        finish();
                                    }


                                }
                            });
                        }
                        catch (InterruptedException e)
                        {
                            // ooops
                        }
                }
            })).start();



        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

        }

        void setcolor4(int randomnum){

            int color1 = R.color.black;
            int color2 = color1-10;
            if(randomnum == 0){
                button0.setBackgroundColor(getResources().getColor(color1));
            }
            else{
                button0.setBackgroundColor(getResources().getColor(color2));
            }
            if(randomnum == 1){
                button1.setBackgroundColor(getResources().getColor(color1));
            }
            else{
                button1.setBackgroundColor(getResources().getColor(color2));
            }
            if(randomnum == 2){
                button2.setBackgroundColor(getResources().getColor(color1));
            }
            else{
                button2.setBackgroundColor(getResources().getColor(color2));
            }
            if(randomnum == 3){
                button3.setBackgroundColor(getResources().getColor(color1));
            }
            else{
                button3.setBackgroundColor(getResources().getColor(color2));
            }
        }

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        protected void onResume() {
            super.onResume();

            SharedPreferences sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE);
            life = sharedPref.getInt("life", 888);
            stage = sharedPref.getInt("stage", 888);
            SharedPreferences.Editor editor = sharedPref.edit();

            button0 = (Button) findViewById(R.id.button2_1) ;
            button1 = (Button) findViewById(R.id.button2_2) ;
            button2 = (Button) findViewById(R.id.button2_3) ;
            button3 = (Button) findViewById(R.id.button2_4) ;

            checkbutton = (ToggleButton) findViewById(R.id.checkBox);
            gridlayout = (GridLayout) findViewById(R.id.grid);
            linearLayout = (LinearLayout) findViewById(R.id.linear);


            button0.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(randomnum == 0){
                        stage = stage + 1;
                    }
                    else{
                        life = life - 1;
                    }

                }
            });
            button1.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(randomnum == 1){
                        stage += 1;
                    }
                    else{
                        life -=1;
                    }
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(randomnum == 2){
                        stage += 1;
                    }
                    else{
                        life -=1;
                    }
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(randomnum == 3){
                        stage += 1;
                    }
                    else{
                        life -=1;
                    }
                }
            });
            editor.putInt("life", life);
            editor.putInt("stage", stage);
            editor.commit();
            //////////////////////////////////////////게임 종료 //////////////////////////////////



            //////////////////////// 토글 버튼으로 배경색 바꾸기/////////////////////////////////
            checkbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @SuppressLint("ResourceAsColor")
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        gridlayout.setBackgroundColor(getResources().getColor(R.color.black));
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.black));
                    } else {
                        gridlayout.setBackgroundColor(getResources().getColor(R.color.white));
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                }
            });
            //////////////////////// 토글 버튼으로 배경색 바꾸기/////////////////////////////////

        }
    }

    public static class game9 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game9);
        }
    }

    public static class game16 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game16);
        }
    }

//    public static class page3Adapter extends RecyclerView.Adapter<page3Adapter.ViewHolder> {
//
//        public ArrayList<cell> cells = null ;
//        ViewGroup parent;
//        ConstraintLayout outview;
//        public void addItem(cell nw){
//            cells.add(nw);
//        }
//
//        // 아이템 뷰를 저장하는 뷰홀더 클래스.
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            View view1 ;
//            ViewHolder(View itemView) {
//                super(itemView) ;
//
//                // 뷰 객체에 대한 참조. (hold strong reference)
//                view1 = itemView.findViewById(R.id.view) ;
//            }
//        }
//
//        // 생성자에서 데이터 리스트 객체를 전달받음.
//        page3Adapter(ArrayList<cell> list) {
//            cells = list ;
//        }
//
//        // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            Context context = parent.getContext() ;
//            this.parent=parent;
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
//
//            View view = inflater.inflate(R.layout.cell_layout, parent, false) ;
//            ViewHolder vh = new ViewHolder(view) ;
//
//            return vh ;
//        }
//
//        // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            cell Cell= cells.get(position);
//
//            outview.getLayoutParams().width = (int) (300.F / (float) Cell.sz) ;
//            outview.getLayoutParams().height = (int) (300.F / (float) Cell.sz) ;
//            holder.view1.getLayoutParams().width= (int) (300.F / (float) Cell.sz) - 10;
//            holder.view1.getLayoutParams().height= (int) (300.F / (float) Cell.sz) - 10;
//
//            holder.view1.setOnClickListener(new View.OnClickListener() {
//                @Override
//
//                public void onClick(View v) {
//                    int pos=position;
//                    Toast.makeText(parent.getContext(),""+position,Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//        // getItemCount() - 전체 데이터 갯수 리턴.
//        @Override
//        public int getItemCount() {
//            return cells.size() ;
//        }
//
//    }
}