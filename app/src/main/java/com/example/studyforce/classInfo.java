package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class classInfo extends AppCompatActivity {
    TextView cSub;
    ListView cPList;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        cSub = (TextView)findViewById(R.id.cSubject);
        cPList = (ListView)findViewById(R.id.cPersonList);

        exit = (Button)findViewById(R.id.exit4);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}