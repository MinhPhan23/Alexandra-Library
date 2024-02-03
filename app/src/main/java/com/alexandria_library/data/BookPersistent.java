package com.alexandria_library.data;

import com.alexandria_library.dso.*;
import java.sql.PreparedStatement;

public interface BookPersistent {

    int upload(Book book);

    int upload(BookList list);

    void delete(Book book);

    void delete(BookList list);

    Book update(Book book);

    Book getBook(Book book);

    BookList getBooklist(BookList list);

    BookList getAll();

    BookList getSQL(PreparedStatement statement);
}
