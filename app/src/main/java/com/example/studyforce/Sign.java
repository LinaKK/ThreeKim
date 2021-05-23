package com.example.studyforce;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Sign extends AppCompatActivity {

    private static final String TAG =Sign.class.getSimpleName();
    private EditText si_n, si_id, si_pw,si_email;
    private Button btn1_sign, btn1_idc, btnLogin;
    private AlertDialog dialog;
    private String userID; //sharedPreferences 저장하기 위한 전역변수


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("회원가입");

        //값찾기 이름,학번,비번
        si_n = (EditText) findViewById(R.id.si_n);
        si_id = (EditText) findViewById(R.id.si_id);
        si_pw = (EditText) findViewById(R.id.si_pw);
        si_email = (EditText) findViewById(R.id.si_email);

        btn1_sign = (Button) findViewById(R.id.btn1_sign);
        btn1_idc = (Button) findViewById(R.id.btn1_idc);
        /*btnLogin = (Button) findViewById(R.id.btnLoginScreen);*/

        CheckBox cb1 = (CheckBox) findViewById(R.id.chstu);
        CheckBox cb2 = (CheckBox) findViewById(R.id.chpro);

        //체크박스로 둘 나뉘는 이벤트 추가예정 ->학생은 0 교수님 1

        btn1_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sign();
            }
        });
    }

    private void Sign(){
        String Uname = si_n.getText().toString();
        int Uid = Integer.parseInt(si_id.getText().toString());
        //우리 id string이 아니라 num아니었나...?
        String Upw = si_pw.getText().toString();
        String Uemail = si_email.getText().toString();
        int di = 0; //0 or 1

        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("num", Uid);
            rj.put("name", Uname);
            rj.put("pw", Upw);
            rj.put("division", di);
            rj.put("email", Uemail);

        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/Sign.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int res= response.getInt("res");
                            if (res == 0) print();


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
        Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

    private void println(String data){
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}

