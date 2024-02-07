package com.alexandria_library.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alexandria_library.R;
import com.alexandria_library.dso.Book;
import com.alexandria_library.logic.SideBarService;
import com.alexandria_library.presentation.Adapter.AllBookListAdapter;
import com.alexandria_library.presentation.Authentication.LoginActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchBar.SearchBarListener {

    private ArrayList<Book> allBookList;
    private ArrayList<Book> inProgressList;
    private ArrayList<Book> finishedList;
    private boolean grid = true;
    private AllBookListAdapter bookAdapter;
    private SideBarService sideBarService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookDisplayCategory();

        //Getting Search Bar input immediately
        EditText editText = findViewById(R.id.searchInput);
        SearchBar.setupSearchBar(editText, this);

        //Change Book display Category button
        Button categoryBtn = findViewById(R.id.book_display_category_button);
        sideBarService = LoginActivity.getSideBarService();
        find();

        //go to Authentication page
        Button logOut = findViewById(R.id.account);

        /*****
         * main page change book category's button
         */
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid = !grid;
                bookDisplayCategory();
            }
        });

        /*****
         * main page account button
         */
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void find(){
        if(sideBarService != null){
            allBookList = sideBarService.getUser().getAllBookList();
            inProgressList = sideBarService.getUser().getInProgressList();
            finishedList = sideBarService.getUser().getFinishedList();
        }
    }

    private void bookDisplayCategory(){
        if(grid){
            //Setting Grid of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);

            bookAdapter = new AllBookListAdapter(allBookList,this);
            recyclerView.setAdapter(bookAdapter);
        }
        else{
            //Setting list of book display
            RecyclerView recyclerView = findViewById(R.id.gridView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            bookAdapter = new AllBookListAdapter(allBookList, this);
            recyclerView.setAdapter(bookAdapter);
        }

        bookAdapter.setRecyclerItemClickListener(new AllBookListAdapter.OnRecyclerItemClickListener() {
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

}