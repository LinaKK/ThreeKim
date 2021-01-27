package com.example.studyforce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class whole_class_list extends AppCompatActivity {

    private String[] mClass = {"학생", "교수"};
    private AlertDialog mClassSelectDialog;

    private ClassJob[] cJobsData ={
      new ClassJob("안드로이드", 4, "학생"),
      new ClassJob("네트워크", 7, "학생"),
      new ClassJob("알고리즘", 2,"교수"),
    };

    private ListView cList;
    private ClassLIstAdapter1 cLAdapter1;

    ImageButton fClass;
    ImageButton addClass;
    ListView wholeClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_class_list);

        fClass = (ImageButton)findViewById(R.id.findClass);
        addClass = (ImageButton)findViewById(R.id.addClass);
        wholeClass = (ListView)findViewById(R.id.wholeClass);

        fClass.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });


        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClassSelectDialog.show();

            }
        });

        mClassSelectDialog = new AlertDialog.Builder(whole_class_list.this)
                .setItems(mClass, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setTitle("신분")
                .setPositiveButton("확인", null)
                .setNegativeButton("취소",null)
                .create();

        //리스트뷰 초기화
        cList = (ListView)findViewById(R.id.wholeClass);
        cLAdapter1 = new ClassLIstAdapter1(this, cJobsData);
        cList.setAdapter(cLAdapter1);

    }
}