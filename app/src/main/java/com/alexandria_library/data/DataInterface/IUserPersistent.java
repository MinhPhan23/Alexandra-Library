package com.alexandria_library.data.DataInterface;

import com.alexandria_library.dso.User;

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

}
