package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Study_function extends AppCompatActivity {

    ImageView StudyState;
    Button TimerStart;
    Button TimerFinish;
    Button Exit;
    TextView studytimer_time;

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
        StudyState = (ImageView)findViewById(R.id.study_state);
        TimerStart = (Button)findViewById(R.id.timer_start);
        TimerFinish = (Button)findViewById(R.id.timer_finish);
        Exit = (Button)findViewById(R.id.exit2);
        studytimer_time =(TextView)findViewById(R.id.studytimer_time);


        //페이지 나가기
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), personal_page.class);
                startActivity(intent);
            }
        });
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
                        //멈춘 시간을 파악
                        mPauseTime = SystemClock.elapsedRealtime();
                        TimerStart.setText("시작");
                        mStatus = PAUSE;//상태를 멈춤으로 표시
                        StudyState.setImageResource(R.drawable.study_off);
                        break;

                    //멈춤이면
                    case PAUSE:
                        //현재값 가져옴
                        long now = SystemClock.elapsedRealtime();
                        //베이스타임 = 베이스타임 + (now - mPauseTime)
                        //잠깐 스톱워치를 멈췄다가 다시 시작하면 기준점이 변하게 되므로..
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
                    //RUNNING 상태일 때.
                    case RUNNING:
                        //기존의 값을 가져온뒤 이어붙이기 위해서
                        String sSplit = studytimer_time.getText().toString();
                        //텍스트뷰의 값을 바꿔줌
                        studytimer_time.setText(sSplit);
                        StudyState.setImageResource(R.drawable.study_off);
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
    } //지금 구현이 안된게 작동중인 상태에서 정지를 누르면 바로 정지가 안 되는 점
      //작동중인 상태에서 일시정지 누르고 정지를 눌러야만 작동됨..


    String getTime(){
        long now = SystemClock.elapsedRealtime();
        long time = now - mBaseTime;
        String timer = String.format("%02d:%02d:%02d", time/ 1000/ 60,(time/1000)%60, (time%1000)/10);
        return timer;
    }

}