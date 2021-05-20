package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.DriverManager.println;

public class personal_schedule extends AppCompatActivity {

    TextView date;
    long Now = System.currentTimeMillis();;
    Date Date = new Date(Now);
    SimpleDateFormat cDate = new SimpleDateFormat(" \u003Cyyyy년 MM월 dd일\u003E ");
    String getTime;
    private int num;
    private String name, email;
    private static scheduleList[] slist;

    //db용 - 학번을 걸러서 가져오기
    int id2;
    int sDay;
    int sMonth;
    int sYear;
    int eDay;
    int eMonth;
    int eYear;
    String schTitle;
    String schCont;

    //현재 일,월, 년도
    SimpleDateFormat cDay = new SimpleDateFormat("dd");
    SimpleDateFormat cMonth = new SimpleDateFormat("MM");
    SimpleDateFormat cYear = new SimpleDateFormat("yyyy");
    String getDay = cDay.format(Date);
    String getMonth = cMonth.format(Date);
    String getYear = cYear.format(Date);



    private ListView listView1;  //리스트뷰
    private ScheduleAdapter adapter; //리스트뷰어댑터


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_schedule);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

        MaterialCalendarView materialCalendarView = findViewById(R.id.calendar);
        materialCalendarView.setSelectedDate(CalendarDay.today());
        materialCalendarView.addDecorators(
                new SundayDecorator(), new SaturdayDecorator());

        date = (TextView)findViewById(R.id.date);
        //Now = System.currentTimeMillis();
        //Date = new Date(Now);
        getTime = cDate.format(Date);
        date.setText(getTime);

        //어댑터 생성
        adapter = new ScheduleAdapter();
        //리스트뷰에 어댑터 삽입
        listView1= (ListView)findViewById(R.id.today_todo);
        listView1.setAdapter(adapter);

        //회원정보 가져오기
        Intent intent = getIntent();
        num = intent.getIntExtra("num",0);// 로그인후 학번
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");

        //sendRequest();
        getSchedule();

        //일정 예시
        adapter.addItem("과제1","2021/"+"04/09","2021/04/30","알고리즘 과제 빨리 끝내기");
        adapter.addItem("과제2", "2021/05/10", "2021/05/28", "레포트 제출");
        //AddItem();
        adapter.notifyDataSetChanged();

        /*
        adapter.addItem(schTitle, sDay+"/"+sMonth+"/"+sYear, eDay+"/"+eMonth+"/"+eYear, schCont);
         */


        //날짜 선택 시 팝업창 띄우기
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                AlertDialog.Builder ad = new AlertDialog.Builder(personal_schedule.this);
                ad.setTitle(date.getYear()+"/"+(date.getMonth()+1)+"/"+date.getDay());
                //setCont에서 날짜 비교해서 일치하는 날짜의 일정만 출력
                String cont = setCont(date.getYear(), (date.getMonth()+1),date.getDay());
                ad.setMessage("그 날의 할일");

                ad.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();

            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int i, long id) {
                AlertDialog.Builder ad = new AlertDialog.Builder(personal_schedule.this);
                ad.setTitle("일정삭제");
                ad.setMessage("해당 일정을 삭제하시겠습니까?");
                ad.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //db에서 삭제해야함
                        deleteSchedule("스케줄이름넣어주세요");//이건 서버확인용
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

    }

    public void deleteSchedule(String schedule){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("num", num);
            rj.put("todo", schedule);
        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/deleteSchedule.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int res = response.getInt("res");
                            if (res == 1) print();//String- 출력->같음 ==->다름


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

    private void print(){
        Toast.makeText(getApplicationContext(), "일정을 삭제했습니다.", Toast.LENGTH_SHORT).show();
    }

    //일정 db에서 가져오기(시작날짜, 종료날짜, 제목, 내용) 학번은 이전페이지에서 인텐트로 받아서 db로 보내서 스케줄가져오는거라 따로 안가져왔어
    /* 행번호는 어디쓰게?? 저거 가져와도 순서대로 아닐텐뎅..여러명꺼 다 섞여있던거 골라오는거라 cont??
    //num(학번), id(행번호), s_day, s_month, s_year, e_day, e_month, e_year, title, cont
     JSONObject jsonObject = jsonArray.getJSONObject(i);
     id2 = jsonObject.getInt("id");
     sDay = jsonObject.getInt("s_day");
     sMonth = jsonObject.getInt("s_month");
     sYear = jsonObject.getInt("s_year");
     eDay = jsonObject.getInt("e_day");
     eMonth = jsonObject.getInt("e_month");
     eYear = jsonObject.getInt("e_year");
     schTitle = jsonObject.getString("title");
     schCont = jsonObject.getString("cont");
     */

    public void getSchedule(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("num", num);
        }
        catch (JSONException e){}
        //contextQ.setText(rj.toString());

        String url = "http://118.33.132.221/php/getSchedule.php";

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // processResponse(response);
                        // classname.setText(response);
                        try {
                            JSONArray jsonArray = response.getJSONArray("res");
                            slist = new scheduleList[jsonArray.length()];

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String todo = jsonObject.getString("todo");
                                int syear = jsonObject.getInt("syear");
                                int smonth = jsonObject.getInt("smonth");
                                int sday = jsonObject.getInt("sday");
                                int eyear = jsonObject.getInt("eyear");
                                int emonth = jsonObject.getInt("emonth");
                                int eday = jsonObject.getInt("eday");
                                String con = jsonObject.getString("todocontext");
                                slist[i] = new scheduleList(todo, syear, smonth, sday, eyear, emonth, eday, con);
                                //도희야 이거내가 가져오느라 그냥 편하게 쓴거고 scheduleList에 있는거= 너가 위에 주석으로 적은거대로
                                //쓰면돼 like eDay, scheCont ...
                            }
                            //showScheduleList(slist); 여기서 일정표시 리스트 함수 호출하면됑 + 가져온거 쓸 다른 필요한함수

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
        AppHelper.requestQueue.add(jsonObjectRequest);

    }
    private void println(String data){
        TextView a;
        a = (TextView)findViewById(R.id.scheduleError);
        a.append(data);
    }


    //일정표시리스트뷰 (날짜계산해서 해당 날짜범위의 것만 표시)
   /* public void AddItem(){

         //어댑터 생성
        adapter = new ScheduleAdapter();
        //리스트뷰에 어댑터 삽입
        listView1= (ListView)findViewById(R.id.today_todo);
        listView1.setAdapter(adapter);

        if(sYear <= getYear =< eYear){
        if(sMonth <= getMonth =<eMonth){
        if(sDay <= getDay =<eDay){
        //adapter.addItem(schTitle, sYear+"/"+sMonth+"/"+sDay,  eYear+"/"+eMonth+"/"+eDay, schCont);
        }
        }
        }

    }*/


    //일정 추가이벤트
    public void date_add(View view) {
        Intent intent = new Intent(this, ScheduleAdd.class);
        intent.putExtra("num",num);
        intent.putExtra("name", name);
        intent.putExtra("email",email);
        startActivity(intent);
    }

    //날짜알림창에 넣을 내용
    String setCont(int year, int month, int day){

        return null;
    }


}

