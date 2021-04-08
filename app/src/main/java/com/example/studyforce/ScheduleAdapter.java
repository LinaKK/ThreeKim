package com.example.studyforce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;

import java.util.ArrayList;

public class ScheduleAdapter extends BaseAdapter {

    private Context ctx;

    private ArrayList<Schedule> ScheduleAdapter = new ArrayList<Schedule>();

    public ScheduleAdapter(Context ctx) {
        this.ctx = ctx;
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

        return null;

    }
}
