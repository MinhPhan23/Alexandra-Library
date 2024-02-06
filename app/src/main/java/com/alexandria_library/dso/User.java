package com.alexandria_library.dso;

import java.util.ArrayList;

public class User{
    private ArrayList<Book> allBookList;
    private ArrayList<Book> inProgressList;
    private ArrayList<Book> finishedList;
    private String userName;
    private String password;


    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        allBookList = new ArrayList<>();
        inProgressList = new ArrayList<>();
        finishedList = new ArrayList<>();
    }

    public ArrayList<Book> getAllBookList(){
        return allBookList;
    }
    public ArrayList<Book> getInProgressList(){
        return inProgressList;
    }
    public ArrayList<Book> getFinishedList(){
        return finishedList;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }

}
