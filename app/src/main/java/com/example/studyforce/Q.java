package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Q extends AppCompatActivity {
    private EditText setQTitle;
    private EditText setQC;
    private TextView a;
    private String classname;
    private int num;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q);
        setQTitle = (EditText) findViewById(R.id.setQTitle);
        setQC = (EditText) findViewById(R.id.setQC);
        Intent intent = getIntent();
        classname = intent.getStringExtra("classname");
        num = intent.getIntExtra("num",num);
        name = intent.getStringExtra("name");
    }

    public void updateQClick(View v){
        //대화상자 등록? ->확인 ->등록

        String qt = setQTitle.getText().toString();
        String q = setQC.getText().toString();
        if (qt.length() == 0 || q.length() == 0) {
            Toast.makeText(this, "please enter all", Toast.LENGTH_SHORT).show();
        }
        else sendRequest(qt, q);
        Intent intent2 = new Intent(getApplicationContext(), qnaList.class);
        intent2.putExtra("num",num);
        intent2.putExtra("classname",classname);
        intent2.putExtra("name",name);
        startActivity(intent2);

    }

    private void sendRequest(final String qtitle, String q){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("qtitle", qtitle);
            rj.put("q", q);
            rj.put("cname", classname);
            rj.put("num", num);
            //작성자 이름, 클래스이름 넘기기
        }
        catch (JSONException e){}
        //contextQ.setText(rj.toString());

        String url = "http://118.33.132.221/php/Q.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String context = response.getString("qtitle");
                            if (context.length() == qtitle.length()) print();//String- 출력->같음 ==->다름


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
        Intent intent = new Intent(this, qnaList.class);
        startActivity(intent);// 뒤로가기하면 Inclass나오게 -지금은 질문하기로 돌아감

    }


    private void println(String data){
        a = (TextView)findViewById(R.id.a);
        a.append(data);
    }
}
