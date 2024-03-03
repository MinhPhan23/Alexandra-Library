package com.alexandria_library.data;

import com.alexandria_library.dso.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public interface IBookPersistentIntermediate {

    public int checkList(ArrayList<Book> list);

    public int checkBook(Book book);

    public int checkCredentials(User user);

    public int upload(Book book, User user);

    public int update(Book book, User user);

    public void delete(Book book, User user);

    public void delete(ArrayList<Book> list, User user);

    public ArrayList<Book> searchTag(List<String> tags);

    public ArrayList<Book> searchGenre(List<String> genres);

    public ArrayList<Book> searchAuthor(String author);

    public ArrayList<Book> searchName(String bookName);

    public ArrayList<Book> search(ArrayList<Book> list);

    public ArrayList<Book> search(PreparedStatement statement);

    public Book search(Book book);

    public ArrayList<Book> getBookList();

}
