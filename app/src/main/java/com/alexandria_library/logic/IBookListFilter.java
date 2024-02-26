package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;

import java.util.ArrayList;

public interface IBookListFilter {
    public ArrayList<Book> sortByTitle(ArrayList<Book> bookList);

    public ArrayList<Book> sortByDate(ArrayList<Book> bookList);

    public ArrayList<Book> sortByAuthor(ArrayList<Book> bookList);

    public ArrayList<Book> filterByTag(ArrayList<Book> bookList, String[] tags);

    public ArrayList<Book> filterByGenre(ArrayList<Book> bookList, String[] genres);

    public ArrayList<Book> filterByAuthor(ArrayList<Book> bookList, String[] authors);

    public ArrayList<Book> rankBooks(ArrayList<Book> bookList, String[] tags);
}
