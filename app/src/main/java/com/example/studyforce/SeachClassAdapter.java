//클래스 검색과 관련된 리스트 어댑터뷰

package com.example.studyforce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SeachClassAdapter<ViewHolder> extends BaseAdapter {

    private Context context;
    private List<String> list;
    private LayoutInflater inflate;
    private ViewHolder viewHolder = new ViewHolder();

    public SeachClassAdapter(List<String> list,Context context) {
        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder{
        public TextView label;
    }

    //왜 오류가 날까??

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.search_class_list,null);

            viewHolder = new ViewHolder();
            viewHolder.label = (TextView)convertView.findViewById(R.id.search_class);

            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.label.setText(list.get(position));
        return convertView;
    }

}

//참고사이트 - https://sharp57dev.tistory.com/11