package com.alexandria_library.dso;

import com.alexandria_library.dso.Booklist;

public class Reader extends User implements IReader{
    private Booklist allBooksList;
    private Booklist inProgressList;
    private Booklist finishedList;

    public Reader(String userName, String password, int id){
        super(userName, password, id);
        allBooksList = new Booklist();
        inProgressList = new Booklist();
        finishedList = new Booklist();
    }

    /**
     * Getter method for the list of all reader's books
     * @return "AllBooks" book list
     */
    public Booklist getAllBooksList(){
        return allBooksList;
    }

    /**
     * Getter method for the list of books that are currently being read
     * @return "In Progress" book list
     */
    public Booklist getInProgressList(){
        return inProgressList;
    }

    /**
     * Getter method for the list of books that are finished
     * @return "Finished" book list
     */
    public Booklist getFinishedList(){
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
