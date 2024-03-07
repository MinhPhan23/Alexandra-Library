package com.alexandria_library.data.DataInterface;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.User;

public interface IBookPersistent {
    public int checkCredentials(User user);
    public int update(Book book, User user);
    public Booklist searchAuthor(String author);
    public Booklist searchName(String bookName);
    public Booklist getBookList();
}
