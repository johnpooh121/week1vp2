package com.example.tabwithpv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//public class JSONTest{
//
//    class User {
//        int id;
//        String name;
//        String pwd;
//    }
//
//    @Test
//    public void parsing() throws Exception {
//        ArrayList<User> userList = new ArrayList<>();
//        User user1 = new User();
//        user1.id = R.drawable.chaeyoung;
//        user1.name = "김삿갓";
//        user1.pwd = "111";
//
//        userList.add(user1);
//
//        // JSON 으로 변환
//        try {
//            JSONArray jArray = new JSONArray();//배열이 필요할때
//            for (int i = 0; i < userList.size(); i++) {
//                JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
//                sObject.put("id", userList.get(i).id);
//                sObject.put("name", userList.get(i).name);
//                sObject.put("pwd", userList.get(i).pwd);
//                jArray.put(sObject);
//            }
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//}

public class human_add extends AppCompatActivity {
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