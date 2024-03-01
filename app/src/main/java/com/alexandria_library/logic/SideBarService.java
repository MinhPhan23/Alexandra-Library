package com.alexandria_library.logic;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistentIntermediate;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.User;

import java.util.ArrayList;

public class SideBarService {
    private final User targetUser;
    private final IBookPersistentIntermediate libraryBookData;

    /*****
     * SideBarService constructor
     * @param user
     */
    public SideBarService(User user){
        libraryBookData = Service.getBookPersistenceIntermediate();
        targetUser = user;
    }

    public SideBarService(User user, IBookPersistentIntermediate libraryBookData){
        this.libraryBookData = libraryBookData;
        targetUser = user;
    }

    /****
     * Getter function
     * @return
     */
    public User getUser(){
        return targetUser;
    }
    public ArrayList<Book> getBookList(){
        return libraryBookData.getBookList();
    }
}
 