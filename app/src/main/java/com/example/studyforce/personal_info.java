package com.example.studyforce;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class personal_info extends AppCompatActivity {

    Button exit;
    TextView uname;
    TextView uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

        //로그인 성공시 받아올 학번
        //int userid3 = ((Login_Request)Login_Request.context_login).userid1;

        uname = findViewById(R.id.uname);
        uid = findViewById(R.id.uid);
        exit = (Button)findViewById(R.id.exit3);

        //메인으로 나가기
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), personal_page.class);
                startActivity(intent);
            }
        });
    }
}