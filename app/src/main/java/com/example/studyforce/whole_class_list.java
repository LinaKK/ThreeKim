//전체 클래스 리스트 (추가, 검색 기능 포함) - 검색 기능은 아직 미완/추가할때 메시지 창도 불안함

package com.example.studyforce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
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
        classSearch = (EditText)findViewById(R.id.fClassname);
        //listView1 = (ListView)findViewById(R.id.);


        //클래스찾기버튼


        //클래스 추가버튼


        //리스트뷰 초기화

    }
    //메시지 창 설정

}