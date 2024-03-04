package com.alexandria_library.data;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBookPersistenceHSQLDB {

    public int checkCredentials(User user);
    public boolean upload(Book book, User user) throws SQLException;
    public int update(Book book, User user);
    public ArrayList<Book> searchBookByTag(String tagName) throws SQLException;
    public ArrayList<String> searchTagByBook (Book book) throws SQLException;
    public ArrayList<Book> searchGenre (String genreName) throws SQLException;
    public ArrayList<Book> searchAuthor(String author) throws SQLException;
    public ArrayList<Book> searchName(String bookName) throws SQLException;
    public ArrayList<Book> getBookList() throws SQLException;

    public void deleteLibraryBook(ArrayList<Book> list, User user) throws SQLException;

}
