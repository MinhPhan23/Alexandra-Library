package com.alexandria_library.logic;

import com.alexandria_library.dso.Booklist;


public interface ISearchService {
    Booklist searchInput(String keywords) throws SearchServiceException;
}
