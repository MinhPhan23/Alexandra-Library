package com.alexandria_library.presentation.Adapter;

import android.content.Context;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandria_library.R;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.BookListFilter;
import com.alexandria_library.logic.IBookListFilter;

import java.util.ArrayList;
import java.util.Arrays;

public class FilterBookAdapter extends RecyclerView.Adapter<FilterBookAdapter.MyViewHolder> {
    private static IBookListFilter filter;
    private Booklist filteredBooks;
    private Booklist allLibraryBooks;
    private String [] tagArray;
    private String [] genreArray;
    private Context context;

    public FilterBookAdapter(Context context, IBookListFilter filter, Booklist allLibraryBooks, ArrayList<String>tagsList,
                             ArrayList<String> genresList){

        tagArray = transferListToArray(tagsList);
        genreArray = transferListToArray(genresList);
        FilterBookAdapter.filter = filter;
        this.context = context;
        this.allLibraryBooks = allLibraryBooks;
        findBooks();
    }

    public void findBooks(){
        if(filter != null){
            filteredBooks = filter.getFilteredList(allLibraryBooks, tagArray, genreArray);
        }
        else{
            filteredBooks = null;
        }
    }

    public String[] transferListToArray(ArrayList<String> list){
        String[] result = new String[list.size()];
        if(result.length > 0){
            result = list.toArray(result);
        }
        return result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = View.inflate(context, R.layout.activity_filter_book_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterBookAdapter.MyViewHolder holder, int position){
        //get book name
        holder.title.setText(filteredBooks.get(position).getName());

        //get book id
        String parsedID = ""+filteredBooks.get(position).getID();
        holder.id.setText(parsedID);

        //get tags
        String tags = filteredBooks.get(position).getTags().toString();
        holder.tag.setText(tags);

        //get genres
        String genres = filteredBooks.get(position).getGenres().toString();
        holder.genre.setText(genres);
    }

    @Override
    public int getItemCount(){
        if(filteredBooks == null)
            return 0;
        else
            return filteredBooks.size();
    }

    private OnRecyclerItemClickListener myOnItemClickListener;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView id;
        private TextView tag;
        private TextView genre;

        public MyViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.filter_book_title);
            id = itemView.findViewById(R.id.filter_book_id);
            tag = itemView.findViewById(R.id.filter_book_tags);
            genre = itemView.findViewById(R.id.filter_book_genres);
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
