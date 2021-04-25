package com.example.studyforce;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.studyforce.PHPComm;
import okhttp3.MediaType;




public class Sign extends AppCompatActivity {

    private static final String TAG =Sign.class.getSimpleName();
    private EditText si_n, si_id, si_pw,si_email;
    private Button btn1_sign, btn1_idc, btnLogin;
    private AlertDialog dialog;
    private String userID; //sharedPreferences 저장하기 위한 전역변수

    public static final MediaType JSON =MediaType.parse("application/json; charset=utf-8");
    public SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ActionBar ac =getSupportActionBar();
        ac.setTitle("회원가입");

        //값찾기 이름,학번,비번
        si_n = (EditText) findViewById(R.id.si_n);
        si_id = (EditText)findViewById(R.id.si_id);
        si_pw = (EditText)findViewById(R.id.si_pw);
        si_email = (EditText)findViewById(R.id.si_email);

        btn1_sign = (Button) findViewById(R.id.btn1_sign);
        btn1_idc = (Button) findViewById(R.id.btn1_idc);
        /*btnLogin = (Button) findViewById(R.id.btnLoginScreen);*/

        CheckBox cb1=(CheckBox)findViewById(R.id.chstu);
        CheckBox cb2=(CheckBox)findViewById(R.id.chpro);

        //체크박스로 둘 나뉘는 이벤트 추가예정


        //회원가입버튼
          btn1_sign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String Uname = si_n.getText().toString().trim();
                String Uid = si_id.getText().toString().trim();
                userID = Uid;
                String Upw = si_pw.getText().toString().trim();
                String Uemail = si_email.getText().toString().trim();

                //입력안할 시
                if (!Uname.isEmpty() && !Uid.isEmpty() && !Upw.isEmpty() && !Uemail.isEmpty()) {
                    registerUser(Uname, Uid, Upw, Uemail);
                } else {
                    Toast.makeText(getApplicationContext(), "빈칸없이 입력해주세요!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

    }

                //값전달
             private void registerUser(String Uname, String userID, String Upw, String Uemail){
                    Uri.Builder builder =new Uri.Builder()
                            .appendQueryParameter("Uname", Uname)
                            .appendQueryParameter("userID", userID)
                            .appendQueryParameter("Uid", userID)
                            .appendQueryParameter("Upw", Upw)
                            .appendQueryParameter("Uemail", Uemail);
                    String urlParameters =builder.build().getEncodedQuery();
                    new getJSONData().execute(Value.IPADDRESS + "/Sign.php", urlParameters);
                }
                private class getJSONData extends AsyncTask<String, Void, String>{
                    ProgressDialog pdLoading =new ProgressDialog(Sign.this);

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        pdLoading.setMessage("\t회원가입 처리중");
                        pdLoading.setCancelable(false);
                        pdLoading.show();
                    }

                    @Override
                    //링크참조해서 클래스 생성해야하나?
                    protected String doInBackground(String... params){
                        try {
                            return PHPComm.getJson(params[0], params[1]);
                        }catch (Exception e){
                            return new String("Exception" +e.getMessage());
                        }
                    }

                    protected void onPostExecute(String result){
                    pdLoading.dismiss();
                    showJSONResult(result);
                    }
                }

                    protected void showJSONResult(String result){
                        if(result.equalsIgnoreCase("1")) {
                            Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                            storeUserData();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            finish();
                        } else if (result.equalsIgnoreCase("-1")) {
                            Toast.makeText(this, "아이디가 이미 가입되어 있습니다", Toast.LENGTH_SHORT).show();
                            si_id.clearFocus();
                        }else {
                            Toast.makeText(this, "회원 등록에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
                        }
                    }

                    private void storeUserData(){
                        settings =getSharedPreferences("settings", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor =settings.edit();
                        editor.putString("userID",userID);
                        editor.putBoolean("autologin", true);
                        editor.commit();
                    }
                }



