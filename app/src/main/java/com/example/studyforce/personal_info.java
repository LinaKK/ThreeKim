package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class personal_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        //로그인 성공시 받아올 학번
        int userid3 = ((Login_Request)Login_Request.context_login).userid1;


        TextView uname = findViewById(R.id.uname);
        TextView uid = findViewById(R.id.uid);


    }
    void onButton_exit(View view){
        Intent intent = new Intent(this, personal_page.class);
        startActivity(intent);
    }
}