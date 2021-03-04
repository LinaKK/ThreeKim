package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Login_Request extends AppCompatActivity {


    //값을 넘겨줄 때
    public static Context context_login;
    public int userid1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__request);

        context_login = this;
    }
}