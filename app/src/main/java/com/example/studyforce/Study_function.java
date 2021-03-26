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
    Button TimerFinish;
    Button Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_function);
        StudyState = (ImageView)findViewById(R.id.study_state);
        TimerStart = (ToggleButton)findViewById(R.id.timer_start);
        TimerFinish = (Button)findViewById(R.id.timer_finish);
        Exit = (Button)findViewById(R.id.exit2);

        //타이머 시작
        TimerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudyState.setImageResource(R.drawable.study_on);
                if (TimerStart.isChecked()){
                    StudyState.setImageResource(R.drawable.study_on);
                }else{
                    StudyState.setImageResource(R.drawable.study_off);
                }
            }
        });

        //타이머 종료
        TimerFinish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StudyState = (ImageView)findViewById(R.id.study_state);
                StudyState.setImageResource(R.drawable.study_off);
            }
        });

        //페이지 나가기
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), personal_page.class);
                startActivity(intent);
            }
        });




    }

}