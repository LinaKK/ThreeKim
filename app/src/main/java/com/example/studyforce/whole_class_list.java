//전체 클래스 리스트 (추가, 검색 기능 포함)

package com.example.studyforce;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class whole_class_list extends AppCompatActivity {

    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> data1;

    String[] mClass = {"학생", "교수"};
    ListView listView1;  //전체리스트뷰
    TextView errorm;
    int num;
    String name, email;

    public static wholeclist[] wclist;

    private String jobs = "학생";
    String state;

    ImageButton aClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_class_list);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

        Intent intent = getIntent();
        num = intent.getIntExtra("num",0);// 로그인후 학번
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");

        sendRequest1();

        aClass = (ImageButton)findViewById(R.id.addClass);

        //클래스추가버튼
       aClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jobs == mClass[0]){ //학생일때
                    Intent intent = new Intent(getApplicationContext(),CreateByStu.class);
                    intent.putExtra("num",num);
                    intent.putExtra("name", name);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }
                else{//교수일때 -도희야 이거 그냥 학생일때 화면으로 넘기고 비공개 버튼 막을수 있어?? 할수있으면 그렇게부탁 ㅠ
                    Intent intent = new Intent(getApplicationContext(),CreateByStu.class);
                    intent.putExtra("num",num);
                    intent.putExtra("name", name);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }
            }
        });

    }
    //키보드
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
                                int classnum = jsonObject.getInt("id");
                                String classname = jsonObject.getString("classname");
                                int num = jsonObject.getInt("num"); //학번
                                String name = jsonObject.getString("name");
                                String subject = jsonObject.getString("subject");
                                String goal = jsonObject.getString("goal");
                                int open = jsonObject.getInt("open");
                                int pw = jsonObject.getInt("pw");

                                wclist[i] = new wholeclist(classnum, classname, num, name, subject, goal, open, pw);

                            }println(wclist[1].classname);
                            //classname.setText(nList[1].notice);
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

    //클래스리스트 출력
    //지금 리스트는 제대로 넘어오는거같아 이번에는 진짜
    //도희야 문제가 같은 클래스가 가입한 사람수만큼 나와 걸러야해 -ctodolist에 내가 todo할때 쓴거 있는데 필요하면 참고용
    private void showCList(wholeclist[] wclist){
        data = new ArrayList<HashMap<String, String>>();
        data1 = new HashMap<String, String>();
        listView1 = (ListView) findViewById(R.id.wholeClasslist);
        // String name, String job, int num, String open
        for(int i=0; i<wclist.length; i++){
            data1.put("Name", wclist[i].classname);
            data1.put("Subject", wclist[i].subject);
            data.add(data1);
        }
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                data,R.layout.activity_class_list, new String[]{"Name", "Subject"}
                ,new int[]{R.id.class_name, R.id.class_job});
        listView1.setAdapter(adapter);


        //클래스 검색
        EditText classSearch = (EditText)findViewById(R.id.fClassname);
        classSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString();
                if(filterText.length() > 0){
                    listView1.setFilterText(filterText);
                }else{
                    listView1.clearTextFilter();
                }
                //
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        //리스트뷰 클릭시
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int i, long id) {
                AlertDialog.Builder ad = new AlertDialog.Builder(whole_class_list.this);
                ad.setTitle("가입메시지");
                ad.setMessage("해당 클래스에 가입하시겠습니까?");
                ad.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //개인클래스리스트로 넘기기(db)
                        //클래스이름갯수
                        //내부클래스 액티비티에 값 넘겨주기
                        String cnames = (String)parent.getAdapter().getItem(i);
                        Intent intent = new Intent(getApplicationContext(),InClass.class);
                        intent.putExtra("num",num);
                        intent.putExtra("name", name);
                        intent.putExtra("email",email);
                        intent.putExtra("cname",cnames);
                        startActivity(intent);
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

    private void println(String data){
        errorm = (TextView)findViewById(R.id.errorm);
        errorm.setText(data);
    }


    /*모른척부탁
    private wholeclist[] make (int i, int classnum, String classname, int num, String name, String subject, String goal ){
        wclist[i] = new wholeclist(classnum, classname, num, name, subject, goal);
        return wclist;
    }*/


}