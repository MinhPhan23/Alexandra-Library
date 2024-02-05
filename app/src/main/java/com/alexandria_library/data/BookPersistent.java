package com.alexandria_library.data;

import com.alexandria_library.dso.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public interface BookPersistent {

    int upload(Book book);

    int upload(ArrayList<Book> list);

    int update(Book book);

    void delete(Book book);

    void delete(ArrayList<Book> list);

    Book getBook(Book book);

    ArrayList<Book> getBookList(ArrayList<Book> list);

    ArrayList<Book> getAll();

    ArrayList<Book> getSQL(PreparedStatement statement);
}
