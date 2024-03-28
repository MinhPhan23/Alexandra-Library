package com.alexandria_library.logic;

import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Exception.AuthenticationException;

public interface IAuthentication {
    public User login(String userName, String password, boolean librarianMode) throws AuthenticationException;
    public void register(String userName, String password, String doublePassword, boolean librarianMode) throws AuthenticationException;
}
