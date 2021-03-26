package com.example.studyforce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONException;

import java.text.BreakIterator;


/*public class Sign extends AppCompatActivity {

    private EditText si_n, si_id, si_pw, si_pwc;
    private Button btn1_sign, btn1_idc;
    private AlertDialog dialog;
    private boolean validate = false; //학번 중복체크 기능

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        //값찾기 이름,학번,비번,비밀번호확인
        si_n=findViewById(R.id.si_n);
        si_id=findViewById(R.id.si_id);
        si_pw=findViewById(R.id.si_pw);
        si_pwc=findViewById(R.id.si_pwc);

        btn1_sign=findViewById(R.id.btn1_sign);
        btn1_sign.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (Uname.getText().toString().length ==0){
                    Toast.makeText(Sign.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    Uname.requestFocus();
                    return;
                }
                if (Uid.getText().toString().length ==0){
                    Toast.makeText(Sign.this, "학번을 입력하세요", Toast.LENGTH_SHORT).show();
                    Uid.requestFocus();
                    return;
                }
                if (Upw.getText().toString().length ==0){
                    Toast.makeText(Sign.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    Upw.requestFocus();
                    return;
                }
                if (Uemail.getText().toString().length ==0){
                    Toast.makeText(Sign.this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    Uemail.requestFocus();
                    return;
                }
                if(!Upwc.getText().toString().equals(Upw.getText().toString()) ) {
                    Toast.makeText(Sign.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    Upwc.setText("");
                    Upwc.requestFocus();
                    return;
                }
            }
        });

        btn1_idc =findViewById(R.id.btn1_idc);
        btn1_idc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String Uid = si_id.getText().toString();
                if (validate) {
                    return; //중복체크 완료
                }
                if (Uid.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Sign.this);
                    dialog = builder.setMessage("ID가 빈칸입니다.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;

                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Sign.this);
                                dialog = builder.setMessage("사용 가능한 ID입니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                si_id.setEnabled(false);
                                validate = true;
                                btn1_idc.setText("확인");

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Sign_Request.this);
                                dialog = builder.setMessage(" 사용 불가능한 ID 입니다.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }; // 이해필요..
                ValidateRequest validateRequest = new ValidateRequest(Uid, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Sign_Request.this);
                queue.add(validateRequest);
            }
        });
        //회원가입버튼 눌렀을 때
        btn1_sign=findViewById(R.id.btn1_sign);
        btn1_sign.setOnClickListener(new View.onClickListener(){
            @Override
            public void onClick(View v) {

            }
        });


        }


        //추가할 것: 중복체크 보안v, 서버 연동 후 이름 체크, 회원가입누르면 화면전환(로그인)

    }*/

