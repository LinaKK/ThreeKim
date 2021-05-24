package com.example.studyforce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class cStuListAdapter extends BaseAdapter {

    private Context ctx;
    private stulist[] stulist;

    public cStuListAdapter(Context ctx, stulist[] stulist){
        this.ctx = ctx;
        this.stulist =stulist;
    }

    @Override
    public int getCount() {
        return stulist.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.answerlist, parent, false);
        }

        TextView sn = (TextView) convertView.findViewById(R.id.stuname);
        TextView d = (TextView) convertView.findViewById(R.id.done);
        TextView tdn = (TextView) convertView.findViewById(R.id.todon);
        sn.setText(stulist[position].name);
        d.setText(stulist[position].doneNum);
        tdn.setText(stulist[position].doneNum);

        return convertView;
    }
}
