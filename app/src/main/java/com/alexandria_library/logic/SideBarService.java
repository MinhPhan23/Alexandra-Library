package com.alexandria_library.logic;
import android.view.WindowInsets;

import com.alexandria_library.data.IUser;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.User;

public class SideBarService {
    private final User targetUser;

    public SideBarService(User user){
        targetUser = user;
    }

    public User getUser(){
        return targetUser;
    }
    /*****
     * those three functions are by using User's function to add book to each of three lists
     * @param newBook
     * @return : boolean for test
     */
    public boolean addBookToAll (Book newBook){
        return targetUser.addBookToAll(newBook);
    }
    public boolean addBookToInProgress(Book newBook){
        return targetUser.addBookToInProgress(newBook);
    }
    public boolean addBookToFinished(Book newBook){
        return targetUser.addBookToFinished(newBook);
    }

    /*****
     * those three functions are by using User's
     * function to delete target book for each of three lists
     * @param targetBook
     * @return: boolean for test
     */
    public boolean deleteBookToAll (Book targetBook){
        return targetUser.deleteBookToAll(targetBook);
    }
    public boolean deleteBookToInProgress(Book targetBook){
        return targetUser.deleteBookToInProgress(targetBook);
    }
    public boolean deleteBookToFinished(Book targetBook){
        return targetUser.deleteBookToFinished(targetBook);
    }
}
 