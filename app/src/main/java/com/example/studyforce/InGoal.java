package com.example.studyforce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InGoal extends AppCompatActivity {
    TextView Gtitle;
    ListView listview;
    PieChart pieChart;
    Button finishGoal;

    //db에서 이름까지 가져오게 했어

    float num ; //array length (db table) = 인원 수
    float num2;
    int id;
    String todo;

    private String name = "홍길동";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_goal);

        finishGoal = (Button)findViewById(R.id.goalfinish);
        pieChart = (PieChart)findViewById(R.id.piechart);

        Intent intent = new Intent(this.getIntent());
        int gnum = intent.getIntExtra("gnum",0);
        id = intent.getIntExtra("num", 0);
        todo = intent.getStringExtra("todo");
        //showtodo에서 이름이랑 완료여부 리스트로 만들고 인텐트로 여기로 보냄.


        Gtitle = (TextView)findViewById(R.id.GoalTitle);
        Gtitle.setText(todo);
        setChart();

        listview = (ListView)findViewById(R.id.goalperson);
        getList();

        finishGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(InGoal.this);
                ad.setTitle("목표 완료");
                ad.setMessage("해당 목표를 달성하셨습니까?");
                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //해당 그래프에 수치 추가+이름은 로그인 성공시때 intent로 받아오기
                        updateDone(id, todo);//db table에 이름과 수치값 넘겨주기
                        //완료 확인 DB update 다음에 실행되게 밑으로 넘겼습니다.
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });
    }

    //달성하면 db에 update
    public void updateDone(int num, String todo){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("num", num);
            rj.put("todo", todo);
        }
        catch (JSONException e){}
        //contextQ.setText(rj.toString());

        String url = "http://118.33.132.221/php/updateDone.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int res = response.getInt("res");
                            if (res == 0)
                                toastM();
                            else
                                print();

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
    private void toastM(){
        Toast.makeText(getApplicationContext(), "목표를 달성했습니다!!!! Congratulation ^^", Toast.LENGTH_SHORT).show();
        finishGoal.setText("이미 달성함!");
        finishGoal.setEnabled(false);
    }

    private void print(){
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }


    private void println(String data){
        //a = (TextView)findViewById(R.id.a);
        //a.append(data);
    }

    //리스트 db에서 가져오기(클래스에 가입된 사람들)
    public void getList(){

    }
    //db에서 값 받아오기 그걸로 세팅...
    private ArrayList<PieEntry>piedata(){
        Intent intent = new Intent();
        todo = intent.getStringExtra("todo");

        ArrayList<Glist> glists = (ArrayList<Glist>)getIntent().getSerializableExtra("glist");
        ArrayList<PieEntry> data = new ArrayList<>();
        num2 = (1/num)*100;
        data.add(new PieEntry(num2, name));
        for(int i =0; i<glists.size(); i++){
            if (glists.get(i).title == todo){
                if(glists.get(i).done == 0)
                    data.add(new PieEntry(num2, glists.get(i).name));
            }
        }
        return data;
    }

    public void setChart(){
        num=7;

        pieChart = (PieChart)findViewById(R.id.piechart);
        Description des = new Description();
        des.setText("StudyForCE");
        pieChart.setDescription(des);

        int orange = ContextCompat.getColor(this, R.color.orange);
        int skyblue = ContextCompat.getColor(this,R.color.skyblue);
        int pink = ContextCompat.getColor(this, R.color.pink);

        PieDataSet dataSet = new PieDataSet(piedata(), "");
        //dataSet.setColors(ColorTemplate.MATERIAL_COLORS); => 색 테마
        dataSet.setColors(new int[] {Color.RED,orange, Color.YELLOW,Color.GREEN,skyblue, Color.BLUE, pink});
        pieChart.animateXY(200, 200);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
    }



}