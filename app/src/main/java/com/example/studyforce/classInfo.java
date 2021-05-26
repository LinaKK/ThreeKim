package com.example.studyforce;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.List;

public class classInfo extends AppCompatActivity {
    TextView cSub;
    ListView cPList;
    Button exit;
    String cname;
    String sub;
    private static classStu[] cStu;
    private static stulist[] stulist;
    private cStuListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        cSub = (TextView)findViewById(R.id.cSubject);
        cPList = (ListView)findViewById(R.id.cPersonList);

        Intent intent = getIntent();
        cname = intent.getStringExtra("cname");

        getSub();
        getStu();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFC107));


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
                        }cSub.setText(sub);

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

    private void getStu(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("cname", cname);
        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/getStu.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //int tdnum = response.getInt("ress");
                            JSONArray jsonArray = response.getJSONArray("res");
                            cStu = new classStu[jsonArray.length()];
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String stuName = jsonObject.getString("name");
                                int dnum = jsonObject.getInt("done");
                                cStu[i] = new classStu(stuName, dnum, 3);
                            }showStulist(cStu);


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

    private void showStulist(classStu[] list){
        final List slist = new ArrayList();
        for(int i=0; i<list.length; i++){
            if (slist.contains(list[i].name) == false)
                slist.add(list[i].name);//겹치는거 걸러서 넣어서 갯수 다르게 보일거야
        }

        ArrayAdapter<String> adapterClassTodo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                slist);
        cPList.setAdapter(adapterClassTodo);
       /* List l1 = new ArrayList();
        List l2 = new ArrayList();
        List l3 = new ArrayList();
        int dn=0;
        for (int i=0; i<list.length; i++){
            if (l1.contains(list[i].name) == false){
                l1.add(list[i].name);
                //l2.add(list[i].doneNum);
                //l2.add(list[i].todoNum);
            }
        }
        for (int i = 0; i<l1.size();i++){
            int j=0;
            while(j<list.length){
                if(l1.get(i) == list[j].name ){
                    if (list[j].doneNum == 1) {dn++;}}
                j++;
            }
            l2.add(dn);
            dn =0;
            l3.add(list[i].todoNum);
        }

        stulist = new stulist[l1.size()];
        for (int i=0; i< l1.size(); i++){
            stulist[i]= new stulist(l1.get(i).toString(), Integer.parseInt(l2.get(i).toString()), Integer.parseInt(l3.get(i).toString()));
        }
        String a = String.valueOf(l1.size());
        cSub.setText(a);

        mAdapter = new cStuListAdapter(this, stulist);
        cPList.setAdapter(mAdapter);*/

    }


    private void println(String data){
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();}
}