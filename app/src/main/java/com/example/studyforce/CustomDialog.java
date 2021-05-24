package com.example.studyforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

public class CustomDialog  {
    private Context context;

    public CustomDialog(Context context){
        this.context=context;
    }

    public void callFunction(){
        final Dialog dig = new Dialog(context);
        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dig.setContentView(R.layout.activity_custom_dialog);
        dig.show();

        final CalendarView calendarView = (CalendarView)dig.findViewById(R.id.calendar);
        final ListView listView = (ListView)dig.findViewById(R.id.listview);
        final Button exit = (Button)dig.findViewById(R.id.exitt);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig.dismiss();
            }
        });
    }

}