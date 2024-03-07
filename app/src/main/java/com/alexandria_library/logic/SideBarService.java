package com.alexandria_library.logic;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.IBookPersistentStub;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.User;

public class SideBarService {
    private final User targetUser;
    private final IBookPersistent libraryBookData;

    /*****
     * SideBarService constructor
     * @param user
     */
    public SideBarService(User user){
        libraryBookData = Service.getBookPersistent();
        targetUser = user;
    }

    public SideBarService(User user, IBookPersistent libraryBookData){
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
    public Booklist getBookList(){
        return libraryBookData.getBookList();
    }
}
 