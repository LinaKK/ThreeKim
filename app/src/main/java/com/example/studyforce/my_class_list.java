package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class my_class_list extends AppCompatActivity {
    Button exit;
    Button plus_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class_list);
        exit=findViewById(R.id.exit);
        plus_class=findViewById(R.id.plus_class);

        /*
        본인 클래스 리스트 db에서 받아오기
        (클래스name, 클래스공개(or 비공개))
        => 누르면 클래스 내부 페이지로 이동

         */

        //나가기 버튼
        exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(my_class_list.this, personal_page.class);
                startActivity(intent);
            }
        });

        //클래스 추가버튼
        plus_class.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (my_class_list.this, whole_class_list.class);
                startActivity(intent);
            }
        });

        };
    }
