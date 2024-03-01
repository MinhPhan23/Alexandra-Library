package com.alexandria_library.dso;

import java.util.ArrayList;

public class Reader extends User implements IReader{
    private ArrayList<Book> allBooksList;
    private ArrayList<Book> inProgressList;
    private ArrayList<Book> finishedList;

    public Reader(String userName, String password){
        super(userName, password);
        allBooksList = new ArrayList<>();
        inProgressList = new ArrayList<>();
        finishedList = new ArrayList<>();
    }

    /**
     * Getter method for the list of all reader's books
     * @return "AllBooks" book list
     */
    public ArrayList<Book> getAllBooksList(){
        return allBooksList;
    }

    /**
     * Getter method for the list of books that are currently being read
     * @return "In Progress" book list
     */
    public ArrayList<Book> getInProgressList(){
        return inProgressList;
    }

    /**
     * Getter method for the list of books that are finished
     * @return "Finished" book list
     */
    public ArrayList<Book> getFinishedList(){
        return finishedList;
    }


    /**
     * Adding a new book to the "All Books" list
     * @param newBook a book to add to the list
     * @return true if added
     */
    public boolean addBookToAll(Book newBook){
        return allBooksList.add(newBook);
    }

    /**
     * Adding a new book to the "In Progress" list
     * @param newBook a book to add to the list
     * @return true if added
     */
    public boolean addBookToInProgress(Book newBook){
        return inProgressList.add(newBook);
    }

    /**
     * Adding a new book to the "Finished" list
     * @param newBook a book to add to the list
     * @return true if added
     */
    public boolean addBookToFinished(Book newBook){
        return finishedList.add(newBook);
    }

}
