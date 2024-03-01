package com.alexandria_library.dso;

import java.util.ArrayList;

public interface IReader {

    /**
     * Getter method for the list of all reader's books
     * @return "AllBooks" book list
     */
    public ArrayList<Book> getAllBooksList();
    /**
     * Getter method for the list of books that are currently being read
     * @return "In Progress" book list
     */
    public ArrayList<Book> getInProgressList();
    /**
     * Getter method for the list of books that are finished
     * @return "Finished" book list
     */
    public ArrayList<Book> getFinishedList();


    /**
     * Adding a new book to the "All Books" list
     * @param newBook a book to add to the list
     * @return true if added
     */
    public boolean addBookToAll(Book newBook);
    /**
     * Adding a new book to the "In Progress" list
     * @param newBook a book to add to the list
     * @return true if added
     */
    public boolean addBookToInProgress(Book newBook);
    /**
     * Adding a new book to the "Finished" list
     * @param newBook a book to add to the list
     * @return true if added
     */
    public boolean addBookToFinished(Book newBook);

}
