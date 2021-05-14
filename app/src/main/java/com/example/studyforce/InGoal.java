package com.example.studyforce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class InGoal extends AppCompatActivity {
    TextView Gtitle;
    ListView listview;
    PieChart pieChart;
    Button finishGoal;


    float num = 4; //array length (db table) = 인원 수
    float num2;

    private String name = "홍길동";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_goal);

        finishGoal = (Button)findViewById(R.id.goalfinish);
        pieChart = (PieChart)findViewById(R.id.piechart);

        Intent intent = new Intent(this.getIntent());
        int gnum = intent.getIntExtra("gnum",0);

        getGTitle(gnum);
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
                        //db table에 이름과 수치값 넘겨주기
                        Toast.makeText(getApplicationContext(), "목표를 달성했습니다!!!! Congratulation ^^", Toast.LENGTH_SHORT).show();
                        finishGoal.setText("이미 달성함!");
                        finishGoal.setEnabled(false);
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

    public void getGTitle(int num){
        Gtitle = (TextView)findViewById(R.id.GoalTitle);
        String gTitle = "";
        Gtitle.setText(gTitle);
    }

    //db에서 값 받아오기 그걸로 세팅...

    private ArrayList<PieEntry>piedata(){
        ArrayList<PieEntry> data = new ArrayList<>();
        num2 = (1/num)*100;
        data.add(new PieEntry(num2, name));
        data.add(new PieEntry(num2, "김도희"));
        data.add(new PieEntry(num2, "이"));
        data.add(new PieEntry(num2, "q"));

        return data;
    }

    public void setChart(){
        pieChart = (PieChart)findViewById(R.id.piechart);
        Description des = new Description();
        des.setText("StudyForCE");
        pieChart.setDescription(des);

        PieDataSet dataSet = new PieDataSet(piedata(), "");
        //dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setColors(new int[] {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE});
        pieChart.animateXY(200, 200);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
    }


    //리스트 db에서 가져오기(클래스에 가입된 사람들)
    public void getList(){

    }
}