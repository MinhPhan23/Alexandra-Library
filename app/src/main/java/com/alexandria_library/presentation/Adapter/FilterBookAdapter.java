package com.alexandria_library.presentation.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.alexandria_library.R;

public class FilterBookAdapter extends RecyclerView.Adapter<FilterBookAdapter.MyViewHolder> {


    private OnRecyclerItemClickListener myOnItemClickListener;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView id;
        private TextView tag;
        private TextView genre;

        public MyViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.book_title);
            id = itemView.findViewById(R.id.book_id);
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

    public void setRecyclerItemClickListener(FilterBookAdapter.OnRecyclerItemClickListener listener){
        myOnItemClickListener = listener;
    }

    public interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(int position);
    }
}
