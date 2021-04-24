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
    TextView umail;
    String username;
    String usermail;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

        //로그인 성공시 받아올 학번
        //int userid3 = ((Login_Request)Login_Request.context_login).userid1;
        /*
         username = jsonObject.getString("name"); //'pinfo'테이블
         usermail = jsonObject.getString("email"); //'pinfo'테이블
         userid = jsonObject.getint("num"); //'pinfo'테이블
         */

        uname = findViewById(R.id.uname);
        uid = findViewById(R.id.uid);
        umail = findViewById(R.id.umail);
        exit = (Button)findViewById(R.id.exit3);

        uname.setText(username);
        uid.setText(userid);
        umail.setText(usermail);


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