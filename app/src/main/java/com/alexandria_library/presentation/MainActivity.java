package com.alexandria_library.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alexandria_library.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchBar.SearchBarListener {

    private List<Bean> data = new ArrayList<>();
    private boolean grid = true;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookDisplayCategory();

        //Getting Search Bar input immediately
        EditText editText = findViewById(R.id.searchInput);
        SearchBar.setupSearchBar(editText, this);

        //Change Book display Category button
        Button button = findViewById(R.id.book_display_category_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid = !grid;
                bookDisplayCategory();
            }
        });
    }


    private void bookDisplayCategory(){
        if(grid){
            //Setting Grid of book display
            getBookData(data);
            RecyclerView recyclerView = findViewById(R.id.gridView);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);

            bookAdapter = new BookAdapter(data, this);
            recyclerView.setAdapter(bookAdapter);
        }
        else{
            //Setting list of book display
            getBookData(data);
            RecyclerView recyclerView = findViewById(R.id.gridView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            bookAdapter = new BookAdapter(data, this);
            recyclerView.setAdapter(bookAdapter);
        }

        bookAdapter.setRecyclerItemClickListener(new BookAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                Log.e("xiang", "onRecyclerItemClick:" +position);
            }
        });
    }

    @Override
    public void onTextChanged(String input){
        Log.e("xiang", "New Input: "+input);
    }

    public void getBookData (List<Bean> data){
        for(int i = 0; i<1000; i++){
            Bean bean = new Bean();
            bean.setName("Book"+i);
            data.add(bean);
        }
    }
}