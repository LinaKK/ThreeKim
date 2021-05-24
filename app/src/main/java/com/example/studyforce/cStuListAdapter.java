package com.example.studyforce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class cStuListAdapter extends BaseAdapter {

    private Context ctx;
    private stulist[] cStu;
    public cStuListAdapter(Context ctx, stulist[] cStu){
        this.ctx = ctx;
        this.cStu =cStu;
    }

    @Override
    public int getCount() {
        return cStu.length;
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
        sn.setText(cStu[position].name);
        d.setText(cStu[position].doneNum);
        tdn.setText(cStu[position].doneNum);

        return convertView;
    }
}
