package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

public class InClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class);

    }

    public void BtnClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.notice:
                intent = new Intent(this, notice.class);
                startActivity(intent);
                break;

            case R.id.nextEvent:
                intent = new Intent(this, nextEvent.class);
                startActivity(intent);
                break;

            case R.id.cal:
                //달력추가, 이벤트 표시가능?
                break;

            case R.id.todo:
                intent = new Intent(this, todoC.class);
                startActivity(intent);
                break;

            case R.id.qna:
                intent = new Intent(this, qnaList.class);
                startActivity(intent);
                break;

        }


    }

   /* public void sendRequest(){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
        );
    }*/
}
