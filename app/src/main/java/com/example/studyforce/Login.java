package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
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
          //btn_lo = findViewById(R.id.btn_lo);
          //btn_sign = findViewById(R.id.btn1_sign);

          //회원가입페이지 이동
         /* btn_sign.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(Login.this, Sign.class);
                  startActivity(intent);
              }
          });

          btn_lo.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //login();
              }
          });*/


      }

      public void btn_sign(View view){
          Intent intent = new Intent(this, Sign.class);
          startActivity(intent);
      }

      public void btn_lo(View view){
          String Sid = et_id.getText().toString();
          int id = Integer.parseInt(Sid);
          String pw = et_pw.getText().toString();

          if (Sid == null || pw == null)
              Toast.makeText(this, "please enter all", Toast.LENGTH_SHORT).show();
          else
              loginsendRequest(id, pw);
      }

      private void loginsendRequest(final int ed_id, String ed_pw){
          if (AppHelper.requestQueue == null){
              AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
          }
          JSONObject rj = new JSONObject();
          try {
              rj.put("id", ed_id);
              rj.put("pw", ed_pw);
          }
          catch (JSONException e){}
          //contextQ.setText(rj.toString());

          String url = "http://118.33.132.221/php/Login.php";

          JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                  Request.Method.POST,
                  url,
                  rj,
                  new Response.Listener<JSONObject>(){
                      @Override
                      public void onResponse(JSONObject response) {

                          try {
                              /*TextView a;
                              a = (TextView)findViewById(R.id.error);
                              a.setText(response.toString());*/
                              int result = response.getInt("result");
                              String name = response.getString("name");
                              String email = response.getString("email");
                              if (result==0){
                                  start(ed_id, name, email);
                              /*println(response.toString());*/}

                              else
                                  loginError();

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
          TextView a;
          a = (TextView)findViewById(R.id.error);
          a.append(data);
      }

      private void start(int num, String name, String email){
          Intent intent = new Intent(this, personal_page.class);
          intent.putExtra("num", num);
          intent.putExtra("name", name);
          intent.putExtra("email", email);
          startActivity(intent);
      }

      private void loginError(){
          Toast.makeText(this,"id or pw is incorrect",Toast.LENGTH_SHORT).show();
      }

      long pressTime;
      public void onBackPressed(){
          if(System.currentTimeMillis() - pressTime < 2000){
              finishAffinity();
              return;
          }
          Toast.makeText(this, "한 번 더 누르시면 앱이 종료됩니다", Toast.LENGTH_LONG).show();
          pressTime=System.currentTimeMillis();
      }


      /*private void login() {
          final int Uid = Integer.parseInt(et_id.getText().toString());
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
                          //finish();
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
          //loginRequest.setShouldCache(false);

          RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
          queue.add(loginRequest);
      }*/



  }
