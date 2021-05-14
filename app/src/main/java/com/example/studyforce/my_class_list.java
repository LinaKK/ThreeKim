package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.sql.DriverManager.println;

public class my_class_list extends AppCompatActivity {
    Button exit;
    Button plus_class;
    int userid;  //학번(로그인 성공 시 받아오기)

    public static wholeclist[] wclist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class_list);
        exit=findViewById(R.id.exit);
        plus_class=findViewById(R.id.plus_class);

        /*
        본인 클래스 리스트 db에서 받아오기
        (클래스name, 클래스공개(or 비공개))
        => 누르면 클래스 내부 페이지로 이동
         */
        sendRequest1();

        //나가기 버튼
        exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(my_class_list.this, personal_page.class);
                startActivity(intent);
            }
        });

        //클래스 추가버튼
        plus_class.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (my_class_list.this, whole_class_list.class);
                startActivity(intent);
            }
        });

        };

    private void sendRequest1(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        String url = "http://118.33.132.221/php/wholeclasslist.php";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            wclist = new wholeclist[jsonArray.length()];
                            int s = jsonArray.length();
                            //classname.setText(String.valueOf(s));
                            //classname.setText(nList[1].title);

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int classnum = jsonObject.getInt("classnum");
                                String classname = jsonObject.getString("classname");
                                int num = jsonObject.getInt("num"); //학번
                                String name = jsonObject.getString("name");
                                String subject = jsonObject.getString("subject");
                                String goal = jsonObject.getString("goal");
                                int open = jsonObject.getInt("open");

                                wclist[i] = new wholeclist(classnum, classname, num, name, subject, goal, open);

                            }
                            showCList(wclist);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
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


    //my class list 보여주기용 - 수정중....
    private void showCList(wholeclist[] wclist){
        /* 학번 구분해야함...
        for(int i=0; i<.length; i++){
        if(wclist[i].name == username)
         ArrayAdapter<String> adapterNotice = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                nolist); //layout 수정해야함.
        shortNoticeList.setAdapter(adapterNotice);
        }
         */

        }
    }
