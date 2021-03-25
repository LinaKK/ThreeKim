package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class Study_function extends AppCompatActivity {

    ImageView StudyState;
    ToggleButton TimerStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_function);


    }


    //타이머시작 버튼
    void timerStart(View view){
        StudyState = (ImageView)findViewById(R.id.study_state);
        TimerStart = (ToggleButton)findViewById(R.id.timer_start);
        if (TimerStart.isChecked()){
            StudyState.setImageResource(R.drawable.study_off);
        }else{
            StudyState.setImageResource(R.drawable.study_on);
        }
    }

    //타이머종료 버튼
    void timerFinish(View view){
        StudyState = (ImageView)findViewById(R.id.study_state);
        StudyState.setImageResource(R.drawable.study_off);
    }

    //타이머페이지 나가기 버튼튼
    void onButton_exit2(View view){
        Intent intent = new Intent(this, personal_page.class);
        startActivity(intent);
    }
}