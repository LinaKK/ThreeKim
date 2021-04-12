package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class InClass extends AppCompatActivity {
    private TextView classname;
    private TextView subject;
    private TextView goal;
    private ListView shortNoticeList;
    private ArrayList noticetitle;
    //private noticeList nolist = new noticeList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class);
        classname = (TextView) findViewById(R.id.InClassName);
        sendRequest1();
        sendRequest2();
        shortNoticeList = (ListView) findViewById(R.id.shortNoticeList);
        ArrayAdapter<String> adapterNotice = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                noticetitle);//noticeTitle로 교체
        shortNoticeList.setAdapter(adapterNotice);



        //가입 후 값 받아오기 (whole_class_list 액티비티의 listview에서)
        Intent intent = getIntent();
        classname.setText(intent.getStringExtra("name"));
        //..넘겨줄 값이 또 있을려나?? 해당 클래스 리스트 클릭후
        // 클래스이름만 넘겨주고 db에서 이름과 일치하는
        // 클래스 정보들을 끌어당겨서 보여주는게 낫겠지??

    }

    public void btnClick(View v){
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

    private void sendRequest1(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        StringRequest request = new StringRequest(
                Request.Method.GET,
                AppHelper.url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        classname.setText(response);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("error -> " + error.getMessage());
                    }
                }

        );
        AppHelper.requestQueue.add(request);
    }

    private void sendRequest2(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        String url = "http://118.33.132.221/php/noticeTitle.php";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("error -> " + error.getMessage());
                    }
                }

        );
        AppHelper.requestQueue.add(request);
    }

    public void processResponse(String response){
        Gson gson = new Gson();
        noticeList noticelist = gson.fromJson(response, noticeList.class);
        if (noticelist != null){
            ArrayAdapter<String> adapterNotice = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    noticetitle);//noticeTitle로 교체
            shortNoticeList.setAdapter(adapterNotice);
           /* int countNotice = noticelist.noticelist.size();

            getnotice g = new getnotice();
            for(int i =0; i<countNotice; i++){
                noticetitle.add(g.title);
            }*/

        }
    }


    //오류확인
    private void println(String data){
        classname.append(data);
    }


    private void printlnn(){
        Toast.makeText(this, "connect success",Toast.LENGTH_SHORT).show();
    }

}

