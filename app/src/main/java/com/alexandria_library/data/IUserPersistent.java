package com.alexandria_library.data;

import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IReader;
import com.alexandria_library.dso.IUser;
import com.alexandria_library.dso.User;

import java.util.List;

public interface IUserPersistent {

    /*****
     * add a new user to user list
     * @param userName new username
     * @param password user's password
     * @return true if added successfully
     */
    public boolean addNewUser(String userName, String password);

    /*****
     * add a new librarian to librarian list
     * @param userName new username
     * @param password user's password
     * @return true if added successfully
     */
    public boolean addNewLibrarian(String userName, String password);

    /*****
     * check if the user exists in the system by username only
     * @param userName a username to check
     * @return An user object if found, null if not
     */
    public User findUser(String userName);

    /***********************************************************************************
     * ADD book to user's list */
    public void addBookToAllList(Booklist list, User user);
    public void addBookToReadingList(Booklist list, User user);
    public void addBookToFinishedList(Booklist list, User user);
    /*************************************************************************************/

    /***********************************************************************************
     * DELETE book from user's list */
    public void deleteUserAllListBook(Booklist list, User user);
    public void deleteReadingListBook(Booklist list, User user);
    public void deleteFinishedListBook(Booklist list, User user);
    /*************************************************************************************/

    /*****
     * check if the librarian exists in the system by username only
     * @param userName a username to check
     * @return An user object if found, null if not
     */
    public User findLibrarian(String userName);
}
