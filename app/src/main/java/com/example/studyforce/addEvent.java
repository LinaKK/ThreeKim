package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class addEvent extends AppCompatActivity {

    private String Date="";
    private EditText Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        updateResult();
        Data = (EditText) findViewById(R.id.eData);
    }

    private void updateResult(){
        TextView eDate = (TextView) findViewById(R.id.eDate);
        eDate.setText("날짜: "+ Date);
    }

    private void mOnclick(View v) {
        Calendar c = Calendar.getInstance();
        switch(v.getId()){
            case R.id.selectEDate:
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(this, onDateSetListener, year, month, day).show();
                break;

            case R.id.updateE:
        String data = Data.getText().toString();
        if(data.length()>0){
            //이벤트 날짜, 내용 추가
            }
        else {
            Toast.makeText(this, "이벤트 내용을 입력하세요",Toast.LENGTH_SHORT).show();
        }
        break;

        }
    }


    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Date = String.format("%d년 %d월 %d일", year, month+1, dayOfMonth);
            updateResult();
        }
    };



}
