package com.example.studyforce;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class A extends AppCompatActivity {
    private TextView setQTitle;
    private EditText setA;
    public String  qtitle;
    TextView b;
    TextView qt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        setA = (EditText) findViewById(R.id.setA);
        qt = (TextView) findViewById(R.id.qt);
        Intent intent = getIntent();
        qtitle = intent.getStringExtra("qtitle");
        qt.setText(qtitle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

    }
    public void updateAClick(View v){
        //대화상자 등록? ->확인 ->등록

        String a = setA.getText().toString();
        if (a.length() == 0) {
            Toast.makeText(this, "please enter all", Toast.LENGTH_SHORT).show();
        }
        else sendRequest(qtitle, a);

    }

    private void sendRequest(final String qtitle, String a){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("qtitle", qtitle);
            rj.put("a", a);
            //작성자 이름 넘기기
        }
        catch (JSONException e){}
        //contextQ.setText(rj.toString());

        String url = "http://118.33.132.221/php/A.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int context = response.getInt("qnanum");
                            if (context>0) print();//String- 출력->같음 ==->다름


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
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        //자동 뒤로가기

    }


    private void println(String data){
        b = (TextView)findViewById(R.id.b);
        b.append(data);
    }



}

