package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class personal_schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_schedule);



        MaterialCalendarView materialCalendarView = findViewById(R.id.calendar);
        materialCalendarView.setSelectedDate(CalendarDay.today());
        materialCalendarView.addDecorators(
                new SundayDecorator(), new SaturdayDecorator());

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
        Intent intent = new Intent(this, ScheduleAdd.class);
        startActivity(intent);
    }
}