package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;

import java.util.ArrayList;

public interface IBookListRanker {
    public ArrayList<Book> rankBooks(ArrayList<Book> bookList, String[] tags);
}
