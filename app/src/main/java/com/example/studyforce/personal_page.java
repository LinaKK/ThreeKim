//개인페이지(로그인 후 보여질 페이지)

package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class personal_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);

        //로그인 성공시 받아올 학번
        int userid2 = ((Login_Request)Login_Request.context_login).userid1;

        //학번->이름으로 연결(data연결)

    }

    //개인정보
    void onButton_info(View view){
        Intent intent = new Intent(this, personal_info.class);
        startActivity(intent);
    }
    //기능추가용
    void onButton_function(View v){
        Intent intent = new Intent(this, Study_function.class);
        startActivity(intent);
    }
    //스케줄러
    void onButton_schedule(View v){
        Intent intent = new Intent(this, personal_schedule.class);
        startActivity(intent);
    }
    //개인 클래스 리스트
    void onButton_uclass(View v){
        Intent intent = new Intent(this, my_class_list.class);
        startActivity(intent);
    }


    //앱종료
    void app_exit(View v){
        ActivityCompat.finishAffinity(this);
        System.exit(0);
    }
    //뒤로 가기 두번 -> 앱종료
    long pressTime;
    public void onBackPressed(){
        if(System.currentTimeMillis() - pressTime < 2000){
            finishAffinity();
            return;
        }
        Toast.makeText(this, "한 번 더 누르시면 앱이 종료됩니다", Toast.LENGTH_LONG).show();
        pressTime=System.currentTimeMillis();
    }
}