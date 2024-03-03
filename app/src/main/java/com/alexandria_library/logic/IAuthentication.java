package com.alexandria_library.logic;

import com.alexandria_library.dso.User;

public interface IAuthentication {
    public User findExist(String userName, String password);
    public boolean insertNewUser(String userName, String password);
}
