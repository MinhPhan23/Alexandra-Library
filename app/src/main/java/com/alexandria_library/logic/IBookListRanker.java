package com.alexandria_library.logic;

import com.alexandria_library.dso.Booklist;

public interface IBookListRanker {
    public Booklist rankBooks(Booklist bookList, String[] tags);
}
