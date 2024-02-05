package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;

public class SearchService implements ISearchService{
    public SearchService() {

    }

    @Override
    public Book[] searchInput(String keywords) {
        Book[] result = null;
        String[] keywordList = keywords.split(" ");
        return result;
    }

    @Override
    public Book[] filterInput(String[] filters) {
        Book[] result = null;
        return result;
    }

}
