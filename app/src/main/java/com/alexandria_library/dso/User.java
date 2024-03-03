package com.alexandria_library.dso;

public class User implements IUser{
    private String userName;
    private String password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    /**
     * Getter method for the username
     * @return username
     */
    public String getUserName(){
        return userName;
    }

    /**
     * Getter method for the user's password
     * @return password
     */
    public String getPassword(){
        return password;
    }


}
