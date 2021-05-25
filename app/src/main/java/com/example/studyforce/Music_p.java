package com.example.studyforce;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Music_p extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView mrain, mjazz, mpiano, mfire, msound;
    Button mstop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_p);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

        mrain=(ImageView)findViewById(R.id.mrain);
        mjazz=(ImageView)findViewById(R.id.mjazz);
        mpiano=(ImageView)findViewById(R.id.mpiano);
        mfire=(ImageView)findViewById(R.id.mfire);
        msound=(ImageView)findViewById(R.id.msound);
        mstop=findViewById(R.id.mstop);

        mrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer =MediaPlayer.create(Music_p.this, R.raw.rain);
                mediaPlayer.start();
            }
        });
        mjazz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer =MediaPlayer.create(Music_p.this, R.raw.Downpour);
                mediaPlayer.start();
            }
        });
        mpiano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer =MediaPlayer.create(Music_p.this, R.raw.Pianom);
                mediaPlayer.start();
            }
        });
        mfire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer =MediaPlayer.create(Music_p.this, R.raw.bonfire);
                mediaPlayer.start();
            }
        });
        msound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer =MediaPlayer.create(Music_p.this, R.raw.s_river);
                mediaPlayer.start();
            }
        });

        mstop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
        });

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //플레이어 해지
        if(mediaPlayer !=null){
            mediaPlayer.release();
            mediaPlayer =null;
        }
    }
}