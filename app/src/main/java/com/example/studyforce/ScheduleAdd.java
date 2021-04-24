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
import android.widget.Toast;

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
    TextView sDay_Y;
    TextView sDay_M;
    TextView sDay_D;
    TextView eDay_Y;
    TextView eDay_M;
    TextView eDay_D;

    public String title;
    public String content;
    //public int startD;
    //public String endD;
    public String startY;
    public String startM;
    public String startD;
    public String endY;
    public String endM;
    public String endD;

    //시작날짜용
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            update();
            updateM();
            updateD();

        }
    };

    //종료날짜용
    Calendar calendar2 = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener myDatePicker2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar2.set(Calendar.YEAR, year);
            calendar2.set(Calendar.MONTH, month);
            calendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            update2();
            update2M();
            update2D();

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
        sDay_Y = (TextView)findViewById(R.id.startDay);
        sDay_M =(TextView)findViewById(R.id.startDay_month);
        sDay_D =(TextView)findViewById(R.id.startDay_day);
        eDay_Y = (TextView)findViewById(R.id.endDay);
        eDay_M = (TextView)findViewById(R.id.endDay_month);
        eDay_D = (TextView)findViewById(R.id.endDay_day);



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
                //string -> int로 변경 예정
                startY = sDay_Y.getText().toString();
                startM = sDay_M.getText().toString();
                startD = sDay_D.getText().toString();
                endY = eDay_Y.getText().toString();
                endM = eDay_M.getText().toString();
                endD = eDay_D.getText().toString();

                //값 잘 가져오는지 확인용
                 Toast.makeText(getApplicationContext(), startY+startM+startD, Toast.LENGTH_SHORT).show();
                //값 php로 보내기
                /*
                //num(행번호), id(학번), s_day, s_month, s_year, e_day, e_month, e_year, title, cont
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id2 = jsonObject.getInt("id");
                int sDay = jsonObject.getInt("s_day");
                int sMonth = jsonObject.getInt("s_month");
                int sYear = jsonObject.getInt("s_year");
                int eDay = jsonObject.getInt("e_day");
                int eMonth = jsonObject.getInt("e_month");
                int eYear = jsonObject.getInt("e_year");
                String schTitle = jsonObject.getString("title");
                String schCont = jsonObject.getString("cont");
                */
            }
        });

    }

    //키보드
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    //날짜 입력
    void update(){
        String myFormat = " yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        sDay_Y = (TextView)findViewById(R.id.startDay);
        sDay_Y.setText(sdf.format(calendar.getTime()));
    }
    void updateM(){
        String myFormat = " MM";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        sDay_M =(TextView)findViewById(R.id.startDay_month);
        sDay_M.setText(sdf.format(calendar.getTime()));
    }
    void updateD(){
        String myFormat = " dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        sDay_D = (TextView)findViewById(R.id.startDay_day);
        sDay_D.setText(sdf.format(calendar.getTime()));
    }

    void update2(){
        String myFormat = " yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat, Locale.KOREA);
        eDay_Y = (TextView)findViewById(R.id.endDay);
        eDay_Y.setText(sdf2.format(calendar2.getTime()));
    }
    void update2M(){
        String myFormat = " MM";
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat, Locale.KOREA);
        eDay_M = (TextView)findViewById(R.id.endDay_month);
        eDay_M.setText(sdf2.format(calendar2.getTime()));
    }
    void update2D(){
        String myFormat = " dd";
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat, Locale.KOREA);
        eDay_D = (TextView)findViewById(R.id.endDay_day);
        eDay_D.setText(sdf2.format(calendar2.getTime()));
    }


}