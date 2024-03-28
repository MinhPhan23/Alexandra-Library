package com.alexandria_library.logic;

import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.Exception.SearchServiceException;

public interface ISearchService {
    Booklist searchInput(String keywords) throws SearchServiceException;
}
