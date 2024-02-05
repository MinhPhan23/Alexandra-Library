package com.alexandria_library.data;

import com.alexandria_library.dso.*;
import java.sql.PreparedStatement;

public interface BookPersistent {

    int upload(Book book);

    int upload(BookList list);

    int update(Book book);

    void delete(Book book);

    void delete(BookList list);

    Book getBook(Book book);

    BookList getBooklist(BookList list);

    BookList getAll();

    BookList getSQL(PreparedStatement statement);
}
