//클래스 리스트뷰에 쓰일 어댑터뷰

package com.example.studyforce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassLIstAdapter1 extends BaseAdapter {
    private Context ctx;

    private ArrayList<ClassJob> classLIstAdapter1 = new ArrayList<ClassJob>();

    public ClassLIstAdapter1(){

    }

    @Override
    public int getCount() {
        return classLIstAdapter1.size();
    }

    @Override
    public Object getItem(int i) {
        return classLIstAdapter1.get(i);
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
           view = inflater.inflate(R.layout.activity_class_list, viewGroup, false);
       }

        //클래스 이름
        TextView text1 = (TextView)view.findViewById(R.id.class_name);
        //클래스 인원 수
        TextView text2 = (TextView)view.findViewById(R.id.class_num);
        //클래스(학생전용 or 교수포함)
        TextView text3 = (TextView)view.findViewById(R.id.class_job);

        ClassJob class_list = classLIstAdapter1.get(i);
        text1.setText(class_list.getTitle());
        text2.setText(class_list.getNumber());
        text3.setText(class_list.getJob());

        return view;
    }

    //아이템 추가
    public void addItem(String name, String num, String job){
        ClassJob item = new ClassJob();

        item.setTitles(name);
        item.setJobs(job);
        item.setNumber(num);

        classLIstAdapter1.add(item);

    }


}

//참조페이지 - https://baessi.tistory.com/52 or 책
