package com.alexandria_library.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
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

        //Setting Horizontal List of book display
        getBookData(data);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter(data, this));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("xiang", "onClicked: "+position);
            }
        });

    }

    @Override
    public void onTextChanged(String input){
        Log.e("xiang", "New Input: "+input);
    }

    public void getBookData (List<Bean> data){
        for(int i = 0; i<100; i++){
            Bean bean = new Bean();
            bean.setName("Book"+i);
            data.add(bean);
        }
    }
}