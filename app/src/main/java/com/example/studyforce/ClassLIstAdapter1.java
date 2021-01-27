package com.example.studyforce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClassLIstAdapter1 extends BaseAdapter {
    private Context ctx;
    private ClassJob[] data;

    public ClassLIstAdapter1(Context ctx, ClassJob[] data){
        this.ctx =ctx;
        this.data=data;
    }


    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            view = inflater.inflate(R.layout.class_list, viewGroup, false);
        }
        TextView text1 = (TextView)view.findViewById(R.id.class_name);
        text1.setText(data[i].name);
        TextView text2 = (TextView)view.findViewById(R.id.class_num);
        text2.setText(data[i].num);
        TextView text3 = (TextView)view.findViewById(R.id.class_job);
        text3.setText(data[i].job);

        return view;
    }
}
