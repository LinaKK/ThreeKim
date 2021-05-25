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

public class wclistAdapter extends BaseAdapter implements Filterable {
    private Context ctx;
    Filter listFilter;
    private ArrayList<clist> cList = new ArrayList<clist>();
    private ArrayList<clist> fcList = cList;

    public wclistAdapter(){
    }

    @Override
    public int getCount() {
       return fcList.size();
    }

    @Override
    public Object getItem(int position) {
        return fcList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ctx = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.wclistlay, parent, false);
        }

        TextView cname = (TextView) convertView.findViewById(R.id.cname);
        TextView sub = (TextView) convertView.findViewById(R.id.sub);
        TextView op = (TextView) convertView.findViewById(R.id.op);

        clist cItem = fcList.get(position);
            cname.setText(cItem.getClassname());
            sub.setText(cItem.getSubject());
            op.setText(cItem.getOpen());
        return convertView;
    }

    public void addItem(String classname, String subject, String open){
        clist item= new clist();
        item.setClassname(classname);
        item.setSubject(subject);
        item.setOpen(open);

        cList.add(item);
    }

    @Override
    public Filter getFilter() {
        if(listFilter == null){
            listFilter = new ListFilter();
        }
        return listFilter;
    }

    public class ListFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint == null || constraint.length() == 0){
                results.values = cList;
                results.count = cList.size();
            }else{
                ArrayList<clist> itemList = new ArrayList<clist>();
                for (clist item: cList){
                    if(item.getClassname().toUpperCase().contains(constraint.toString().toUpperCase())){
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
            fcList = (ArrayList<clist>) results.values;
            if(results.count>0){
                notifyDataSetChanged();
            }else{
                notifyDataSetInvalidated();
            }
        }
    }
}
