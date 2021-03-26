package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    void onButton_info2(View view){
        Intent intent = new Intent(this, InClass.class);
        startActivity(intent);
    }
}