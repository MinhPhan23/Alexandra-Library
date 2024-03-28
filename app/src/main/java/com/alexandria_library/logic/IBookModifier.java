package com.alexandria_library.logic;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.IUser;
import com.alexandria_library.dso.Librarian;

import java.util.ArrayList;

public interface IBookModifier {
    public boolean uploadBook(IUser user, int id, String bookName, String author, String date,
                              ArrayList<String> tags, ArrayList<String> genres);
    public boolean deleteLibraryBook(Book book, IUser librarian);
}
