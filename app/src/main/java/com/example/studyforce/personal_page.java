//개인페이지(로그인 후 보여질 페이지)

package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class personal_page extends AppCompatActivity {


    ImageButton info;
    ImageButton timer;
    ImageButton schedule;
    ImageButton myclass;
    ImageView mp3;
    Button exit, logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);

        getSupportActionBar().setTitle("StudyForCE"); //액션바 제목
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999)); //액션바배경색
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //홈버튼




        //로그인 성공시 받아올 학번
        //int userid2 = ((Login_Request)Login_Request.context_login).userid1;

        //학번->이름으로 연결(data연결)

        info = (ImageButton)findViewById(R.id.userinfo);
        schedule = (ImageButton)findViewById(R.id.schedule);
        myclass = (ImageButton)findViewById(R.id.userclasses);
        timer = (ImageButton)findViewById(R.id.timer);
        exit = (Button)findViewById(R.id.exit);
        logout=(Button)findViewById(R.id.logout);

        //개인정보
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), personal_info.class);
                startActivity(intent);
            }
        });

        //개인 스케줄러
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), personal_schedule.class);
                startActivity(intent);
            }
        });


        //개인 클래스 리스트
        myclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), my_class_list.class);
                startActivity(intent);
            }
        });

        //타이머
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), study_time.class);
                startActivity(intent);
            }
        });

        //로그아웃
        logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(), Login.class);
              startActivity(intent);
                    }
                });

        //앱종료
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(personal_page.this);
                builder.setMessage("정말로 종료하시겠습니까?");
                builder.setTitle("종료알림창")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                return;
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("종료알림창");
                alert.show();
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
        if(id == R.id.studytime){
            Intent intent= new Intent(getApplicationContext(), Study_function.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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