package com.alexandria_library.presentation.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandria_library.R;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.Reader;
import com.alexandria_library.logic.SideBarService;
import com.alexandria_library.presentation.Authentication.LoginActivity;

import java.util.ArrayList;

public class InProgressBookAdapter extends RecyclerView.Adapter<InProgressBookAdapter.MyViewHolder> {
    private static SideBarService sideBarService;
    private Booklist inProgressList;
    private Context context;

    public InProgressBookAdapter(Context context){
        sideBarService = LoginActivity.getSideBarService();
        find();
        this.context = context;
    }
    public void find(){
        if(sideBarService != null){
            if (sideBarService.getUser() instanceof Reader) {
                Reader reader = (Reader) sideBarService.getUser();
                inProgressList = reader.getInProgressList();
            }
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.activity_book_list_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InProgressBookAdapter.MyViewHolder holder, int position) {
        //get book name
        holder.title.setText(inProgressList.get(position).getName());
        //get book ID
        String parsedID = ""+inProgressList.get(position).getID();
        holder.id.setText(parsedID);
        //get book author
        holder.author.setText(inProgressList.get(position).getAuthor());
        //get book data
        String data = inProgressList.get(position).getDate()+"";
        holder.date.setText(data);
    }

    @Override
    public int getItemCount() {
        if(inProgressList == null){
            return 0;
        }
        return inProgressList.size();
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
                        myOnItemClickListener.onRecyclerItemClick(getAdapterPosition(), inProgressList.get(getAdapterPosition()));
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
        void onRecyclerItemClick(int position, Book book);
    }
}
