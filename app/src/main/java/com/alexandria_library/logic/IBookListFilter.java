package com.alexandria_library.logic;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.dso.Booklist;
import java.util.ArrayList;

public interface IBookListFilter {
    public Booklist sortByTitle(Booklist bookList);

    public Booklist sortByDate(Booklist bookList);

    public Booklist sortByAuthor(Booklist bookList);

    public Booklist filterByTag(Booklist bookList, String[] tags);

    public Booklist filterByGenre(Booklist bookList, String[] genres);

    public Booklist filterByAuthor(Booklist bookList, String[] authors);
    public Booklist getFilteredList(Booklist books, String[] tags, String[] genres);
    public ArrayList<String> getAllTags(IBookPersistent bookPersistent);
    public ArrayList<String > getAllGenre(IBookPersistent bookPersistent);
}
