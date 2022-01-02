package com.example.tabwithpv2;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link page1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class page1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<String> personNames = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    ArrayList<String> mobileNumbers = new ArrayList<>();
    ArrayList<Human_information> humanlist;
    ListView myListView;
    private static contactAdapter contactAdapter;

    public page1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment page1.
     */
    // TODO: Rename and change types and number of parameters
    public static page1 newInstance(String param1, String param2) {
        page1 fragment = new page1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { // Fragment가 생성될때 호출되는 부분
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, //  onCreate 후에 화면을 구성할때 호출되는 부분
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_page1, container, false);

        humanlist = new ArrayList<>();

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            // fetch JSONArray named users
            JSONArray userArray = obj.getJSONArray("human");
            // implement for loop for getting users list data
            for (int i = 0; i < userArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject userDetail = userArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
                String face1 = userDetail.getString("face");
                String name = userDetail.getString("name");
                String phoneNumber = userDetail.getString("phoneNumber");
                humanlist.add(new Human_information(this.getResources().getIdentifier(face1, "drawable", getContext().getPackageName()), name, phoneNumber));
//                humanlist.add(new Human_information(R.drawable.ic_launcher_foreground, name, phoneNumber));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        myListView = (ListView) rootView.findViewById(R.id.contacts_lv);
        contactAdapter = new contactAdapter(getContext(),humanlist);
        myListView.setAdapter(contactAdapter);
        FloatingActionButton fab = rootView.findViewById(R.id.page1FloatingActionButton);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //각 아이템을 분간 할 수 있는 position과 뷰
                Human_information human = humanlist.get(position);
//                int id = getContext().getResources().getIdentifier("ic_launcher_foreground", "drawable", getContext().getPackageName());
//                Toast.makeText(getContext(), id, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), human_detail.class);
                Bundle bundle = new Bundle();
                bundle.putInt("imageId",human.getImage());
                bundle.putString("name",human.getName());
                bundle.putString("number",human.getNumber());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),human_add.class);

                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });

        return rootView;

    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("human_information.json");
//            FileInputStream is1 = getActivity().openFileInput("myFile.json");
//            InputStream is = getActivity().openFileInput("myFile.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static class human_add extends AppCompatActivity {
        Button button;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_human_add);
            Button button = (Button) findViewById(R.id.addButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText editName = (EditText)findViewById(R.id.addName);
                    EditText editNumber = (EditText)findViewById(R.id.addNumber);
                    if (editName.getText().toString().length() == 0 ) {
                        Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        if (editNumber.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "둘 다 입력해도 안되지롱", Toast.LENGTH_SHORT).show();
    //                        try{
    //                            FileOutputStream fos = openFileOutput("myFile.json",MODE_PRIVATE);
    //                            DataOutputStream dos = new DataOutputStream(fos);
    //                            dos.writeInt(100);
    //                            dos.writeUTF("문자열");
    //                            dos.flush();
    //                            dos.close();
    //                        } catch (IOException e) {
    //                            Toast.makeText(getApplicationContext(), "catch 입력해주세요", Toast.LENGTH_SHORT).show();
    //                            e.printStackTrace();
    //                        }
                        }
                    }

                }
            });
        }

    }

    public static class human_detail extends AppCompatActivity {

        public ImageView face;
        public TextView name1;
        public TextView number1;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.human_detail);

            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();

            int img = bundle.getInt("imageId");
            String name = bundle.getString("name");
            String number = bundle.getString("number");

            face = findViewById(R.id.faceInDetail);
            name1 = findViewById(R.id.nameDetail);
            number1 = findViewById(R.id.numberDetail);

            face.setImageResource(img);
            name1.setText(name);
            number1.setText(number);

        }
    }

    public static class Human_information {
        private int image;
        private String name;
        private String phoneNumber;

        public Human_information(int image, String name, String phoneNumber){
            this.image = image;
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public int getImage()
        {
            return this.image;
        }

        public String getName()
        {
            return this.name;
        }

        public String getNumber()
        {
            return this.phoneNumber;
        }
    }

    public static class contactAdapter extends BaseAdapter {
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
}