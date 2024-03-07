package com.alexandria_library.dso.dsoInterface;

import com.alexandria_library.dso.Booklist;

import java.util.ArrayList;

public interface IReader extends IUser{

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
     * Getter method for all custom lists
     * @return All "custom" book list
     */
    public ArrayList<Booklist> getAllCustomList();
}
