package com.alexandria_library.logic;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IUser;
import com.alexandria_library.dso.User;

public class Authentication {

    private final IUser userData;

    public Authentication(){
        userData = Service.getUserPersistence();
    }
    public Authentication(IUser userData){
        this.userData = userData;
    }

    /*****
     * Insert new user to user stub
     * @param userName
     * @param password
     * @return :true for success added, otherwise for false
     */
    public boolean insertNewUser(String userName, String password){
        boolean success = false;
        if(userData.findUser(userName) == null){
            success = userData.addNewUser(userName, password);
        }
        return success;
    }

    /*****
     * find exiting user
     * @param userName
     * @param password
     * @return : specific user, or null
     */
    public User findExist(String userName, String password){
        return userData.findUser(userName, password);
    }
}
