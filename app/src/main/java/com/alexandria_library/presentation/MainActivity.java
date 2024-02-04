package com.alexandria_library.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alexandria_library.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchBarInput.SearchBarListener {

    private List<Bean> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting Search Bar input immediately
        EditText editText = findViewById(R.id.searchInput);
        SearchBarInput.setupSearchBar(editText, this);

//        //Setting Horizontal List of book display
//        getBookData(data);
//        ListView listView = findViewById(R.id.listView);
//        listView.setAdapter(new MyListBookAdapter(data, this));
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("xiang", "onClicked: "+position);
//            }
//        });

        //Setting Grid of book display
        getBookData(data);
        RecyclerView recyclerView = findViewById(R.id.gridView);

//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        MyGridBookAdapter myGridBookAdapter = new MyGridBookAdapter(data, this);
        recyclerView.setAdapter(myGridBookAdapter);

        myGridBookAdapter.setRecyclerItemClickListener(new MyGridBookAdapter.OnRecyclerItemClickListener() {
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