package com.alexandria_library.logic;

import  com.alexandria_library.dso.*;

import java.util.ArrayList;

public interface ISearchService {
    ArrayList<Book> searchInput(String keywords) throws SearchServiceException;
}
