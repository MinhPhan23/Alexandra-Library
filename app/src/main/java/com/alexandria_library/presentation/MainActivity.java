package com.alexandria_library.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.alexandria_library.R;

public class MainActivity extends AppCompatActivity implements SearchBarInput.SearchBarListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting Search Bar input immediately
        EditText editText = findViewById(R.id.searchInput);
        SearchBarInput.setupSearchBar(editText, this);
    }

    @Override
    public void onTextChanged(String input){
        Log.e("xiang", "New Input: "+input);
    }
}