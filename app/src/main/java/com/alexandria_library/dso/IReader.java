package com.alexandria_library.dso;

import com.alexandria_library.dso.Booklist;

public interface IReader {

    /**
     * Getter method for the list of all reader's books
     * @return "AllBooks" book list
     */
    public Booklist getAllBooksList();
    /**
     * Getter method for the list of books that are currently being read
     * @return "In Progress" book list
     */
    public Booklist getInProgressList();
    /**
     * Getter method for the list of books that are finished
     * @return "Finished" book list
     */
    public Booklist getFinishedList();


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
