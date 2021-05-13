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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_goal);

        finishGoal = (Button)findViewById(R.id.goalfinish);
        Gtitle = (TextView)findViewById(R.id.GoalTitle);
        pieChart = (PieChart)findViewById(R.id.piechart);
        ArrayList pieuser = new ArrayList();

        pieuser.add(new PieEntry(50f, "김"));
        pieuser.add(new PieEntry(100f, "이"));
        pieuser.add(new PieEntry(75f,"박"));
        pieuser.add(new PieEntry(50f, "비어있음"));
        Description des = new Description();
        des.setText("StudyForCE");
        pieChart.setDescription(des);
        PieDataSet dataSet = new PieDataSet(pieuser, "");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieChart.animateXY(200, 200);
        PieData data =new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);

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

    //리스트 db에서 가져오기(클래스에 가입된 사람들)
    public void getList(){

    }
}