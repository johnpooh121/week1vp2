package com.example.tabwithpv2;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
        humanlist.add(new Human_information(R.drawable.ic_launcher_foreground,"가나다", "80111111"));
        humanlist.add(new Human_information(R.drawable.ic_launcher_foreground,"나다라", "90111111"));
        humanlist.add(new Human_information(R.drawable.ic_launcher_foreground,"다라마", "70111111"));
        humanlist.add(new Human_information(R.drawable.ic_launcher_foreground,"라마바", "60111111"));
        humanlist.add(new Human_information(R.drawable.ic_launcher_foreground,"마바사", "50111111"));

        myListView = (ListView) rootView.findViewById(R.id.contacts_lv);
        contactAdapter = new contactAdapter(getContext(),humanlist);
        myListView.setAdapter(contactAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //각 아이템을 분간 할 수 있는 position과 뷰
                Toast.makeText(getContext(), contactAdapter.getItem(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
//        return inflater.inflate(R.layout.fragment_page1, container, false);
        return rootView;
    }

}