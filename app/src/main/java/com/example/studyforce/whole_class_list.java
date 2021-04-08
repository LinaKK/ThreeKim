//전체 클래스 리스트 (추가, 검색 기능 포함) - 검색 기능은 아직 미완/추가할때 메시지 창도 불안함

package com.example.studyforce;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class whole_class_list extends AppCompatActivity {

    private String[] mClass = {"학생", "교수"};
    private AlertDialog mClassSelectDialog;
    private SeachClassAdapter adapter1; //리스트뷰와 연결할 어댑터
    private EditText classSearch; //검색어를 입력할 창
    private ListView listView1;  //전체리스트뷰
    private ClassLIstAdapter1 adapter; //전체리스트뷰어댑터

    private String jobs = "학생";


    private ListView cList;

    ImageButton fClass;
    ImageButton aClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_class_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

        //어댑터 생성
        adapter = new ClassLIstAdapter1();

        listView1 = (ListView)findViewById(R.id.wholeClasslist);
        listView1.setAdapter(adapter);

        adapter.addItem("알고리즘", "2명", "전공", "공개");
        adapter.addItem("정보보안", "4명", "전공","공개");
        adapter.addItem("데이터", "0명", "전공", "비공개");
        adapter.addItem("지능형", "4명", "전공","공개");
        adapter.addItem("창의", "4명", "전공"," 비공개");
        adapter.addItem("C언어", "10명", "전공","공개");
        adapter.addItem("java", "4명", "전공","공개");
        adapter.addItem("C++", "45명", "전공","공개");

        adapter.notifyDataSetChanged();


        fClass = (ImageButton)findViewById(R.id.findClass);
        classSearch = (EditText)findViewById(R.id.fClassname);
        aClass = (ImageButton)findViewById(R.id.addClass);


        //클래스찾기버튼


        //클래스추가버튼
        aClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jobs == mClass[0]){ //학생일때
                    Intent intent = new Intent(getApplicationContext(),CreateByStu.class);
                    startActivity(intent);
                }
                else{//교수일때
                    Intent intent = new Intent(getApplicationContext(),CreateByT.class);
                    startActivity(intent);
                }
            }
        });




        //리스트뷰 초기화

    }


    //클래스 추가버튼 -> 클래스 클릭시 추가 여부 메시지 띄우기


    //키보드
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


}