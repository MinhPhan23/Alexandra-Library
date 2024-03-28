package com.alexandria_library.presentation.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandria_library.R;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.ISearchService;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.MyViewHolder> {
    private static ISearchService searchService;
    private Booklist BookList;
    private Context context;

    public SearchListAdapter(Booklist list, Context context, ISearchService searchService){
        BookList = list;
        SearchListAdapter.searchService = searchService;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.activity_search_bar_list_item, null);
        return new SearchListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchListAdapter.MyViewHolder holder, int position) {
        //get book name
        holder.title.setText(BookList.get(position).getName());
        //get book ID
        String parsedID = ""+BookList.get(position).getID();
        holder.id.setText(parsedID);
    }

    @Override
    public int getItemCount() {
        if (BookList == null){
            return 0;
        }
        return BookList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView id;

        public MyViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.search_book_title);
            id = itemView.findViewById(R.id.search_book_id);

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

    private SearchListAdapter.OnRecyclerItemClickListener myOnItemClickListener;

    public void setRecyclerItemClickListener(SearchListAdapter.OnRecyclerItemClickListener listener){
        myOnItemClickListener = listener;
    }

    public interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(int position);
    }
}
