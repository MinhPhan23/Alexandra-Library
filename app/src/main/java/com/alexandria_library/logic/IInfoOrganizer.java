package com.alexandria_library.logic;

import com.alexandria_library.dso.BookList;

public interface IInfoOrganizer {
    //sort books alphabetically
    public BookList sortByTitle(BookList bookList);

    //sort books by date published
    public BookList sortByDate(BookList bookList);

    //sort books by author alphabetically
    public BookList sortByAuthor(BookList bookList);

    //filter books by tag
    public BookList filterByTag(BookList bookList, String[] tags);

    //filter books by genre
    public BookList filterByGenre(BookList bookList, String[] genres);

    //filter books by author
    public BookList filterByAuthor(BookList bookList, String[] authors);
}
