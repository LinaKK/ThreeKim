package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class classInfo extends AppCompatActivity {
    TextView cSub;
    ListView cPList;
    Button exit;
    String cname;
    String sub;
    private static classStu[] cStu;
    private cStuListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        cSub = (TextView)findViewById(R.id.cSubject);
        cPList = (ListView)findViewById(R.id.cPersonList);

        Intent intent = new Intent();
        String cname = intent.getStringExtra("cname");

        getSub();
        cSub.setText(sub);
        getStu();


        exit = (Button)findViewById(R.id.exit4);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getSub(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("cname", cname);
        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/getSub.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            sub = response.getString("subject");

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

    private void getStu(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("classname", cname);
        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/noticeTitle.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("res");
                            cStu = new classStu[jsonArray.length()];

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String stuName = jsonObject.getString("name");
                                int dnum = jsonObject.getInt("dnum");
                                int tdnum = jsonObject.getInt("tdnum");
                                cStu[i] = new classStu(stuName, dnum, tdnum);

                            }showStulist(cStu);

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

    private void showStulist(classStu[] list){
        mAdapter = new cStuListAdapter(this, list);
        cPList.setAdapter(mAdapter);//php만드는중 아직안나와요

    }


    private void println(String data){
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();}
}