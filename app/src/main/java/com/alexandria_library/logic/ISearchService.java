package com.alexandria_library.logic;

import  com.alexandria_library.dso.*;
public interface ISearchService {
    public Book[] searchInput(String keywords);
    public Book[] filterInput(String[] filters);
}
