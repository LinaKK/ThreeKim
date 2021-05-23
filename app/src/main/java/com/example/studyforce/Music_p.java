package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Music_p extends AppCompatActivity {
    Button m_stop;
    ImageView m_1,m_2,m_3,m_4;
    private static MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_p);

        m_stop=(Button)findViewById(R.id.m_stop);
        m_1=(ImageView)findViewById(R.id.m_1);
        //m_2=(ImageView)findViewById(R.id.m_2);
        m_3=(ImageView)findViewById(R.id.m_3);
        m_4=(ImageView)findViewById(R.id.m_4);

        //멈춤
        m_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent =new Intent (getApplicationContext(), m_service.class);
               stopService(intent);
            }
        });

        //빗소리
        m_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(),m_service.class));
            }
        });


        //풀벌레
        m_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(),m_night.class));
            }
        });
/*
        //시냇물
        m_3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(),m_service.class));
            }
        });
*/

        /*//실험용
        m_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(),m_service.class));
            }
        });*/

        }

    };