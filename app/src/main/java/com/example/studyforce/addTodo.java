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

public class addTodo extends AppCompatActivity {
    String cname, name;
    int num;
    EditText contextTD;
    TextView TD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        Intent intent = getIntent();
        cname = intent.getStringExtra("cname");
        num = intent.getIntExtra("num", num);
        name = intent.getStringExtra("name");
        contextTD = findViewById(R.id.setTodo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));

    }
    public void updateTDClick(View view){
        String TD = contextTD.getText().toString();
        if (contextTD != null)
            updateTD(TD);
        else
            Toast.makeText(this, "내용을 입력하세요!",Toast.LENGTH_SHORT).show();

        Intent intent2 = new Intent(getApplicationContext(), todoC.class);
        intent2.putExtra("num",num);
        intent2.putExtra("classname",cname);
        intent2.putExtra("name",name);
        startActivity(intent2);
    }

    //php작성중
    private void updateTD(String todo){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("todo", todo);
            rj.put("cname", cname);
            //작성자 이름, 클래스이름 넘기기
        }
        catch (JSONException e){}
        //contextQ.setText(rj.toString());

        String url = "http://118.33.132.221/php/addTodo.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int res = response.getInt("res");
                            if (res == 0);//String- 출력->같음 ==->다름
                                print();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //println("error -> " + error.getMessage());
                    }
                }
        );
        AppHelper.requestQueue.add(jsonObjectRequest);

    }

    private void print(){
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        // todolist로
    }

    private void println(String data){
        TD = (TextView)findViewById(R.id.TD);
        TD.append(data);
    }


}