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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



  public class Login extends AppCompatActivity {
      private EditText et_id, et_pw;
      private String aId, aPw;
      private Button btn_lo, btn_sign;

      private SharedPreferences appData;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_login);
          setTitle("로그인");

          //설정값 불러오기
          appData = getSharedPreferences("appData", MODE_PRIVATE);

          et_id = findViewById(R.id.et_id);
          et_pw = findViewById(R.id.et_pw);

          btn_lo = findViewById(R.id.btn_lo);
          btn_sign = findViewById(R.id.btn1_sign);

          //회원가입페이지 이동
          btn_sign.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(Login.this, Sign.class);
                  startActivity(intent);
              }
          });

          btn_lo.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  login();
              }
          });
      }


      private void login() {
          final String Uid = et_id.getText().toString();
          String Upw = et_pw.getText().toString();

          Response.Listener<String> responseListener = new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  try {
                      JSONObject jsonObject = new JSONObject(response);
                      String success = jsonObject.getString("success");
                      if (success != null && success.equals("1")) {
                          Toast.makeText(getApplicationContext(), "로그인 성공!", Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(Login.this, personal_page.class);
                          intent.putExtra("Uid", Uid);
                          startActivity(intent);
                          finish();
                      } else {
                          Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_SHORT).show();
                          return;
                      }
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
              }
          };

          Response.ErrorListener errorListener = new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  Toast.makeText(getApplicationContext(), "로그인 처리시 에러발생!", Toast.LENGTH_SHORT).show();
                  return;
              }
          };
          Login_Request loginRequest = new Login_Request(Uid, Upw, responseListener, errorListener);
          loginRequest.setShouldCache(false);

          RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
          queue.add(loginRequest);
      }
  }
