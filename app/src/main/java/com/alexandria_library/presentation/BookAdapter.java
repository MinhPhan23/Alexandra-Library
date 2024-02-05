package com.alexandria_library.presentation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandria_library.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    private List<Bean> data;
    private Context context;

    public BookAdapter(List<Bean> data, Context context){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.book_list_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(data == null){
            return 0;
        }
        else {
            return data.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public MyViewHolder(View itemView){
            super(itemView);
            tv = itemView.findViewById(R.id.book_title);

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

    private OnRecyclerItemClickListener myOnItemClickListener;

    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener){
        myOnItemClickListener = listener;
    }

    public interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(int position);
    }
}
