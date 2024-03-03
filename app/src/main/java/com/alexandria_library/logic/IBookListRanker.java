package com.alexandria_library.logic;

import com.alexandria_library.dso.Booklist;

import java.util.ArrayList;

public interface IBookListRanker {
    public Booklist rankBooks(Booklist bookList, String[] tags);
}
