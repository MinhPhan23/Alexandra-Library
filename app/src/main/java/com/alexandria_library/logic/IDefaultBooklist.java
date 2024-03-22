package com.alexandria_library.logic;

import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IReader;
import com.alexandria_library.logic.Exception.BooklistException;

public interface IDefaultBooklist {
    /**
     * Adding a new book to the "All Books" list
     * @param newBook a book to add to the list
     */
    public void addBookToAll(IReader reader, Booklist newBook) throws BooklistException;
    /**
     * Adding a new book to the "In Progress" list
     * @param newBook a book to add to the list
     */
    public void addBookToInProgress(IReader reader, Booklist newBook) throws BooklistException;
    /**
     * Adding a new book to the "Finished" list
     * @param newBook a book to add to the list
     */
    public void addBookToFinished(IReader reader, Booklist newBook) throws BooklistException;
}
