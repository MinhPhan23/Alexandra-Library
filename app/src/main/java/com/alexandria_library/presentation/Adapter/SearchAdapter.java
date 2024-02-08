package com.alexandria_library.presentation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexandria_library.R;
import com.alexandria_library.dso.Book;

import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter {

    private ArrayList<Book> bookList;
    private Context context;

    public SearchAdapter(ArrayList<Book> bookList, Context context){
        this.bookList = bookList;
        this.context = context;
    }

    @Override
    public int getCount(){
        if(bookList == null){
            return 0;
        }
        return bookList.size();
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
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_search_bar_list_item, parent, false);
        }
        TextView bookName = convertView.findViewById(R.id.book_title);
        bookName.setText(bookList.get(position).getName());

        TextView bookId = convertView.findViewById(R.id.book_id);
        bookId.setText(bookList.get(position).getID());

        return convertView;
    }
}
