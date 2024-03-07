package com.alexandria_library.data;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBookPersistentHSQLDB extends IBookPersistent{

    public boolean upload(Book book, User user);
    public void deleteLibraryBook(Booklist list, User user);
    public ArrayList<String> searchTagByBook (Book book);
    public ArrayList<String> getAllTags ();
    public ArrayList<String> getAllGenres();

    //START searching book
    public Booklist searchTag(String tagName);
    public Booklist searchGenre (String genreName);

    //START finding book
    public Booklist getUserCustomList(User user);
    public Booklist getUserInProgressList(User user);
    public Booklist getUserFinishedList(User user);
}
