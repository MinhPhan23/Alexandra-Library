package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;

import java.util.ArrayList;

public class SearchService implements ISearchService{
    public SearchService() {

    }

    @Override
    public ArrayList<Book> searchInput(String keywords) {
        ArrayList<Book> result = null;
        String[] keywordList = keywords.split(" ");
        return result;
    }

    @Override
    public ArrayList<Book> filterInput(String[] filters) {
        ArrayList<Book> result = null;
        return result;
    }

}
