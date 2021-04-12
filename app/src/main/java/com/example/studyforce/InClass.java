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
import com.github.mikephil.charting.charts.ScatterChart;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
    private ArrayList Notice;
    private static noticeList[] nList;
    private static String nolist[];//notice

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class);
        classname = (TextView) findViewById(R.id.InClassName);

        //sendRequest1();
        sendRequest2();

       // processResponse();
        shortNoticeList = (ListView) findViewById(R.id.shortNoticeList);





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
        String url = "http://118.33.132.221/php/noticeList.php";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // processResponse(response);
                       // classname.setText(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            nList = new noticeList[jsonObject.length()];
                            String title = jsonObject.getString("title");
                            classname.setText(title);

                            /*for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int noticenum = jsonObject.getInt("noticenum");
                                int num = jsonObject.getInt("num");
                                String classname = jsonObject.getString("classname");
                                String title = jsonObject.getString("title");
                                String notice = jsonObject.getString("notice");
                                nList[i].noticenum = noticenum;
                                nList[i].num = num;
                                nList[i].classname = classname;
                                nList[i].title = title;
                                nList[i].notice = notice;
                                if (i+1 == jsonArray.length()) processResponse(nList.length);
                            }*/
                            //classname.setText(nList[1].title);

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                        /*Gson gson = new Gson();
                        noticeList noticelist = gson.fromJson(response, noticeList.class);
                        getnotice g = new getnotice();
                        classname.setText();*/
                        //classname.setText(noticelist.title.toString());
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

    public void processResponse(int k){
        nolist = new String[k];
        for(int i=0; i<nList.length; i++){
            nolist[i] = nList[i].title;
        }
        ArrayAdapter<String> adapterNotice = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                nolist);
        shortNoticeList.setAdapter(adapterNotice);
        /*Gson gson = new Gson();
        noticeList noticelist = gson.fromJson(response, noticeList.class);
        if (noticelist != null){
            int i=0;*
           // classname.setText(noticelist.titlelist.get(i).toString());
            /*int countNotice = noticelist.titlelist.size();

            getnotice g = new getnotice();
            for(int i =0; i<countNotice; i++){
                noticetitle.add(noticelist.titlelist);
            }*/

       // }
    }


    //오류확인
    private void println(String data){
        classname.append(data);
    }


    private void printlnn(){
        Toast.makeText(this, "connect success",Toast.LENGTH_SHORT).show();
    }

}

