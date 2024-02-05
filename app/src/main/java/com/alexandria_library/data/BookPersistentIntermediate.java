package com.alexandria_library.data;

import com.alexandria_library.dso.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public interface BookPersistentIntermediate {

    int checkList(ArrayList<Book> list);

    int checkBook(Book book);

    int checkCredentials(User user);

    int upload(Book book);

    int update(Book book);

    boolean isValid(Book book);

    ArrayList<Book> search(String[] tags);

    ArrayList<Book> search(String tag);
    ArrayList<Book> search(ArrayList<Book> list);

    ArrayList<Book> search(PreparedStatement statement);

    Book search(Book book);


}
