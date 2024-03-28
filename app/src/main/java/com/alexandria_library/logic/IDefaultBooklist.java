package com.alexandria_library.logic;

import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IReader;
import com.alexandria_library.logic.Exception.BooklistException;

public interface IDefaultBooklist {
    /**
     * Add books to the "All" list
     * @param reader the current Reader DSO
     * @param newBook the list of books to remove
     * @throws BooklistException if one of the book is not in the list
     */
    public void addBookToAll(IReader reader, Booklist newBook) throws BooklistException;
    /**
     * Add books to the "In Progress" list
     * @param reader the current Reader DSO
     * @param newBook the list of books to remove
     * @throws BooklistException if one of the book is not in the list
     */
    public void addBookToInProgress(IReader reader, Booklist newBook) throws BooklistException;
    /**
     * Add books to the "Finished" list
     * @param reader the current Reader DSO
     * @param newBook the list of books to remove
     * @throws BooklistException if one of the book is not in the list
     */
    public void addBookToFinished(IReader reader, Booklist newBook) throws BooklistException;

    /**
     * Remove books to the "All" list
     * @param reader the current Reader DSO
     * @param books the list of books to remove
     * @throws BooklistException if one of the book is not in the list
     */
    public void removeBookFromAll(IReader reader, Booklist books) throws BooklistException;
    /**
     * Remove books to the "In Progress" list
     * @param reader the current Reader DSO
     * @param books the list of books to remove
     * @throws BooklistException if one of the book is not in the list
     */
    public void removeBookFromInProgress(IReader reader, Booklist books) throws BooklistException;
    /**
     * Remove books to the "Finished" list
     * @param reader the current Reader DSO
     * @param books the list of books to remove
     * @throws BooklistException if one of the book is not in the list
     */
    public void removeBookFromFinished(IReader reader, Booklist books) throws BooklistException;
}
