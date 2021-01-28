package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.studyforce.R;

public class Login extends AppCompatActivity {
    EditText et_id,et_pw;
    String aId,aPw;
    Button btn_lo,btn_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        et_id=findViewById(R.id.et_id);
        et_pw=findViewById(R.id.et_pw);
        btn_lo=findViewById(R.id.btn_lo);
        btn_sign=findViewById(R.id.btn1_sign);

    }
    public void btn_lo(View view)
    {

    }

}