package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class detailQ extends AppCompatActivity {
    private int qtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_q);
        Intent intent = getIntent();
        qtitle = intent.getIntExtra("qtitle", qtitle);
    }
}
