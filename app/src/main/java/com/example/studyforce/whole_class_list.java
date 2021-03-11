//전체 클래스 리스트 (추가, 검색 기능 포함)

package com.example.studyforce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class whole_class_list extends AppCompatActivity {

    private String[] mClass = {"학생", "교수"};
    private AlertDialog mClassSelectDialog;
    private SeachClassAdapter adapter1;
    private ListView listView1;  //검색과 관련된 리스트뷰


    //리스트뷰 아이템
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


        //클래스찾기버튼
        fClass.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //
            }
        });

        //클래스 추가버튼
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

        //리스트 클릭 이벤트 처리 - 누르면 추가 여부 메시지 창 띄우기(alertdialog)
        cList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showMessage();

            }
        });
    }
    //메시지 창 설정
    public void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("과목 추가");
        builder.setMessage("해당 클래스를 추가하시겠습니까?");

        //추가 버튼 (눌리면 my_class_list에 추가됨)
        builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //취소버튼
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}