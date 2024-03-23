package com.alexandria_library.logic;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Exception.AuthenticationException;

public class Authentication implements IAuthentication{

    private final IUserPersistent userData;

    public Authentication(){
        userData = Service.getUserPersistent();
    }
    public Authentication(IUserPersistent userData){
        this.userData = userData;
    }

    private boolean insertNewUser(String userName, String password, boolean librarianMode){
        boolean success;
        if(librarianMode){
            success = userData.addNewLibrarian(userName, password);
        }
        else{
            success = userData.addNewUser(userName, password);
        }
        return success;
    }

    private User checkExistingUser(String userName, boolean librarianMode){
        User user;
        if(librarianMode){
            user = userData.findLibrarian(userName);
        }
        else{
            user = userData.findUser(userName);
        }
        return user;
    }

    @Override
    public User login(String userName, String password, boolean librarianMode) throws AuthenticationException{
        if (userName==null || userName.equals("") || password==null || password.equals("")) {
            throw new AuthenticationException("Username and Password cannot be empty");
        }
        User user = checkExistingUser(userName, librarianMode);
        if (user == null) {
            throw new AuthenticationException("Username does not exist");
        }
        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("Password is not correct");
        }
        return user;
    }

    @Override
    public void register(String userName, String password, String doublePassword, boolean librarianMode) throws AuthenticationException {
        if(userName == null || password == null || doublePassword == null || userName.equals("") || password.equals("") || doublePassword.equals("")){
            throw new AuthenticationException("Username and Password cannot be empty");
        }
        if (!password.equals(doublePassword)) {
            throw new AuthenticationException("Double password does not match");
        }

        User user = checkExistingUser(userName, librarianMode);

        if (user != null) {
            throw new AuthenticationException("Username already exist");
        }

        boolean success = insertNewUser(userName, password, librarianMode);

        if (!success) {
            throw new AuthenticationException("Cannot add reason due to some failures, please try again");
        }
    }
}
