package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Study_function extends AppCompatActivity {

    ImageView StudyState;
    Button TimerStart;
    Button TimerFinish;
    TextView studytimer_time;
    BottomNavigationView bottomNavigationView;

    final static int IDLE = 0;
    final static int RUNNING = 1;
    final static int PAUSE = 2;
    int mStatus = IDLE;//처음 상태는 IDLE
    long mBaseTime;
    long mPauseTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_function);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        StudyState = (ImageView)findViewById(R.id.study_state);
        TimerStart = (Button)findViewById(R.id.timer_start);
        TimerFinish = (Button)findViewById(R.id.timer_finish);
        studytimer_time =(TextView)findViewById(R.id.studytimer_time);


        //bottomnavigation bar button설정
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.timerpage:
                        Intent intent = new Intent(getApplicationContext(), Study_function.class);
                        startActivity(intent);
                        return true;
                    case R.id.timeresult:
                        intent = new Intent(getApplicationContext(), Study_time_graph.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

    }

    //뒤로 가기 한번 시 personal_page로 이동
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg){
            studytimer_time.setText(getTime());
            handler.sendEmptyMessage(0);
        };
    };

    protected  void OnDestroy(){
        handler.removeMessages(0);
        super.onDestroy();
    }

    public void Timer(View v){
        switch(v.getId()){
            case R.id.timer_start:
                switch(mStatus){
                    case IDLE:
                        mBaseTime = SystemClock.elapsedRealtime();
                        handler.sendEmptyMessage(0);
                        TimerStart.setText("일시정지");
                        mStatus =RUNNING;
                        StudyState.setImageResource(R.drawable.study_on);
                        break;

                    case RUNNING:
                        //핸들러 메시지를 없애고
                        handler.removeMessages(0);
                        mPauseTime = SystemClock.elapsedRealtime();
                        TimerStart.setText("시작");
                        mStatus = PAUSE;//상태를 멈춤으로 표시
                        StudyState.setImageResource(R.drawable.study_off);
                        break;

                    case PAUSE:
                        long now = SystemClock.elapsedRealtime();
                        mBaseTime += (now - mPauseTime);
                        handler.sendEmptyMessage(0);
                        TimerStart.setText("일시정지");
                        mStatus = RUNNING;
                        StudyState.setImageResource(R.drawable.study_on);
                        break;
                }
                break;

            case R.id.timer_finish:
                switch(mStatus){
                    case RUNNING:
                        //기존의 값을 가져온뒤 이어붙이기 위해서
                        String sSplit = studytimer_time.getText().toString();
                        //텍스트뷰의 값을 바꿔줌
                        studytimer_time.setText(sSplit);
                        StudyState.setImageResource(R.drawable.study_off);
                        handler.removeMessages(0);
                        TimerStart.setText("시작");
                        studytimer_time.setText("00:00:00");
                        mStatus = IDLE;

                        break;

                    case PAUSE://여기서는 초기화버튼이 됨
                        //핸들러를 없애고
                        handler.removeMessages(0);
                        //처음상태로 원상복귀시킴
                        TimerStart.setText("시작");
                        studytimer_time.setText("00:00:00");
                        mStatus = IDLE;
                        StudyState.setImageResource(R.drawable.study_off);
                        break;
                }
                break;
        }
    }


    String getTime(){
        long now = SystemClock.elapsedRealtime();
        long time = now - mBaseTime;
        String timer = String.format("%02d:%02d:%02d", time/ 1000/ 60,(time/1000)%60, (time%1000)/10);
        return timer;
    }

}