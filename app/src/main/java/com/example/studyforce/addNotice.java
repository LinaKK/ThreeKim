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

public class addNotice extends AppCompatActivity {
    private EditText title;
    private EditText cont;
    private String classname;
    private int num;
    private TextView e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        title = (EditText) findViewById(R.id.nTitle);
        cont = (EditText) findViewById(R.id.nCont);
        e = (TextView) findViewById(R.id.e);
        Intent intent = getIntent();
        classname = intent.getStringExtra("classname");
        num = intent.getIntExtra("num",0);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));
    }

    public void updateN(View v){
        String t = title.getText().toString();
        String c = cont.getText().toString();
        if (t.length()>0 & c.length()>0){
            if(c.length()>0){
                addNotice(t,c);

            }
            else
                Toast.makeText(this, "제목과 내용을 모두 입력하세요",Toast.LENGTH_SHORT).show();
        }
        Intent intent = getIntent();
        classname = intent.getStringExtra("classname");
        num = intent.getIntExtra("num",0);
        final String name = intent.getStringExtra("name");
        Intent intent2 = new Intent(getApplicationContext(), notice.class);
        intent2.putExtra("num",num);
        intent2.putExtra("classname",classname);
        intent2.putExtra("name",name);
        startActivity(intent2);
    }
    private void addNotice(final String nTitle, String ncontext){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("ntitle", nTitle);
            rj.put("n", ncontext);
            rj.put("classname",classname);
            rj.put("num",num);
        }
        catch (JSONException e){}
        //contextQ.setText(rj.toString());

        String url = "http://118.33.132.221/php/addNotice.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int result = response.getInt("res");
                            if (result == 0) print();


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
        e = (TextView)findViewById(R.id.e);
        e.append(data);
    }
}
