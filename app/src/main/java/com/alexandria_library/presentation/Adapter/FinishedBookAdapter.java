package com.alexandria_library.presentation.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandria_library.R;
import com.alexandria_library.dso.Book;
import com.alexandria_library.logic.SideBarService;
import com.alexandria_library.presentation.Authentication.LoginActivity;

import java.util.ArrayList;

public class FinishedBookAdapter extends RecyclerView.Adapter<FinishedBookAdapter.MyViewHolder> {
    private static SideBarService sideBarService;
    private ArrayList<Book> finishedList;
    private Context context;

    public FinishedBookAdapter(Context context){
        sideBarService = LoginActivity.getSideBarService();
        find();
        this.context = context;
    }
    public void find(){
        if(sideBarService != null){
            finishedList = sideBarService.getUser().getFinishedList();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.activity_book_list_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinishedBookAdapter.MyViewHolder holder, int position) {
        //get book name
        holder.title.setText(finishedList.get(position).getName());
        //get book ID
        String parsedID = ""+finishedList.get(position).getID();
        holder.id.setText(parsedID);
        //get book author
        holder.author.setText(finishedList.get(position).getAuthor());
        //get data
        String data = finishedList.get(position).getDate()+"";
        holder.date.setText(data);
    }

    @Override
    public int getItemCount() {
        if(finishedList == null){
            return 0;
        }
        else {
            return finishedList.size();
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView id;
        private TextView author;
        private TextView date;

        public MyViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.book_title);
            id = itemView.findViewById(R.id.book_id);
            author = itemView.findViewById(R.id.book_author);
            date = itemView.findViewById(R.id.book_date);

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
