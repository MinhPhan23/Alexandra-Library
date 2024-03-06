package com.alexandria_library.presentation.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandria_library.R;
import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.logic.IBookListFilter;

import java.util.ArrayList;

public class AllGenresListAdapter extends RecyclerView.Adapter<AllGenresListAdapter.MyViewHolder> {
    private static IBookListFilter bookFilter;
    private static IBookPersistent bookPersistent;
    private ArrayList<String> allGenres;
    private AllGenresListAdapter.OnRecyclerItemClickListener myOnItemClickListener;
    private Context context;

    public AllGenresListAdapter(Context context, IBookListFilter bookFilter){
        bookPersistent = Service.getBookPersistent();
        AllGenresListAdapter.bookFilter = bookFilter;
        find();
        this.context = context;
    }
    public void find(){
        if(bookFilter != null){
            allGenres = bookFilter.getAllGenre(bookPersistent);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = View.inflate(context, R.layout.activity_filter_list_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllGenresListAdapter.MyViewHolder holder, int position){
        holder.genreTitles.setText(allGenres.get(position));
    }

    @Override
    public int getItemCount(){
        return allGenres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final CheckBox genreTitles;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            genreTitles = itemView.findViewById(R.id.checkBox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(myOnItemClickListener != null){
                        myOnItemClickListener.onRecyclerItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setRecyclerItemClickListener(AllGenresListAdapter.OnRecyclerItemClickListener listener){
        myOnItemClickListener = listener;
    }
    public interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(int position);
    }
}
