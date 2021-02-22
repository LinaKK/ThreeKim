package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class personal_schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_schedule);


        final TextView date = findViewById(R.id.date);
        CalendarView calendarView = findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                month +=1;
                date.setText(String.format("%d년 %d월 %d일", year, month, day));
            }
        });


    }

    //일정 추가이벤트
    public void date_add(View view) {

    }
}