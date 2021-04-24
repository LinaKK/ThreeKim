package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



  public class Login extends AppCompatActivity {
    private EditText et_id,et_pw;
    private String aId,aPw;
    private Button btn_lo,btn_sign;

    private SharedPreferences appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //설정값 불러오기
        appData =getSharedPreferences("appData", MODE_PRIVATE);

        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);

        btn_lo = findViewById(R.id.btn_lo);
        btn_sign = findViewById(R.id.btn1_sign);



        //회원가입페이지 이동
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login.this,Sign.class);
                startActivity(intent);
            }
        });



        //로그인버튼
        btn_lo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String Uid=et_id.getText().toString();
                String Upw=et_pw.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jasonObject = new JSONObject(response);
                            boolean success = jasonObject.getBoolean("success");
                            if (success) {
                                String Uid = jasonObject.getString("ID");
                                String Upw = jasonObject.getString("PW");
                                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("log", "User");
                                intent.putExtra("ID", Uid);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Login_Request loginRequest=new Login_Request(Uid,Upw,responseListener);
                RequestQueue queue= Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);
            }
        });

    }

    //https://vvh-avv.tistory.com/127
    //SharedPreferences.setAttribute(getContext(), "Uid", Uid.getText().toString());
    //설정값 저장
   /*public static void setAttribute(Context context, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor =prefs.edit();
        editor.putString(key, value);
        editor.commit();
   }
   //설정 값 읽기
    public static String getAttribute(Context context, String key){
        SharedPreferences prefs =PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, null);}*/

