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

    /******
     * those are all getter functions for User.java's instance variables
     * @return
     */
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

    /*****
     * three functions are adding newbook for each of three lists
     * @param newBook
     * @return
     */
    public boolean addBookToAll(Book newBook){
        return allBookList.add(newBook);
    }
    public boolean addBookToInProgress(Book newBook){
        return inProgressList.add(newBook);
    }
    public boolean addBookToFinished(Book newBook){
        return finishedList.add(newBook);
    }

    /*****
     * three functions are deleting new book for each of three lists
     */
    public boolean deleteBookToAll(Book targetBook){
        return allBookList.remove(targetBook);
    }
    public boolean deleteBookToInProgress(Book targetBook){
        return inProgressList.remove(targetBook);
    }
    public boolean deleteBookToFinished(Book targetBook){
        return finishedList.remove(targetBook);
    }
}
