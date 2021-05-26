package com.example.studyforce;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class detailQ extends AppCompatActivity {
    private static qna qnalist[];
    private Alist[] alist;
    private String qtitle;
    private TextView Dqnatitle;
    private TextView contextQ;
    private ListView al;
    private answerListAdapter mAdapter;
    String classname;
    String name;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_q);
        Intent intent = getIntent();
        qtitle = intent.getStringExtra("qtitle");
        classname = intent.getStringExtra("classname");
        num = intent.getIntExtra("num",num);
        name = intent.getStringExtra("name");
        Dqnatitle = (TextView)findViewById(R.id.Dqnatitle);
        Dqnatitle.setText(qtitle);
        contextQ = (TextView)findViewById(R.id.contextQ);
        al = (ListView) findViewById(R.id.alist);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

        sendRequest();
        sendRequest1();

    }

    public void updateA(View view){
        Intent intent = new Intent(this, A.class);
        intent.putExtra("qtitle", qtitle);
        intent.putExtra("num",num);
        intent.putExtra("classname",classname);
        intent.putExtra("name",name);
        startActivity(intent);
    }

    private void sendRequest(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("qtitle", qtitle);
        }
        catch (JSONException e){}
        //contextQ.setText(rj.toString());

        String url = "http://118.33.132.221/php/contextQ.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String context = response.getString("q");
                            contextQ.setText(context);
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

    //답변하기 버튼 누르면 답변 화면이동

    //답변표시
    /*private void showAlist(qna[] list){
        final List qnal = new ArrayList();
        for(int i=0; i<list.length; i++){
            qnal.add(list[i].a);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                qnal);
        qlist.setAdapter(adapter);

    }*/

    private void println(String data){
        Dqnatitle = (TextView)findViewById(R.id.contextQ);
        Dqnatitle.append(data);
    }

    private void sendRequest1(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("qtitle", qtitle);
        }
        catch (JSONException e){}
        //contextQ.setText(rj.toString());

        String url = "http://118.33.132.221/php/listA.php";

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
                            alist = new Alist[jsonArray.length()];

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String a = jsonObject.getString("a");
                                String aw = jsonObject.getString("awriter");
                                alist[i] = new Alist(a, aw);
                            }
                            showAlist(alist);

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //println("error -> " + error.getMessage());
                    }
                }

        );
        AppHelper.requestQueue.add(jsonObjectRequest);

    }
    private void showAlist(Alist[] alist){
        /*List a = new ArrayList();
        for(int i=0; i<alist.length; i++){
            a.add(alist[i].a);
        }*/
        mAdapter = new answerListAdapter(this, alist);
        al.setAdapter(mAdapter);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                a);
        al.setAdapter(adapter);*/

    }

    public void onBackPressed(){
        Intent intent = getIntent();
        qtitle = intent.getStringExtra("qtitle");
        classname = intent.getStringExtra("classname");
        num = intent.getIntExtra("num",num);
        name = intent.getStringExtra("name");

        Intent intent2 = new Intent(this, qnaList.class);
        intent2.putExtra("num",num);
        intent2.putExtra("classname",classname);
        intent2.putExtra("name",name);
        startActivity(intent2);
    }

}
