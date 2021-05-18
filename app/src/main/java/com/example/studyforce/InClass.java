package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
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
import com.android.volley.toolbox.JsonObjectRequest;
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
    private String cname;
    private int num;

    long Now = System.currentTimeMillis();;
    java.util.Date Date = new Date(Now);
    SimpleDateFormat cDay = new SimpleDateFormat("dd");
    SimpleDateFormat cMonth = new SimpleDateFormat("MM");
    SimpleDateFormat cYear = new SimpleDateFormat("yyyy");
    String getDay = cDay.format(Date);
    String getMonth = cMonth.format(Date);
    String getYear = cYear.format(Date);

    String name, email;

    boolean lastLoastState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class);
        classname = (TextView) findViewById(R.id.InClassName);
        shortNoticeList = (ListView) findViewById(R.id.shortNoticeList);
        shortTodolist = (ListView) findViewById(R.id.shortTodoList);
        //sendRequest1();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFF5FD4E3));

        //가입 후 값 받아오기 (whole_class_list 액티비티의 listview에서)
        Intent intent = getIntent();
        cname = intent.getStringExtra("cname");
        name = intent.getStringExtra("name");
        num = intent.getIntExtra("num",0);
        classname.setText(cname);
        //..넘겨줄 값이 또 있을려나?? 해당 클래스 리스트 클릭후 --->학번도 넘겨주세용
        // 클래스이름만 넘겨주고 db에서 이름과 일치하는
        // 클래스 정보들을 끌어당겨서 보여주는게 낫겠지?? yes!!

        sendRequest2("ThreeK");//(cname)
        sendRequest3("ThreeK");//(cname)
    }


    public void btnClick(View v){
        //Intent intent1 = getIntent();
        //String classname = intent1.getStringExtra("name");
        //String classname = "ThreeK";
        Intent intent;
        switch (v.getId()){
            case R.id.notice:
                intent = new Intent(this, notice.class);
                intent.putExtra("num",num);
                intent.putExtra("classname", cname);
                startActivity(intent);
                break;

            case R.id.nextEvent:
                intent = new Intent(this, nextEvent.class);
                intent.putExtra("num",num);
                intent.putExtra("classname", cname);
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
                intent.putExtra("num",num);
                intent.putExtra("classname", cname);
                startActivity(intent);
                break;

            case R.id.qna:
                intent = new Intent(this, qnaList.class);
                intent.putExtra("classname", cname);
                intent.putExtra("num",num);
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



    private void sendRequest2(String classname){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("classname", classname);
        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/noticeTitle.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("res");
                            nList = new noticeList[jsonArray.length()];

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int noticeNum = jsonObject.getInt("noticenum");
                                // String className = jsonObject.getString("classname");
                                int num = jsonObject.getInt("num");
                                String title = jsonObject.getString("title");
                                //String notice = jsonObject.getString("notice");
                                nList[i] = new noticeList(noticeNum, num, title);

                            }showNotiList(nList);


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

    public void showNotiList(noticeList[] notice){
        final List nolist = new ArrayList();
        for(int i=0; i<notice.length; i++){
            nolist.add(nList[i].title);
        }
        ArrayAdapter<String> adapterNotice = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                nolist);
        shortNoticeList.setAdapter(adapterNotice);

        shortNoticeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), noticeD.class);
                String noticeTitle = nolist.get(position).toString();
                intent.putExtra("ntitle", noticeTitle);
                intent.putExtra("classname",cname);
                startActivity(intent);
            }
        });

    }

    //classTodoList request
    private void sendRequest3(String classname){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("classname", classname);
        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/classtodo.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("res");
                            cTodoList = new classTodo[jsonArray.length()];

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                int num = jsonObject.getInt("num");
                                String classtodo = jsonObject.getString("todo");
                                String className = jsonObject.getString("classname");
                                int done = jsonObject.getInt("done");
                                String tname = jsonObject.getString("name");
                                cTodoList[i] = new classTodo(id, num, classtodo, done, className, tname);

                            }showTodo(cTodoList);


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

    private void showTodo(final classTodo[] cTodo){
        final List todolist = new ArrayList();
        final ArrayList<Glist> glist = new ArrayList<Glist>();
        for(int i=0; i<cTodo.length; i++){
            if (todolist.contains(cTodo[i].classtodo) == false) // 겹치는거 걸러서 넘겨서 개수 다르게 보일거야
                todolist.add(cTodo[i].classtodo);
            glist.add(new Glist(cTodo[i].classtodo,cTodo[i].name, cTodo[i].done));
        }
        ArrayAdapter<String> adapterClassTodo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                todolist);
        shortTodolist.setAdapter(adapterClassTodo);
        shortTodolist.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        shortTodolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String gTitle = todolist.get(position).toString();
                int gnum=0;
                for(int i=0; i<cTodo.length; i++){
                    if(cTodo[i].classtodo == gTitle)
                        gnum = cTodo[i].id;
                }
                Intent intent2 = getIntent();
                cname = intent2.getStringExtra("cname");
                name = intent2.getStringExtra("name");
                num = intent2.getIntExtra("num",0);
                Intent intent = new Intent(getApplicationContext(),InGoal.class);
                intent.putExtra("classname", cname);
                intent.putExtra("num",num);
                intent.putExtra("todo", gTitle);
                intent.putExtra("glist",glist);
                startActivity(intent);
            }
        });

    }

    //메뉴 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_menu, menu);
        return true;
    }

    //메뉴바 동작들
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //bgm메뉴
        if(id == R.id.music1){
            Intent intent= new Intent(getApplicationContext(), Music_p.class);
            startActivity(intent);
            //return true;
        }
        //클래스 정보
        if(id == R.id.setting){

        }
        return super.onOptionsItemSelected(item);
    }

    //오류확인
    private void println(String data){
        classname.append(data);
    }


    private void printlnn(){
        Toast.makeText(this, "connect success",Toast.LENGTH_SHORT).show();
    }

}

