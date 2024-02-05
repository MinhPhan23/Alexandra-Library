package com.alexandria_library.data;

import com.alexandria_library.dso.*;
import java.sql.PreparedStatement;

import java.sql.PreparedStatement;

public interface BookPersistentIntermediate {

    int checkList(BookList list);

    int checkBook(Book book);

    int checkCredentials(User user);

    int upload(Book book);

    int update(Book book);

    boolean isValid(Book book);

    BookList search(String[] tags);

    BookList search(BookList list);

    BookList search(PreparedStatement statement);

    Book search(Book book);
}
