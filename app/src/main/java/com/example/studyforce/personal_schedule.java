package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
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


   scheduleList[] scheduleLists;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_schedule);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

        date = (TextView)findViewById(R.id.date);
        //Now = System.currentTimeMillis();
        //Date = new Date(Now);
        getTime = cDate.format(Date);
        date.setText(getTime);

        //회원정보 가져오기
        Intent intent = getIntent();
        num = intent.getIntExtra("num",0);// 로그인후 학번
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");


        //sendRequest();
        getSchedule();


        MaterialCalendarView materialCalendarView = findViewById(R.id.calendar);
        materialCalendarView.setSelectedDate(CalendarDay.today());
        materialCalendarView.addDecorators(
                new SundayDecorator(), new SaturdayDecorator());
        //날짜 선택 시 팝업창 띄우기
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                AlertDialog.Builder ad = new AlertDialog.Builder(personal_schedule.this);
                ad.setTitle(date.getYear()+"/"+(date.getMonth()+1)+"/"+date.getDay());
                //setCont에서 날짜 비교해서 일치하는 날짜의 일정만 출력
                String cont = setCont(date.getYear(), (date.getMonth()+1),date.getDay());

                /*for (int i =0; i<Sslist.size(); i++){
                    if(Sslist[i].sYear<=date.getYear()<=Sslist[i].eYear&&Sslist[i].sMonth<=(date.getMonth()+1)<=Sslist[i].eMonth
                    &&Sslist[i].sDay<=date.getDay()<=Sslist[i].eDay){
                    String t = Sslist[i].schTitle;
                    String cont = Sslist[i].schCont;
                    }
                }*/
                ad.setMessage("그 날의 할일"+"\n"+"할일의 내용 어쩌구저쩌구");
                //ad.setMessage(t+"\n"+cont);
                ad.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
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
                                schTitle = jsonObject.getString("todo");
                                sYear = jsonObject.getInt("syear");
                                sMonth = jsonObject.getInt("smonth");
                                sDay = jsonObject.getInt("sday");
                                eYear = jsonObject.getInt("eyear");
                                eMonth = jsonObject.getInt("emonth");
                                eDay = jsonObject.getInt("eday");
                                schCont = jsonObject.getString("todocontext");
                                slist[i] = new scheduleList(schTitle, sYear, sMonth, sDay, eYear, eMonth, eDay, schCont);
                                //도희야 이거내가 가져오느라 그냥 편하게 쓴거고 scheduleList에 있는거= 너가 위에 주석으로 적은거대로
                                //쓰면돼 like eDay, scheCont ...
                            }
                            showScheduleList(slist); //여기서 일정표시 리스트 함수 호출하면됑 + 가져온거 쓸 다른 필요한함수

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

    public void showScheduleList(scheduleList[] Slist){
        listView1= (ListView)findViewById(R.id.today_todo);
        final List Schlist = new ArrayList();
        final List<Schedule> Sslist = new ArrayList<Schedule>();
        for(int i=0; i<Slist.length; i++){
            Schlist.add(slist[i].schTitle);
            Sslist.add(new Schedule(slist[i].schTitle,slist[i].sYear,slist[i].sMonth,slist[i].sDay, slist[i].eYear,slist[i].eMonth, slist[i].eDay, slist[i].schCont));
        }
        ArrayAdapter<String> adapterSchedule = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                Schlist);
        listView1.setAdapter(adapterSchedule);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int i, long id) {
                AlertDialog.Builder ad = new AlertDialog.Builder(personal_schedule.this);
                ad.setTitle("일정삭제");
                ad.setMessage("해당 일정을 삭제하시겠습니까?");
                final String title = (String)parent.getAdapter().getItem(i);
                ad.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //db에서 삭제해야함
                        deleteSchedule(title);//이건 서버확인용
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

        public String setTitle(int year, int month, int day){
            String t="";
            return t;
        }


    public String setCont(int year, int month, int day){
        String cont="";
        return cont;
    }


    //일정 추가이벤트
    public void date_add(View view) {
        Intent intent = new Intent(this, ScheduleAdd.class);
        intent.putExtra("num",num);
        intent.putExtra("name", name);
        intent.putExtra("email",email);
        startActivity(intent);
    }
}



