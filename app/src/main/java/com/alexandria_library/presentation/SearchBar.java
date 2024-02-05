package com.alexandria_library.presentation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class SearchBar {
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
                listener.onTextChanged(s.toString());
            }
        });
    }
    public interface SearchBarListener {
        void onTextChanged(String input);
    }
}
