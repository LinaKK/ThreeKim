package com.example.studyforce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.net.ConnectException;

public class answerListAdapter extends BaseAdapter {

    private Context ctx;
    private Alist[] answer;

    public answerListAdapter(Context ctx, Alist[] answer){
        this.ctx = ctx;
        this.answer =answer;
    }

    @Override
    public int getCount() {
        return answer.length;
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

        TextView aw = (TextView) convertView.findViewById(R.id.writer);
        TextView a = (TextView) convertView.findViewById(R.id.answer);
        aw.setText("작성자: "+answer[position].aw);
        a.setText("답변: "+answer[position].a);

        return convertView;
    }
}
