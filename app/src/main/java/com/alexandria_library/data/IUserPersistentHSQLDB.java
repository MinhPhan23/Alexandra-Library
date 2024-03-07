package com.alexandria_library.data;

import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserPersistentHSQLDB extends IUserPersistent{

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
