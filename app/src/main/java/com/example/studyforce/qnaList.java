package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class qnaList extends AppCompatActivity {
    private static qna qnalist[];
    private ListView qlist;
    public TextView qnatitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_list);
        qlist = (ListView) findViewById(R.id.qlist);
        sendRequest2();

    }

    private void sendRequest2(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        String url = "http://118.33.132.221/php/qnalist.php";
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
                            qnalist = new qna[jsonArray.length()];
                            int s = jsonArray.length();
                            //classname.setText(String.valueOf(s));
                            //classname.setText(nList[1].title);

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int qnum = jsonObject.getInt("qnanum");
                                String qt = jsonObject.getString("qtitle");
                                String q = jsonObject.getString("q");
                                String a = jsonObject.getString("a");
                                String qw = jsonObject.getString("qwriter");
                                String aw = jsonObject.getString("awirter");
                                qnalist[i] = new qna(qnum, qt, q, a, qw, aw);


                            }showQnalist(qnalist);
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

    private void showQnalist(qna[] list){
        final List qnal = new ArrayList();
        for(int i=0; i<list.length; i++){
            qnal.add(list[i].qtitle);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                qnal);
        qlist.setAdapter(adapter);

       /* 클릭하면 detailQ로 넘어가기 + qnanum detailQ로 넘기기 - 오류나는데 할수있는분 부탁..
       qlist.setOnClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(this, detailQ.class);
                intent.putExtra("qtitle", Integer.parseInt(qnal.get(position).toString()));
                startActivityForResult(intent,0);

            }

        });*/

    }


    private void println(String data){
        qnatitle = (TextView)findViewById(R.id.qnatitle);
        qnatitle.append(data);
    }


}
