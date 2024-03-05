package com.alexandria_library.data;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBookPersistenceHSQLDB {

    public int checkCredentials(User user);
    public boolean upload(Book book, User user) throws SQLException;
    public int update(Book book, User user);
    public void deleteLibraryBook(Booklist list, User user) throws SQLException;
    public ArrayList<String> searchTagByBook (Book book) throws SQLException;
    public Booklist getBookList() throws SQLException;

    //START searching book
    public Booklist searchTag(String tagName) throws SQLException;
    public Booklist searchGenre (String genreName) throws SQLException;
    public Booklist searchAuthor(String author) throws SQLException;
    public Booklist searchName(String bookName) throws SQLException;

    //START finding book
    public Booklist getUserCustomList(User user) throws SQLException;
    public Booklist getUserInProgressList(User user) throws SQLException;
    public Booklist getUserFinishedList(User user) throws SQLException;
}
