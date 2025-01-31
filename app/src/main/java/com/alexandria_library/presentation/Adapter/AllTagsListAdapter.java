package com.alexandria_library.presentation.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.alexandria_library.R;
import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.logic.IBookListFilter;

import java.util.ArrayList;

public class AllTagsListAdapter extends RecyclerView.Adapter<AllTagsListAdapter.MyViewHolder> {
    private static IBookListFilter bookFilter;
    private static IBookPersistent bookPersistent;
    private ArrayList<String> allTags;
    private OnRecyclerItemClickListener myOnItemClickListener;

    private Context context;

    public AllTagsListAdapter(Context context, IBookListFilter bookFilter){
        bookPersistent = Service.getBookPersistent();
        AllTagsListAdapter.bookFilter = bookFilter;
        find();
        this.context = context;
    }
    public void find(){
        if(bookFilter != null){
            allTags = bookFilter.getAllTags(bookPersistent);
        }
    }
    public String getTagsName(int position){
        if(allTags.size() != 0){
            return allTags.get(position);
        }
        return null;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = View.inflate(context, R.layout.activity_filter_list_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllTagsListAdapter.MyViewHolder holder, int position){
        holder.tagsTitle.setText(allTags.get(position));
    }

    @Override
    public int getItemCount(){
        if(allTags == null)
            return 0;
        else
            return allTags.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox tagsTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tagsTitle = itemView.findViewById(R.id.checkBox);

            tagsTitle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(myOnItemClickListener != null){
                        myOnItemClickListener.onRecyclerItemClick(tagsTitle, getAdapterPosition());
                    }
                }
            });
        }
    }


    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener){
        myOnItemClickListener = listener;
    }
    public interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(CheckBox currentBox, int position);
    }
}
