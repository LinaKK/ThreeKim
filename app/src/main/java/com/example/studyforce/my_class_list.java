package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;

public class my_class_list extends AppCompatActivity {
    Button exit;
    Button plus_class;
    ImageView classpl;
    ImageView exit1;
    TextView username2;
    int id;
    int userid;
    ListView myClassList;
    myClist[] myClist;
    String username;

    public static wholeclist[] wclist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class_list);
        exit=findViewById(R.id.exit);
        /*plus_class=findViewById(R.id.plus_class);*/
        classpl=findViewById(R.id.classpl);
        exit1=findViewById(R.id.exit1);

        myClassList = (ListView)findViewById(R.id.list_class);
        username2 = (TextView)findViewById(R.id.username2);

        Intent intent = getIntent();
        username = intent.getStringExtra("name");
        userid = intent.getIntExtra("num",0);
        final String usermail = intent.getStringExtra("email");
        username2.setText(username);

        /*
        본인 클래스 리스트 db에서 받아오기
        (클래스name, 클래스공개(or 비공개))
        => 누르면 클래스 내부 페이지로 이동
         */
        getMyclass();

        //나가기 버튼
        /*exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(my_class_list.this, personal_page.class);
                intent.putExtra("name",username);
                intent.putExtra("num",userid);
                intent.putExtra("email", usermail);
                startActivity(intent);
            }
        });*/

        exit1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(my_class_list.this, personal_page.class);
                intent.putExtra("name",username);
                intent.putExtra("num",userid);
                intent.putExtra("email", usermail);
                startActivity(intent);
            }
        });

        //클래스 추가버튼
        /*plus_class.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (my_class_list.this, whole_class_list.class);
                intent.putExtra("name",username);
                intent.putExtra("num",userid);
                intent.putExtra("email", usermail);
                startActivity(intent);
            }
        });*/

        //클래스추가 이미지버튼 -안되면 이거 주석 후 위해 주석이용용
        classpl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent (my_class_list.this, whole_class_list.class);
                intent.putExtra("name",username);
                intent.putExtra("num",userid);
                intent.putExtra("email", usermail);
                startActivity(intent);
            }
        });

        };

    private void getMyclass(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("num", userid);
        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/MyClassList.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("res");
                            myClist = new myClist[jsonArray.length()];
                            int s = jsonArray.length();

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String classname = jsonObject.getString("classname");
                                String subject = jsonObject.getString("subject");
                                String goal = jsonObject.getString("goal");
                                int open = jsonObject.getInt("open");
                                myClist[i] = new myClist(classname, subject, goal, open);

                            }showCList(myClist);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("error -> " + error.getMessage());
                    }
                }
        );
        AppHelper.requestQueue.add(jsonObjectRequest);
    }

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
                                int classnum = jsonObject.getInt("classnum");//안가져옴
                                String classname = jsonObject.getString("classname");
                                int num = jsonObject.getInt("num"); //학번, 안가져옴, 인텐트사용
                                String name = jsonObject.getString("name");//안가져옴, 인텐트사용
                                String subject = jsonObject.getString("subject");
                                String goal = jsonObject.getString("goal");
                                int open = jsonObject.getInt("open");
                                int pw = jsonObject.getInt("pw");//안가져옴

                                wclist[i] = new wholeclist(classnum, classname, num, name, subject, goal, open, pw);

                            }
                            //showCList(wclist);
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
    private void showCList(myClist[] list){
       /* Intent intent = getIntent();
        id= intent.getIntExtra("num", 0);
        final String username3 = intent.getStringExtra("name");*/

        myClassList = (ListView)findViewById(R.id.list_class);

        //학번 구분 or 이름 구분 -> 서버에서 걸러서 가져와서 안해도됩니당
        final List mclist = new ArrayList();
        for(int i=0; i<list.length; i++){
             mclist.add(list[i].classname);
        }
         ArrayAdapter<String> adapterMCList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                mclist); //layout 수정해야함.
        myClassList.setAdapter(adapterMCList);


        myClassList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                username = intent.getStringExtra("name");
                userid = intent.getIntExtra("num",0);
                final String usermail = intent.getStringExtra("email");

                Intent intent2 = new Intent(getApplicationContext(), InClass.class);
                String classname = mclist.get(position).toString();
                intent2.putExtra("num", userid);
                intent2.putExtra("cname", classname);
                intent2.putExtra("email", usermail);
                intent2.putExtra("name",username);
                startActivity(intent2);
            }
        });

        }
    public void onBackPressed(){
        Intent intent2 = getIntent();
        username = intent2.getStringExtra("name");
        userid = intent2.getIntExtra("num",0);

        Intent intent = new Intent(this, personal_page.class);
        intent.putExtra("num",userid);
        intent.putExtra("name", username);
        startActivity(intent);
    }
    }
