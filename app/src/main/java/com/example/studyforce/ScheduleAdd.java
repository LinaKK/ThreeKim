package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ScheduleAdd extends AppCompatActivity {

    EditText scheduleTitle;
    EditText scheduleCon;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_add);

        scheduleTitle = (EditText)findViewById(R.id.scheduleTitle);
        scheduleCon = (EditText)findViewById(R.id.scheduleContents);
        add = (Button)findViewById(R.id.scheduleAdd);

        //일정 추가 눌렀을때
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }



}