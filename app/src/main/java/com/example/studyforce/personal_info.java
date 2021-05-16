package com.example.studyforce;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class personal_info extends AppCompatActivity {

    Button exit;
    TextView uname;
    TextView uid;
    TextView umail;
    /*String username;
    String usermail;
    int userid;*/
    TextView a;
    int num;
    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

        uname = findViewById(R.id.uname);
        uid = findViewById(R.id.uid);
        umail = findViewById(R.id.umail);
        exit = (Button)findViewById(R.id.exit3);

        Intent intent = getIntent();
        num = intent.getIntExtra("num", 0);
        String Snum = Integer.toString(num);
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");


        uname.setText(name);
        uid.setText(Snum);
        umail.setText(email);

        //메인으로 나가기
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), personal_page.class);
                intent.putExtra("num",num);
                intent.putExtra("name", name);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }





}