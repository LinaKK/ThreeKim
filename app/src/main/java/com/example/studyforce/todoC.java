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

public class todoC extends AppCompatActivity {
    private static classTodo[] cTodoList;
    private ListView shortTodolist;
    TextView todolistinclass;

    Button AddTodoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_c);
        shortTodolist = (ListView) findViewById(R.id.todoc);
        sendRequest3();

        addTodo();
    }

    private void sendRequest3() {
        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        String url = "http://118.33.132.221/php/classtodo.php";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // processResponse(response);
                        // classname.setText(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            cTodoList = new classTodo[jsonArray.length()];
                            int s = jsonArray.length();
                            //classname.setText(String.valueOf(s));
                            //classname.setText(nList[1].title);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                int num = jsonObject.getInt("num");
                                int done = jsonObject.getInt("done");
                                String classtodo = jsonObject.getString("todo");
                                String className = jsonObject.getString("classname");
                                cTodoList[i] = new classTodo(id, num, classtodo, done, className);

                            }
                            showTodo(cTodoList);
                            //classname.setText(nList[1].notice);
                        } catch (JSONException e) {
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

    private void showTodo(classTodo[] cTodo){
        List todolist = new ArrayList();
        for(int i=0; i<cTodo.length; i++){
            todolist.add(cTodo[i].classtodo);
        }
        ArrayAdapter<String> adapterClassTodo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                todolist);
        shortTodolist.setAdapter(adapterClassTodo);

        shortTodolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //goal 번호 넘겨주기
                Intent intent = new Intent(getApplicationContext(), InGoal.class);
                startActivity(intent);
            }
        });
    }
    private void println(String data){
        todolistinclass = (TextView) findViewById(R.id.todolistinclass);
        todolistinclass.append(data);
    }

    private void addTodo(){
        AddTodoC = (Button)findViewById(R.id.addTodoC);
        AddTodoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addEvent.class);
                startActivity(intent);
            }
        });

    }

}
