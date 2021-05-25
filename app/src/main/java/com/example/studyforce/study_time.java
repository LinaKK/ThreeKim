//스톱워치
package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class study_time extends AppCompatActivity {


    ImageView StudyState;
    Button TimerStart;
    Button TimerFinish;
    TextView studytimer_time;
    ImageButton graph;
    Button exit;

    final static int IDLE = 0;
    final static int RUNNING = 1;
    final static int PAUSE = 2;
    int mStatus = IDLE;//처음 상태는 IDLE
    long mBaseTime;
    long mPauseTime;
    String time;

    private int num;
    private String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_time);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //홈버튼

        StudyState = (ImageView)findViewById(R.id.study_state);
        TimerStart = (Button)findViewById(R.id.timer_start);
        TimerFinish = (Button)findViewById(R.id.timer_finish);
        studytimer_time =(TextView)findViewById(R.id.studytimer_time);
        exit = (Button)findViewById(R.id.exit2);
        graph = (ImageButton)findViewById(R.id.graph);

        //회원 정보
        Intent intent = getIntent();
        num = intent.getIntExtra("num",0);// 로그인후 학번
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");


        //나가기
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),personal_page.class);
                intent.putExtra("num",num);
                intent.putExtra("name", name);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });


    }

    //메뉴를 액션바에 삽입
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    //메뉴바 동작들
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //bgm메뉴
        if(id == R.id.music1){
            Intent intent= new Intent(getApplicationContext(), Music_p.class);
            startActivity(intent);
            //return true;
        }
        //예시용 메뉴
        if(id == R.id.setting){

        }
        return super.onOptionsItemSelected(item);
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
                        time = sSplit;
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