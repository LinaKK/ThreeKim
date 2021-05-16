package com.example.studyforce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassListAdapter extends BaseAdapter implements Filterable {
    private ArrayList<ClassJob> listViewItemList = new ArrayList<ClassJob>();
    private ArrayList<ClassJob> filteredItemList = listViewItemList;

    Filter listFilter ;

    public ClassListAdapter(){

    }

    @Override
    public int getCount() {
        return filteredItemList.size() ;
    }

    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_class_list, parent, false);
        }

        //클래스 이름
        TextView text1 = (TextView) convertView.findViewById(R.id.class_name);
        //클래스 인원 수
        TextView text2 = (TextView) convertView.findViewById(R.id.class_num);
        //클래스(학생전용 or 교수포함)
        TextView text3 = (TextView) convertView.findViewById(R.id.class_job);
        //공개여부
        TextView text4 = (TextView) convertView.findViewById(R.id.class_open);



        ClassJob classJob = filteredItemList.get(position);


        text1.setText(filteredItemList.get(position).getTitle());
        text2.setText(filteredItemList.get(position).getNumber());
        text3.setText(filteredItemList.get(position).getJob());
        text4.setText(filteredItemList.get(position).getOpen());

        return convertView;
    }


     public void addItem( String name, String job, int num, String open) {
        ClassJob item = new ClassJob();

        item.setTitles(name);
        item.setJobs(job);
        item.setNumber(num);
        item.setOpen(open);

        listViewItemList.add(item);
    }


    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter() ;
        }

        return listFilter ;
    }
    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;

            if (constraint == null || constraint.length() == 0) {
                results.values = listViewItemList ;
                results.count = listViewItemList.size() ;
            } else {
                ArrayList<ClassJob> itemList = new ArrayList<ClassJob>() ;

                for (ClassJob item : listViewItemList) {
                    if (item.getTitle().toUpperCase().contains(constraint.toString().toUpperCase()))
                    {
                        itemList.add(item) ;
                    }
                }

                results.values = itemList ;
                results.count = itemList.size() ;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // update listview by filtered data list.
            filteredItemList = (ArrayList<ClassJob>) results.values ;

            // notify
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }
}
