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
    private clist[] clist;
    Filter listFilter;
    private clist[] fclist;//<- 무슨역할이야?? 여기에 아무것도 안들어가서 오류나길래 일단 clist로 바꿔놨는데 => 이게 걸러낸거를 리스트로 만든거야.


    public wclistAdapter(Context ctx, clist[] clist){
        this.ctx = ctx;
        this.clist = clist;
    }

    @Override
    public int getCount() {
        return fclist.length;
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

        cname.setText(fclist[position].classname);
        sub.setText(fclist[position].subject);
        op.setText(fclist[position].open);

        return convertView;
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
                results.values = clist;
                results.count = clist.length;
            }else{
                final clist[] cclist = new clist[clist.length];
                for (clist item: clist){
                    if(item.classname.toUpperCase().contains(constraint.toString().toUpperCase())){
                        for(int i=0; i<clist.length; i++){
                            cclist[i]=item;
                        }
                    }
                }
                results.values = cclist;
                results.count = cclist.length;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            fclist = (clist[])results.values;
            if(results.count>0){
                notifyDataSetChanged();
            }else{
                notifyDataSetInvalidated();
            }
        }
    }
}
