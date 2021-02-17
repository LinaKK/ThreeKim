package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addNotice extends AppCompatActivity {
    private EditText title;
    private EditText cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        title = (EditText) findViewById(R.id.nTitle);
        cont = (EditText) findViewById(R.id.nCont);
    }

    private void mOnclick(View v){
        String t = title.getText().toString();
        String c = cont.getText().toString();
        if (t.length()>0){
            if(c.length()>0){
                //공지사항 등록
            }
            else
                Toast.makeText(this, "공지사항 내용을 입력하세요",Toast.LENGTH_SHORT).show();
        }

        else if (c.length()>0){
            if(t.length()>0){
                //공지사항 등록
            }
            else
                Toast.makeText(this, "제목을 입력하세요",Toast.LENGTH_SHORT).show();
        }

    }
}
