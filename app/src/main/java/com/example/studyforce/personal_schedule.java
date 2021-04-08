package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class personal_schedule extends AppCompatActivity {

    TextView date;
    long Now;
    Date Date;
    SimpleDateFormat cDate = new SimpleDateFormat(" \u003Cyyyy년 MM월 dd일\u003E ");
    String getTime;

    private ListView listView1;  //리스트뷰
    private ScheduleAdapter adapter; //리스트뷰어댑터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_schedule);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

        MaterialCalendarView materialCalendarView = findViewById(R.id.calendar);
        materialCalendarView.setSelectedDate(CalendarDay.today());
        materialCalendarView.addDecorators(
                new SundayDecorator(), new SaturdayDecorator());

        date = (TextView)findViewById(R.id.date);
        Now = System.currentTimeMillis();
        Date = new Date(Now);
        getTime = cDate.format(Date);
        date.setText(getTime);

        //어댑터 생성
        adapter = new ScheduleAdapter();

        listView1= (ListView)findViewById(R.id.today_todo);
        listView1.setAdapter(adapter);

        adapter.addItem("과제1","2021/04/09","2021/04/21","알고리즘 과제 빨리 끝내기");
        adapter.notifyDataSetChanged();


        //날짜 선택 시 팝업창 띄우기
        //materialCalendarView.setSelectedDate();


    }

    //일정 추가이벤트
    public void date_add(View view) {
        Intent intent = new Intent(this, ScheduleAdd.class);
        startActivity(intent);
    }


}




/*final TextView date = findViewById(R.id.date);
        CalendarView calendarView = findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                month +=1;
                date.setText(String.format("%d년 %d월 %d일", year, month, day));
            }
        });*/