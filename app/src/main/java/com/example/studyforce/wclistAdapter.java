package com.example.studyforce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class wclistAdapter extends BaseAdapter {
    private Context ctx;
    private clist[] clist;

    public wclistAdapter(Context ctx, clist[] clist){
        this.ctx = ctx;
        this.clist = clist;
    }

    @Override
    public int getCount() {
        return clist.length;
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
            convertView = inflater.inflate(R.layout.wclistlay, parent, false);
        }

        TextView cname = (TextView) convertView.findViewById(R.id.cname);
        TextView sub = (TextView) convertView.findViewById(R.id.sub);
        TextView op = (TextView) convertView.findViewById(R.id.op);

        cname.setText(clist[position].classname);
        sub.setText(clist[position].subject);
        op.setText(clist[position].open);

        return convertView;
    }
}
