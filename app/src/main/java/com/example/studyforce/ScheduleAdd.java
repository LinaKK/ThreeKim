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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ScheduleAdd extends AppCompatActivity {

    EditText scheduleTitle;
    EditText scheduleCon;
    Button add;
    ImageButton sDate;
    ImageButton eDate;
    TextView sDay;
    TextView eDay;

    public String title;
    public String content;
    public String startD;
    public String endD;

    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            update();
        }
    };

    Calendar calendar2 = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener myDatePicker2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar2.set(Calendar.YEAR, year);
            calendar2.set(Calendar.MONTH, month);
            calendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            update2();
        }
    };



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
                new DatePickerDialog(ScheduleAdd.this, myDatePicker, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //끝달력 눌렸을때
       eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ScheduleAdd.this, myDatePicker2, calendar2.get(Calendar.YEAR),
                        calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //일정 추가 눌렀을때
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //값 가져오기
                title = scheduleTitle.getText().toString();
                content = scheduleCon.getText().toString();
                startD = sDay.getText().toString();
                endD = eDay.getText().toString();

                //값 php로 보내기


            }
        });

    }

    //키보드
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    void update(){
        String myFormat = " yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        sDay = (TextView)findViewById(R.id.startDay);
        sDay.setText(sdf.format(calendar.getTime()));
    }

    void update2(){
        String myFormat = " yyyy/MM/dd";
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat, Locale.KOREA);
        eDay = (TextView)findViewById(R.id.endDay);
        eDay.setText(sdf2.format(calendar2.getTime()));
    }



}