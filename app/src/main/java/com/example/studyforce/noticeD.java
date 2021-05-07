package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class noticeD extends AppCompatActivity {
    String ntitle;
    TextView Dnotitle;
    TextView Ncontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_d);
        Intent intent = getIntent();
        ntitle = intent.getStringExtra("ntitle");
        Dnotitle = findViewById(R.id.noticeTitle);
        Dnotitle.setText(ntitle);
        Ncontext = findViewById(R.id.NoticeContext);
        sendRequest();
    }

    private void sendRequest(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("ntitle", ntitle);
        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/contextNoti.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String context = response.getString("notice"); //날짜 추가
                            Ncontext.setText(context);
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
    private void println(String data){
        Dnotitle = (TextView)findViewById(R.id.noticeTitle);
        Dnotitle.append(data);
    }
}