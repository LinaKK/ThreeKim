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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

        //체크박스로 둘 나뉘는 이벤트 추가예정

        btn1_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sign();
            }
        });
    }
    private void Sign() {
        String Uname = si_n.getText().toString();
        int Uid = Integer.parseInt(si_id.getText().toString());
         //우리 id string이 아니라 num아니었나...?
        String Upw = si_pw.getText().toString();
        String Uemail = si_email.getText().toString();

        Response.Listener<String> resposneListener =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) { //응답 성공시 나올
                try{
                    JSONObject jsonObject =new JSONObject(response);
                    String success =jsonObject.getString("success");
                    if(success != null & success.equals("1")){
                        Toast.makeText(getApplicationContext(),"회원가입 성공!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Sign.this,Login.class);
                        startActivity(intent);
                        //finish();
                    } else {
                        Toast.makeText(getApplicationContext(),"회원가입 실패!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener =new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(),"회원가입 처리시 에러발생!",Toast.LENGTH_SHORT).show();
                return;
            }
        };

        Sign_Request SignRequest = new Sign_Request(Uname,Uid, Upw,Uemail,resposneListener,errorListener);
        SignRequest.setShouldCache(false);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(SignRequest);
    }
}

