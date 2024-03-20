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
     * check if the user exists in the system
     * @param userName a username to check
     * @param password a password to check
     * @return An user object if found, null if not
     */
    public User findUser(String userName, String password);

    /*****
     * check if the user exists in the system by username only
     * @param userName a username to check
     * @return An user object if found, null if not
     */
    public User findUser(String userName);


    /*****
     * those functions we are not using, because we will use on iterations 3
     */

    /*****
     * get all user in database
     * @return : List<User>
     */
    public List<User> getUserSequential();

    /***********************************************************************************
     * ADD book to user's list */
    public void addBookToCustomList(Booklist list, User user);
    public void addBookToReadingList(Booklist list, User user);
    public void addBookToFinishedList(Booklist list, User user);
    /*************************************************************************************/


    /***********************************************************************************
     * DELETE book from user's list */
    public void deleteUserCustomListBook(Booklist list, User user);
    public void deleteReadingListBook(Booklist list, User user);
    public void deleteFinishedListBook(Booklist list, User user);
    /*************************************************************************************/
}
