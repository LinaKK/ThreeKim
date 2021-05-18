package com.example.studyforce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CreateByStu extends AppCompatActivity {
    private String espw = "";
    EditText Ename, Egoal, Esub;
    Button cAdd;

    //1, 0으로 비교

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_by_stu);

        Ename = (EditText)findViewById(R.id.edCname);
        Egoal = (EditText)findViewById(R.id.edCgoal);
        Esub = (EditText)findViewById(R.id.edSub);

        cAdd = (Button)findViewById(R.id.create);

        final TextView spw = (TextView) findViewById(R.id.spw);
        spw.setVisibility(View.INVISIBLE);

        RadioGroup jobs = findViewById(R.id.jobs);
        final EditText editspw = (EditText)findViewById(R.id.edspw);

        editspw.setVisibility(View.INVISIBLE);

        jobs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.open){//공개
                    editspw.setVisibility(View.INVISIBLE);
                    spw.setVisibility(View.INVISIBLE);
                }
                else if(checkedId==R.id.secret){//비공개
                    editspw.setVisibility(View.VISIBLE);
                    espw = editspw.getText().toString();
                    spw.setVisibility(View.INVISIBLE);
                }
            }
        });

        //class create
        cAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "클래스를 생성했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    public void mOnCLick(View V) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.secretpw, null);
        builder.setView(layout);
        final EditText editspw = (EditText) layout.findViewById(R.id.editspw);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                spw = editspw.getText().toString();
                updateResult();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }
     */


}


