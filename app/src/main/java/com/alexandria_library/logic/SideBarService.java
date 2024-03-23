package com.alexandria_library.logic;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IUser;

public class SideBarService {
    private final IUser targetUser;
    private final IBookPersistent libraryBookData;

    /*****
     * SideBarService constructor
     * @param user
     */
    public SideBarService(IUser user){
        libraryBookData = Service.getBookPersistent();
        targetUser = user;
    }

    public SideBarService(IUser user, IBookPersistent libraryBookData){
        this.libraryBookData = libraryBookData;
        targetUser = user;
    }

    /****
     * Getter function
     * @return
     */
    public IUser getUser(){
        return targetUser;
    }
    public Booklist getBookList(){
        return libraryBookData.getBookList();
    }
}
 