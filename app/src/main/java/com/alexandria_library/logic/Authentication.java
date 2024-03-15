package com.alexandria_library.logic;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.DataInterface.IUserPersistent;
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

    private boolean insertNewUser(String userName, String password){
        return userData.addNewUser(userName, password);
    }

    private User checkExistingUser(String userName){
        return userData.findUser(userName);
    }

    @Override
    public User login(String userName, String password) throws AuthenticationException {
        if (userName==null || userName.equals("") || password==null || password.equals("")) {
            throw new AuthenticationException("Username and Password cannot be empty");
        }
        User user = checkExistingUser(userName);
        if (user == null) {
            throw new AuthenticationException("Username does not exist");
        }
        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("Password is not correct");
        }
        return user;
    }

    @Override
    public void register(String userName, String password, String doublePassword) throws AuthenticationException {
        if(userName == null || password == null || doublePassword == null || userName.equals("") || password.equals("") || doublePassword.equals("")){
            throw new AuthenticationException("Username and Password cannot be empty");
        }
        if (!password.equals(doublePassword)) {
            throw new AuthenticationException("Double password does not match");
        }
        User user = checkExistingUser(userName);
        if (user != null) {
            throw new AuthenticationException("Username already exist");
        }
        boolean success = insertNewUser(userName, password);
        if (!success) {
            throw new AuthenticationException("Cannot add reason due to some failures, please try again");
        }
    }
}
