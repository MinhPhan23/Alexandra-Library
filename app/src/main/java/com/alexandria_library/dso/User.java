package com.alexandria_library.dso;

public class User implements IUser {
    private String userName;
    private String password;
    private int id;

    public User(String userName, String password, int id){
        this.userName = userName;
        this.password = password;
        this.id = id;
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

    /**
     * Getter method for the user's id
     * @return id
     */
    public int getId(){
        return id;
    }

}
