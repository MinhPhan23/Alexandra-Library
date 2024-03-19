package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IReader;
import com.alexandria_library.logic.Exception.BooklistException;

public interface ICustomBooklist {
    /**
     * Adding a new book to a custom list
     *
     * @param book     the new book
     * @param booklist the custom list
     * @return
     */
    public void addBookToCustom(IReader reader, Book book, Booklist booklist) throws BooklistException;

    public void addBooklist(IReader reader, String name) throws BooklistException;

    public void addBooklist(IReader reader, Booklist booklist) throws BooklistException;
}