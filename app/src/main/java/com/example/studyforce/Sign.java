package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import android.os.Bundle;

public class Sign extends AppCompatActivity {

    private EditText si_n, si_id, si_pw, si_pwc;
    private Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        //값찾기
        si_n=findViewById(R.id.si_n);
        si_id=findViewById(R.id.si_id);
        si_pw=findViewById(R.id.si_pw);
        si_pwc=findViewById(R.id.si_pwc);

        //연동 후에 중복체크 기능 넣기 user=si_n @Override
        //회원가입 누를 시에 연동기능 넣기 (override 입력안하면 back)

    }
}