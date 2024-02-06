package com.alexandria_library.data;

import com.alexandria_library.dso.User;

public interface IUser {

    /*****
     * add a new user to user list
     * @param userName
     * @param password
     * @return ture for successful added
     */
    public boolean addNewUser(String userName, String password);

    /*****
     * check the user is existing in system or not
     * @param userName
     * @param password
     * @return true for found, otherwise for false;
     */
    public User findUser(String userName, String password);
}
