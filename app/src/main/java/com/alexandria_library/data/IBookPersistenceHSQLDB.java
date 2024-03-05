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
    public Booklist searchBookByTag(String tagName) throws SQLException;
    public ArrayList<String> searchTagByBook (Book book) throws SQLException;
    public Booklist searchGenre (String genreName) throws SQLException;
    public Booklist searchAuthor(String author) throws SQLException;
    public Booklist searchName(String bookName) throws SQLException;
    public Booklist getLibraryBookList() throws SQLException;
    public Booklist findUserCustomList(User user) throws SQLException;
    public Booklist findUserInProgressList(User user) throws SQLException;
    public Booklist findUserFinishedList(User user) throws SQLException;
    public void deleteLibraryBook(Booklist list, User user) throws SQLException;

}
