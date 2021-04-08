package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ScheduleAdd extends AppCompatActivity {

    EditText scheduleTitle;
    EditText scheduleCon;
    Button add;
    ImageButton sDate;
    ImageButton eDate;
    TextView sDay;
    TextView eDay;

    private int state = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_add);

        scheduleTitle = (EditText)findViewById(R.id.scheduleTitle);
        scheduleCon = (EditText)findViewById(R.id.scheduleContents);
        add = (Button)findViewById(R.id.scheduleAdd);
        sDate = (ImageButton)findViewById(R.id.startDate);
        eDate = (ImageButton)findViewById(R.id.endDate);
        sDay = (TextView)findViewById(R.id.startDay);
        eDay = (TextView)findViewById(R.id.endDay);



        //시작달력 눌렀을 때
        sDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //끝달력 눌렸을때
       eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        //일정 추가 눌렀을때
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    //키보드
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }





}