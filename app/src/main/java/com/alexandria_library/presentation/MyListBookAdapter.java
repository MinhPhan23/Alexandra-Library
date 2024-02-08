package com.alexandria_library.presentation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexandria_library.R;
import com.alexandria_library.presentation.Bean.bookBean;

import java.util.List;

public class MyListBookAdapter extends BaseAdapter {
    private List<bookBean> data;
    private Context context;
    public MyListBookAdapter(List<bookBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_book_list_item, parent, false);
            viewHolder.textView = convertView.findViewById(R.id.book_title);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(data.get(position).getName());

        Log.e("xiang", "getView: "+position);
        return convertView;
    }

    private final class ViewHolder {
        TextView textView;
    }


}
