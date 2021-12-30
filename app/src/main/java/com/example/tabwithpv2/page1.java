package com.example.tabwithpv2;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        myListView = (ListView) rootView.findViewById(R.id.contacts_lv);
        contactAdapter = new contactAdapter(getContext(),humanlist);
        myListView.setAdapter(contactAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //각 아이템을 분간 할 수 있는 position과 뷰
                Human_information human = humanlist.get(position);
//                int id = getContext().getResources().getIdentifier("ic_launcher_foreground", "drawable", getContext().getPackageName());
//                Toast.makeText(getContext(), id, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(),MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("imageId",human.getImage());
                bundle.putString("name",human.getName());
                bundle.putString("number",human.getNumber());
                intent.putExtras(bundle);


                startActivity(intent);
            }
        });

        return rootView;

    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("human_information.json");
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

}