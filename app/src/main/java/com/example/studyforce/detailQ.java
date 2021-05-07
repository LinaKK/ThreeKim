package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class detailQ extends AppCompatActivity {
    private static qna qnalist[];
    private ListView qlist;
    private String qtitle;
    private TextView Dqnatitle;
    private TextView contextQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_q);
        Intent intent = getIntent();
        qtitle = intent.getStringExtra("qtitle");
        Dqnatitle = (TextView)findViewById(R.id.Dqnatitle);
        Dqnatitle.setText(qtitle);
        contextQ = (TextView)findViewById(R.id.contextQ);
        sendRequest();



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

        String url = "http://118.33.132.221/php/contextQ.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        contextQ.setText(response.toString());
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

    private void sendRequest2(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }

        JSONObject rj = new JSONObject();
        try {
            rj.put("qtitle", qtitle);
        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/contextQ.php";
        StringRequest request = new StringRequest(

                Request.Method.GET,
                url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        contextQ.setText(response);
                        // processResponse(response);
                        // classname.setText(response);
                        /*try {
                            JSONArray jsonArray = new JSONArray(response);
                            qnalist = new qna[jsonArray.length()];
                            int s = jsonArray.length();
                            //classname.setText(String.valueOf(s));
                            //classname.setText(nList[1].title);

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int qnum = jsonObject.getInt("qnanum");
                                String qt = jsonObject.getString("qtitle");
                                String q = jsonObject.getString("q");
                                String a = jsonObject.getString("a");
                                String qw = jsonObject.getString("qwriter");
                                String aw = jsonObject.getString("awriter");
                                qnalist[i] = new qna(qnum, qt, q, a, qw, aw);


                            }//showAlist(qnalist);

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }*/
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
}
