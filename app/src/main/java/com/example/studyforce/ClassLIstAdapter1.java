//전체 클래스 리스트뷰에 쓰일 어댑터뷰

package com.example.studyforce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClassLIstAdapter1 extends BaseAdapter implements  Filterable{
   // private Context ctx;
    private ArrayList<ClassJob> classLIstAdapter1 = new ArrayList<ClassJob>();
    private ArrayList<ClassJob> filteredItemList = classLIstAdapter1;
    Filter listFilter;

    public ClassLIstAdapter1() {
    }

    @Override
    public int getCount() {
        return filteredItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return filteredItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_class_list, viewGroup,
                    false);
        }

        //클래스 이름
        TextView text1 = (TextView) view.findViewById(R.id.class_name);
        //클래스 인원 수
        TextView text2 = (TextView) view.findViewById(R.id.class_num);
        //클래스(학생전용 or 교수포함)
        TextView text3 = (TextView) view.findViewById(R.id.class_job);
        //공개여부
        TextView text4 = (TextView) view.findViewById(R.id.class_open);

        ClassJob classJob = filteredItemList.get(i);

        text1.setText(classJob.getTitle());
        text2.setText(classJob.getNumber());
        text3.setText(classJob.getJob());
        text4.setText(classJob.getOpen());

        return view;
    }

    //아이템 추가
    public void addItem(String name, String num, String job, String open) {
        ClassJob item = new ClassJob();

        item.setTitles(name);
        item.setJobs(job);
        item.setNumber(num);
        item.setOpen(open);

        classLIstAdapter1.add(item);
    }

    @Override
    public Filter getFilter() {
        if(listFilter == null){
            listFilter = new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint == null || constraint.length()==0){
                results.values = classLIstAdapter1;
                results.count = classLIstAdapter1.size();
            }else{
                ArrayList<ClassJob> itemList = new ArrayList<ClassJob>();
                for(ClassJob item: classLIstAdapter1){
                    if(item.getTitle().toUpperCase().contains
                            (constraint.toString().toUpperCase())){
                        itemList.add(item);
                    }
                }
                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredItemList = (ArrayList<ClassJob>)results.values;
            if(results.count>0){
                notifyDataSetChanged();
            }else{
                notifyDataSetInvalidated();
            }
        }
    }
}

