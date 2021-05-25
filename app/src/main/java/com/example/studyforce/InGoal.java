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
import java.util.HashMap;

public class InGoal extends AppCompatActivity {
    TextView Gtitle;
    ListView listview;
    PieChart pieChart;
    Button finishGoal;

    //db에서 이름까지 가져오게 했어

    //float num=0; //array length (db table) = 인원 수
    float num2;
    int id;
    String todo;
    String t;
    ArrayList donename;
    ArrayList Ndonename;
    HashMap<String, Integer> map;
    int classStuNum;


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
        donename = intent.getStringArrayListExtra("donename");
        Ndonename = intent.getStringArrayListExtra("Ndonename");
        classStuNum = intent.getIntExtra("classStuNum",0);
        t = todo;
        //map = (HashMap<String, Integer>) intent.getSerializableExtra("map");
        //showtodo에서 이름이랑 완료여부 리스트로 만들고 인텐트로 여기로 보냄.


        Gtitle = (TextView)findViewById(R.id.GoalTitle);
        Gtitle.setText(todo);
        stateDone();
        setChart();
        setChartD();



        finishGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(InGoal.this);
                ad.setTitle("목표 완료");
                ad.setMessage("해당 목표를 달성하셨습니까?");
                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "목표를 달성했습니다!!!! Congratulation ^^", Toast.LENGTH_SHORT).show();
                        /*finishGoal.setText("이미 달성함!");
                        finishGoal.setEnabled(false);*/
                        //해당 그래프에 수치 추가+이름은 로그인 성공시때 intent로 받아오기
                        updateDone();//db table에 이름과 수치값 넘겨주기
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
    public void updateDone(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("num", id);
            rj.put("t", t);

        }
        catch (JSONException e){}

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
                            if (res == 1)
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
    //리나야 이 코드 작동 안하는데 res== 0이 done==0을 말하는 거야 아님 뭐야?? 미완성이라고 했었나....?
    //res는 완료버튼 누른거 제대로 업데이트 됐는지 그냥 확인하는 코드여
    //db 확인하려고 만든거라 신경안써도됩니당 => 확인완료
    private void toastM(){
        finishGoal.setText("이미 달성함!");
        finishGoal.setEnabled(false);
        //다시들어가면 버튼눌리는거는 해결했는데 버튼누르고 바로 그래프 바뀌게하는게 문제라 그냥 리스트로 돌아가게하는건 어때
        //뒤로가기해서 돌아가면 완료한거 반영안되어있어서 액티비티 새로 시작해야할듯- 위에 해결하면 안해도되는디 일단 이게 빠를거같아서
    }

    private void print(){
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }


    private void println(String data){
        TextView a;
        a = (TextView)findViewById(R.id.pchardata);
        a.append(data);
    }

    private void stateDone(){
        if (AppHelper.requestQueue == null){
            AppHelper.requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        JSONObject rj = new JSONObject();
        try {
            rj.put("num", id);
            rj.put("t", t);

        }
        catch (JSONException e){}

        String url = "http://118.33.132.221/php/stateDone.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                rj,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int res = response.getInt("res");
                            if (res == 1)
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

    //db에서 값 받아오기 그걸로 세팅...
    private ArrayList<PieEntry>piedata(){
        Intent intent = new Intent();
        todo = intent.getStringExtra("todo");

        ArrayList<Glist> glists = (ArrayList<Glist>)getIntent().getSerializableExtra("glist");
        ArrayList<PieEntry> data = new ArrayList<>();

        //data.add(new PieEntry(num2, name));
        //자꾸 오류남...

        double persent = 1/(double)classStuNum*100;

        for (int i = 0; i<donename.size(); i++){
            data.add(new PieEntry((float) persent, donename.get(i).toString()));
        }
        for (int i = 0; i<Ndonename.size(); i++){
            data.add(new PieEntry(0, Ndonename.get(i).toString()));
        }

       /* println(data.toString());
        println(donename.toString());
        println(Integer.toString(classStuNum));*/
        //println(Integer.toString(persent));
        return data;
    }

    public void setChart(){

        pieChart = (PieChart)findViewById(R.id.piechart);
        Description des = new Description();
        des.setText("StudyForCE");
        pieChart.setDescription(des);

        pieChart.setHoleColor(Color.WHITE);

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

    private ArrayList<PieEntry>piedataD(){
        Intent intent = new Intent();
        todo = intent.getStringExtra("todo");

        ArrayList<Glist> glists = (ArrayList<Glist>)getIntent().getSerializableExtra("glist");
        ArrayList<PieEntry> data = new ArrayList<>();

        double persent = 1/(double)classStuNum*100;

        for (int i = 0; i<donename.size(); i++){
            data.add(new PieEntry((float) persent, donename.get(i).toString()));
        }
        for (int i = 0; i<Ndonename.size(); i++){
            data.add(new PieEntry((float) persent, Ndonename.get(i).toString()));
        }

        return data;
    }

    public void setChartD(){

        pieChart = (PieChart)findViewById(R.id.donepiechart);
        Description des = new Description();
        des.setText("");
        pieChart.setDescription(des);

        pieChart.setHoleColor(Color.WHITE);

        int orange = ContextCompat.getColor(this, R.color.orange);
        int skyblue = ContextCompat.getColor(this,R.color.skyblue);
        int pink = ContextCompat.getColor(this, R.color.pink);

        PieDataSet dataSet = new PieDataSet(piedataD(), "");
        //dataSet.setColors(ColorTemplate.MATERIAL_COLORS); => 색 테마
        dataSet.setColors(new int[] {Color.RED,orange, Color.YELLOW,Color.GREEN,skyblue, Color.BLUE, pink});
        pieChart.animateXY(200, 200);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.BLACK);
        pieChart.getLegend().setEnabled(false);

        pieChart.setData(data);

    }



}