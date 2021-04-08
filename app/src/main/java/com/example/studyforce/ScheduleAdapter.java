package com.example.studyforce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

public class ScheduleAdapter extends BaseAdapter {

    private Context ctx;

    private ArrayList<Schedule> ScheduleAdapter = new ArrayList<Schedule>();

    public ScheduleAdapter() {

    }

    @Override
    public int getCount() {
        return ScheduleAdapter.size();
    }

    @Override
    public Object getItem(int i) {
        return ScheduleAdapter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos =i;
        final Context context = viewGroup.getContext();

        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.schedule_list, viewGroup, false);
        }

        //제목
        TextView text1 = (TextView)view.findViewById(R.id.sTitle);
        //시작날짜
        TextView text2 = (TextView)view.findViewById(R.id.sdate);
        //종료날짜
        TextView text3 = (TextView)view.findViewById(R.id.edate);
        //내용
        TextView text4 = (TextView)view.findViewById(R.id.sCont);


        Schedule schedule_list = ScheduleAdapter.get(i);
        text1.setText(schedule_list.getTitle());
        text2.setText(schedule_list.getSDate());
        text3.setText(schedule_list.getEDate());
        text4.setText(schedule_list.getContents());


        return view;
    }

    public void addItem(String title, String sday, String eday, String cont){
        Schedule item = new Schedule();

        item.setTitle(title);
        item.setSDate(sday);
        item.setEDate(eday);
        item.setContents(cont);

        ScheduleAdapter.add(item);
    }
}
