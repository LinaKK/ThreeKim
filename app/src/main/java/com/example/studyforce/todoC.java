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
import com.android.volley.toolbox.JsonObjectRequest;
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
    String classname;
    int num;

    Button AddTodoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_c);
        Intent intent = getIntent();
        classname = intent.getStringExtra("classname");
        num = intent.getIntExtra("num",0);
        shortTodolist = (ListView) findViewById(R.id.todoc);
        sendRequest2();

        addTodo();
    }


    private void sendRequest2(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("classname", classname);
        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/classtodo.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("res");
                            cTodoList = new classTodo[jsonArray.length()];
                            int s = jsonArray.length();
                            println(String.valueOf(s));

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                int num = jsonObject.getInt("num");
                                String classtodo = jsonObject.getString("todo");
                                String className = jsonObject.getString("classname");
                                int done = jsonObject.getInt("done");
                                String name = jsonObject.getString("name");
                                cTodoList[i] = new classTodo(id, num, classtodo, done, className, name);

                            }showTodo(cTodoList);


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

    private void showTodo(classTodo[] cTodo){
        final List todolist = new ArrayList();
        for(int i=0; i<cTodo.length; i++){
            if (todolist.contains(cTodo[i].classtodo) == false)
                todolist.add(cTodo[i].classtodo); //겹치는거 걸러서 넣어서 갯수 다르게 보일거야
        }
        ArrayAdapter<String> adapterClassTodo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                todolist);
        shortTodolist.setAdapter(adapterClassTodo);

        shortTodolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //goal 번호 넘겨주기
                Intent intent = new Intent(getApplicationContext(), InGoal.class);

                String todo = todolist.get(position).toString();
                intent.putExtra("todo", todo);
                intent.putExtra("num", num);
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
                Intent intent = new Intent(getApplicationContext(), addTodo.class);
                intent.putExtra("cname", classname);
                startActivity(intent);
            }
        });

    }

}
