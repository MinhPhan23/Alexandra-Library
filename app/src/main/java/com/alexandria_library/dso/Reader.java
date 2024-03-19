package com.alexandria_library.dso;

import java.util.ArrayList;

public class Reader extends User implements IReader {
    private Booklist allBooksList;
    private Booklist inProgressList;
    private Booklist finishedList;
    private ArrayList<Booklist> customList;

    public Reader(String userName, String password, int id){
        super(userName, password, id);
        allBooksList = new Booklist();
        inProgressList = new Booklist();
        finishedList = new Booklist();
        customList = new ArrayList<Booklist>();
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

    public ArrayList<Booklist> getAllCustomList() {
        return customList;
    }
}
