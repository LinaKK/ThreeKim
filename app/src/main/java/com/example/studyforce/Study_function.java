package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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


    BottomNavigationView bottomNavigationView;
    Fragment studyTimer;
    Fragment studyGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_function);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        studyGraph = new Studytime_graph();
        studyTimer = new Study_timer();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,studyTimer)
                .commitAllowingStateLoss();


        //bottomnavigation bar button설정
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.timerpage:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout,studyTimer).commitAllowingStateLoss();
                        return true;
                    case R.id.timeresult:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_layout,studyGraph).commitAllowingStateLoss();
                        return true;
                }
                return false;
            }
        });

    }




}