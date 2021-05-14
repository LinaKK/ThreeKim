package com.example.studyforce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.sql.DriverManager.println;

public class InClass extends AppCompatActivity {
    private TextView classname;
    private TextView subject;
    private TextView goal;
    private ListView shortNoticeList;
    private ListView shortTodolist;
    ListView goalList;
    private static noticeList[] nList;
    private static classTodo[] cTodoList;

    long Now = System.currentTimeMillis();;
    java.util.Date Date = new Date(Now);
    SimpleDateFormat cDay = new SimpleDateFormat("dd");
    SimpleDateFormat cMonth = new SimpleDateFormat("MM");
    SimpleDateFormat cYear = new SimpleDateFormat("yyyy");
    String getDay = cDay.format(Date);
    String getMonth = cMonth.format(Date);
    String getYear = cYear.format(Date);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class);
        classname = (TextView) findViewById(R.id.InClassName);
        shortNoticeList = (ListView) findViewById(R.id.shortNoticeList);
        shortTodolist = (ListView) findViewById(R.id.shortTodoList);

        sendRequest1();
        sendRequest2();
        sendRequest3();

        clickGoalList();

        //가입 후 값 받아오기 (whole_class_list 액티비티의 listview에서)
        Intent intent = getIntent();
        classname.setText(intent.getStringExtra("name"));
        //..넘겨줄 값이 또 있을려나?? 해당 클래스 리스트 클릭후
        // 클래스이름만 넘겨주고 db에서 이름과 일치하는
        // 클래스 정보들을 끌어당겨서 보여주는게 낫겠지?? yes!!


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
                int getYears = Integer.parseInt(getYear);
                int getMonths = Integer.parseInt(getMonth);
                int getDays = Integer.parseInt(getDay);
                DatePickerDialog dialog = new DatePickerDialog(this, listener, getYears, getMonths-1, getDays);
                dialog.show();

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
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            AlertDialog.Builder ad = new AlertDialog.Builder(InClass.this);
            ad.setTitle(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
            ad.setMessage("오늘의 할일");
            ad.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ad.setNegativeButton("일정 추가", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "일정을 추가했습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            ad.show();
        }
    };

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
                       // processResponse(response);
                       // classname.setText(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            nList = new noticeList[jsonArray.length()];
                            int s = jsonArray.length();
                            //classname.setText(String.valueOf(s));
                            //classname.setText(nList[1].title);

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int noticeNum = jsonObject.getInt("noticenum");
                                String className = jsonObject.getString("classname");
                                int num = jsonObject.getInt("num");
                                String title = jsonObject.getString("title");
                                String notice = jsonObject.getString("notice");
                                nList[i] = new noticeList(noticeNum, num, className, title, notice);


                            }showNotiList(nList);
                            //classname.setText(nList[1].notice);
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

    public void showNotiList(noticeList[] notice){
        List nolist = new ArrayList();
        for(int i=0; i<notice.length; i++){
            nolist.add(nList[i].title);
        }
        ArrayAdapter<String> adapterNotice = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                nolist);
        shortNoticeList.setAdapter(adapterNotice);

    }

    //classTodoList request
    private void sendRequest3() {
        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        String url = "http://118.33.132.221/php/classtodo.php";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // processResponse(response);
                        // classname.setText(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            cTodoList = new classTodo[jsonArray.length()];
                            int s = jsonArray.length();
                            //classname.setText(String.valueOf(s));
                            //classname.setText(nList[1].title);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                int num = jsonObject.getInt("num");
                                int done = jsonObject.getInt("done");
                                String classtodo = jsonObject.getString("todo");
                                String className = jsonObject.getString("classname");
                                cTodoList[i] = new classTodo(id, num, classtodo, done, className);

                            }
                            showTodo(cTodoList);
                            //classname.setText(nList[1].notice);
                        } catch (JSONException e) {
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

    private void showTodo(classTodo[] cTodo){
        List todolist = new ArrayList();
        for(int i=0; i<cTodo.length; i++){
            todolist.add(cTodo[i].classtodo);
        }
        ArrayAdapter<String> adapterClassTodo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                todolist);
        shortTodolist.setAdapter(adapterClassTodo);

    }

    public void clickGoalList(){
        goalList = (ListView)findViewById(R.id.shortTodoList); //목표 listview
        goalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //int gnum = ((ClassJob)adapter.getItem(position)).getTitle();
                int gnum=0;
                Intent intent = new Intent(getApplicationContext(),InGoal.class);
                //목표번호를 넘겨줘야함(목표번호가 관리하기 쉬울 것 같아서) => 목표 생성할 때마다 목표number 1씩 증가하게 하면 될 듯...
                intent.putExtra("gnum", gnum);
                startActivity(intent);
            }
        });
    }

    //오류확인
    private void println(String data){
        classname.append(data);
    }


    private void printlnn(){
        Toast.makeText(this, "connect success",Toast.LENGTH_SHORT).show();
    }

}

