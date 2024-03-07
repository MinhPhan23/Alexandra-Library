package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.dsoInterface.IReader;
import com.alexandria_library.logic.Exception.BooklistException;

public interface IDefaultBooklist {
    /**
     * Adding a new book to the "All Books" list
     * @param newBook a book to add to the list
     * @return true if added
     */
    public void addBookToAll(IReader reader, Book newBook) throws BooklistException;
    /**
     * Adding a new book to the "In Progress" list
     * @param newBook a book to add to the list
     * @return true if added
     */
    public void addBookToInProgress(IReader reader, Book newBook) throws BooklistException;
    /**
     * Adding a new book to the "Finished" list
     * @param newBook a book to add to the list
     * @return true if added
     */
    public void addBookToFinished(IReader reader, Book newBook) throws BooklistException;
}
