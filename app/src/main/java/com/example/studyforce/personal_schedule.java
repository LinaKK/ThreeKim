package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.DriverManager.println;

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
        //리스트뷰에 어댑터 삽입
        listView1= (ListView)findViewById(R.id.today_todo);
        listView1.setAdapter(adapter);

        //sendRequest();

        //일정 예시
        adapter.addItem("과제1","2021/04/09","2021/04/21","알고리즘 과제 빨리 끝내기");
        adapter.notifyDataSetChanged();


        //날짜 선택 시 팝업창 띄우기
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                AlertDialog.Builder ad = new AlertDialog.Builder(personal_schedule.this);
                ad.setTitle(date.getYear()+"/"+(date.getMonth()+1)+"/"+date.getDay());
                //setCont에서 날짜 비교해서 일치하는 날짜의 일정만 출력
                String cont = setCont(date.getYear(), (date.getMonth()+1),date.getDay());
                ad.setMessage("그 날의 할일");

                ad.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();

            }
        });

    }
    //일정 db에서 가져오기(시작날짜, 종료날짜, 제목, 내용)


    //일정 추가이벤트
    public void date_add(View view) {
        Intent intent = new Intent(this, ScheduleAdd.class);
        startActivity(intent);
    }

    //날짜알림창에 넣을 내용
    String setCont(int year, int month, int day){

        return null;
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