package com.example.studyforce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Study_timer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Study_timer extends Fragment {


    ImageView StudyState;
    Button TimerStart;
    Button TimerFinish;
    TextView studytimer_time;
    Button button1;

    final static int IDLE = 0;
    final static int RUNNING = 1;
    final static int PAUSE = 2;
    int mStatus = IDLE;//처음 상태는 IDLE
    long mBaseTime;
    long mPauseTime;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Study_timer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Study_timer.
     */
    // TODO: Rename and change types and number of parameters
    public static Study_timer newInstance(String param1, String param2) {
        Study_timer fragment = new Study_timer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_study_timer, container, false);

        StudyState = (ImageView)view.findViewById(R.id.study_state);
        TimerStart = (Button)view.findViewById(R.id.timer_start);
        TimerFinish = (Button)view.findViewById(R.id.timer_finish);
        studytimer_time =(TextView)view.findViewById(R.id.studytimer_time);

        return view;
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