package com.alexandria_library.presentation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.alexandria_library.logic.ISearchService;
import com.alexandria_library.logic.SearchService;

public class SearchBar {
    private static ISearchService searchService;

    public static void setupSearchBar(EditText searchBarInput, final SearchBarListener listener){
        searchBarInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("xiang", s.toString());
                listener.onTextChanged(s.toString());
            }
        });
    }
    public interface SearchBarListener {
        void onTextChanged(String input);
    }
}
