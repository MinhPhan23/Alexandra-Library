package com.alexandria_library.data.DataInterface;

import com.alexandria_library.dso.*;
import java.sql.PreparedStatement;
import java.util.List;

public interface IBookPersistentStub extends IBookPersistent{

    public int checkList(Booklist list);

    public int checkBook(Book book);

    public int upload(Book book, User user);

    public void delete(Book book, User user);

    public void delete(Booklist list, User user);

    public Booklist searchTag(List<String> tags);

    public Booklist searchGenre(List<String> genres);

    public Booklist search(Booklist list);

    public Booklist search(PreparedStatement statement);

    public Book search(Book book);
}
