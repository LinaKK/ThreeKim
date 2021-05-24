package com.example.studyforce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateByStu extends AppCompatActivity {
    //private String espw = "";
    EditText Ename;
    EditText Egoal;
    EditText Esub;
    Button cAdd;
    EditText editspw;
    TextView spw;
    int num;
    String name;

    //1, 0으로 비교 -뭐가 공개인지 안정해진거같아서 0을 공개로 할게여..? => ok

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_by_stu);

        Intent intent = getIntent();
        num = intent.getIntExtra("num", 0);
        name = intent.getStringExtra("name");

        editspw = findViewById(R.id.edspw);
        spw = findViewById(R.id.spw);

        spw.setVisibility(View.INVISIBLE);
        editspw.setVisibility(View.INVISIBLE);
        RadioGroup jobs = findViewById(R.id.jobs);
        jobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.open){//공개
                    editspw.setVisibility(View.INVISIBLE);
                    spw.setVisibility(View.INVISIBLE);
                    createOC();
                }
                else if(checkedId==R.id.secret){//비공개
                    editspw.setVisibility(View.VISIBLE);
                    spw.setVisibility(View.VISIBLE);
                    createSC();
                }
            }
        });
    }

    public void createOC(){
        cAdd = findViewById(R.id.create);
        Ename = findViewById(R.id.edCname);
        Egoal = findViewById(R.id.edCgoal);
        Esub = findViewById(R.id.edSub);
        cAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createClassS("ThreeK2","C++","A",1,1234);
                String ename= Ename.getText().toString();
                String egoal = Egoal.getText().toString();
                String esub = Esub.getText().toString();

                createClassS(ename,esub,egoal,0,0);

                Toast.makeText(getApplicationContext(), "클래스를 생성했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createSC(){
        cAdd = findViewById(R.id.create);
        Ename = findViewById(R.id.edCname);
        Egoal = findViewById(R.id.edCgoal);
        Esub = findViewById(R.id.edSub);
        editspw = findViewById(R.id.edspw);
        spw = findViewById(R.id.spw);
        cAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createClassS("ThreeK2","C++","A",1,1234);
                String ename= Ename.getText().toString();
                String egoal = Egoal.getText().toString();
                String esub = Esub.getText().toString();
                int password = Integer.parseInt(editspw.getText().toString());

                createClassS(ename,esub,egoal,1,password);

                Toast.makeText(getApplicationContext(), "클래스를 생성했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    public void mOnCLick(View V) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.secretpw, null);
        builder.setView(layout);
        final EditText editspw = (EditText) layout.findViewById(R.id.editspw);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                spw = editspw.getText().toString();
                updateResult();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }
     */

    public void createClassS(String classname, String subject, String goal, int open ,int pw){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("cname", classname);
            rj.put("subject", subject);
            rj.put("goal",goal);
            rj.put("open",open);
            rj.put("pw", pw);
            rj.put("num",num);
            rj.put("name", name);
        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/createByS.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int res = response.getInt("res");
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
        Toast.makeText(this, "클래스를 생성했습니다.", Toast.LENGTH_SHORT).show();
    }//getApplicationContext()

    private void println(String data){
        TextView cbs;
        cbs = (TextView)findViewById(R.id.createbyS);
        cbs.append(data);
    }


}


