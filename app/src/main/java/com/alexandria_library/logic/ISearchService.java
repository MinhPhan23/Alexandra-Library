package com.alexandria_library.logic;

import  com.alexandria_library.dso.*;

import java.util.ArrayList;

public interface ISearchService {
    public ArrayList<Book> searchInput(String keywords);
    public ArrayList<Book> filterInput(String[] filters);
}
