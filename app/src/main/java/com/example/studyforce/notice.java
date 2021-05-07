package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class notice extends AppCompatActivity {
    private ListView shortNoticeList;
    private static noticeList[] nList;
    private TextView TitleNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        shortNoticeList = (ListView) findViewById(R.id.noticelist);
        sendRequest2();

    }

    public void OnClick(View v){
        Intent intent;
        switch (v.getId()) {
            case R.id.addnotice:
                intent = new Intent(this, addNotice.class);
                startActivity(intent);
                break;
        }
    }

    private void sendRequest2(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        String url = "http://118.33.132.221/php/noticeTitle.php";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // processResponse(response);
                        // classname.setText(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            nList = new noticeList[jsonArray.length()];
                            int s = jsonArray.length();
                            //classname.setText(String.valueOf(s));
                            //classname.setText(nList[1].title);

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int noticeNum = jsonObject.getInt("noticenum");
                                String className = jsonObject.getString("classname");
                                int num = jsonObject.getInt("num");
                                String title = jsonObject.getString("title");
                                String notice = jsonObject.getString("notice");
                                nList[i] = new noticeList(noticeNum, num, className, title, notice);


                            }showNotiList(nList);
                            //classname.setText(nList[1].notice);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("error -> " + error.getMessage());
                    }
                }

        );
        AppHelper.requestQueue.add(request);
    }

    public void showNotiList(noticeList[] notice){
        final List nolist = new ArrayList();
        for(int i=0; i<notice.length; i++){
            nolist.add(nList[i].title);
        }
        ArrayAdapter<String> adapterNotice = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                nolist);
        shortNoticeList.setAdapter(adapterNotice);

        shortNoticeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), noticeD.class);
                String noticeTitle = nolist.get(position).toString();
                intent.putExtra("ntitle", noticeTitle);
                startActivity(intent);
            }
        });

    }

    private void println(String data){
        TitleNotice = (TextView)findViewById(R.id.TitleNotice);
        TitleNotice.append(data);
    }
}
