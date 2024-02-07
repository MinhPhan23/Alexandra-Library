package com.alexandria_library.presentation;

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
import com.alexandria_library.presentation.Bean.bookBean;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    private static SideBarService sideBarService;
    private ArrayList<Book> allBookList;
    private ArrayList<Book> inProgressList;
    private ArrayList<Book> finishedList;
    private Context context;

    public BookAdapter(Context context){
        sideBarService = LoginActivity.getSideBarService();
        find();
        this.context = context;
    }
    public void find(){
        if(sideBarService != null){
            allBookList = sideBarService.getUser().getAllBookList();
            inProgressList = sideBarService.getUser().getInProgressList();
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
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder holder, int position) {
        //get book name
        holder.title.setText(allBookList.get(position).getName());
        //get book ID
        holder.id.setText(allBookList.get(position).getID());
        //get book author
        holder.author.setText(allBookList.get(position).getAuthor());
        //get book data
        holder.date.setText(allBookList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        if(allBookList == null){
            return 0;
        }
        else {
            return allBookList.size();
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
