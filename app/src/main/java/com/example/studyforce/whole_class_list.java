//전체 클래스 리스트 (추가, 검색 기능 포함) - 검색 기능은 아직 미완/추가할때 메시지 창도 불안함

package com.example.studyforce;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class whole_class_list extends AppCompatActivity {

    String[] mClass = {"학생", "교수"};
    //EditText classSearch; //검색어를 입력할 창
    ListView listView1 = null;  //전체리스트뷰
    ClassLIstAdapter1 adapter;

    private String jobs = "학생";

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

        listView1 = (ListView) findViewById(R.id.wholeClasslist);
        listView1.setAdapter(adapter);

        //클래스 미리 생성된 것들
        adapter.addItem("apple", "2명", "전공", "공개");
        adapter.addItem("bird", "4명", "전공","공개");
        adapter.addItem("cat", "0명", "전공", "비공개");
        adapter.addItem("dog", "4명", "전공","공개");
        adapter.addItem("efgh", "4명", "전공"," 비공개");
        adapter.addItem("Cba", "10명", "전공","공개");
        adapter.addItem("java", "4명", "전공","공개");
        adapter.addItem("C++", "45명", "전공","공개");


        //adapter.notifyDataSetChanged();

        fClass = (ImageButton)findViewById(R.id.findClass);
        EditText classSearch = (EditText)findViewById(R.id.fClassname);
        aClass = (ImageButton)findViewById(R.id.addClass);


        //검색
        classSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
            String filterText = edit.toString();
            if(filterText.length() > 0){
                listView1.setFilterText(filterText);
            }else{
                listView1.clearTextFilter();
            }
        }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

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

        //리스트뷰 클릭 시 가입 여부 알림창
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int i, long id) {
                AlertDialog.Builder ad = new AlertDialog.Builder(whole_class_list.this);
                ad.setTitle("가입메시지");
                ad.setMessage("해당 클래스에 가입하시겠습니까?");
                ad.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //내부클래스 액티비티에 값 넘겨주기
                        String names = ((ClassJob)adapter.getItem(i)).getTitle();
                       Intent intent = new Intent(getApplicationContext(),InClass.class);
                       intent.putExtra("name",names);
                       startActivity(intent);
                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });
        //리스트뷰 초기화

    }
    //키보드
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


}