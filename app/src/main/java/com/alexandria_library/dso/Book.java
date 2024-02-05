package com.alexandria_library.dso;

public class Book {

    // instance variables
    private int id;
    private String name;
    private String author;
    private String date;
    private String[] tags;
    private String[] genres;

    public Book (int bookID, String bookName, String bookAuthor, String bookDate,
                 String[] bookTags, String[] bookGenres) {
        id = bookID;
        name = bookName;
        author = bookAuthor;
        date = bookDate;
        tags = bookTags;
        genres = bookGenres;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String[] getTags() {
        return tags;
    }

    public String[] getGenres() {
        return genres;
    }


    public void setID(int bookID) {
        id = bookID;
    }

    public void setName(String bookName) {
        name = bookName;
    }

    public void setAuthor(String bookAuthor) {
        author = bookAuthor;
    }

    public void setDate(String bookDate) {
        date = bookDate;
    }

    public void setTags(String[] bookTags) {
        tags = bookTags;
    }

    public void setGenres(String[] bookGenres) {
        genres = bookGenres;
    }

}
