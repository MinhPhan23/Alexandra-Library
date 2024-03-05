package com.alexandria_library.data;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IUserPersistenceHSQLDB {

    /*****
     * get all user in database
     * @return : List<User>
     * @throws SQLException
     */
    public List<User> getUserSequential() throws SQLException;

    /*****
     * add a new user to user list
     * @param userName new username
     * @param password user's password
     * @return true if added successfully
     */
    public boolean addNewUser(String userName, String password) throws SQLException;

    /*****
     * check if the user exists in the system by username and password
     * @param userName a username to check
     * @param password a password to check
     * @return true for found, false for not found;
     */
    public User findUser(String userName, String password) throws SQLException;

    /*****
     * check if the user exists in the system by username only
     * @param userName a username to check
     * @return true for found, false for not found;
     */
    public User findUser(String userName)throws SQLException;

    /***********************************************************************************
     * ADD book to user's list */
    public void addBookToCustomList(Booklist list, User user) throws SQLException;
    public void addBookToReadingList(Booklist list, User user) throws SQLException;
    public void addBookToFinishedList(Booklist list, User user) throws SQLException;
    /*************************************************************************************/


    /***********************************************************************************
     * DELETE book from user's list */
    public void deleteUserCustomListBook(Booklist list, User user) throws SQLException;
    public void deleteReadingListBook(Booklist list, User user) throws SQLException;
    public void deleteFinishedListBook(Booklist list, User user) throws SQLException;
    /*************************************************************************************/
}
