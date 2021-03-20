package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class lock_setting extends AppCompatActivity {

    Button lock_on;
    Button lock_off;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_setting);

        lock_off=(Button)findViewById(R.id.lock_off);
        lock_on=(Button)findViewById(R.id.lock_on);


    }

    //곧 없어질 액티비티
}