package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;

import java.util.ArrayList;

public class InfoOrganizer implements IInfoOrganizer{
    @Override
    public ArrayList<Book> sortByTitle(ArrayList<Book> bookList) {
        //bookList.sort();
        return null;
    }

    @Override
    public ArrayList<Book> sortByDate(ArrayList<Book> bookList) {
        return null;
    }

    @Override
    public ArrayList<Book> sortByAuthor(ArrayList<Book> bookList) {
        return null;
    }

    @Override
    public ArrayList<Book> filterByTag(ArrayList<Book> bookList, String[] tags) {
        return null;
    }

    @Override
    public ArrayList<Book> filterByGenre(ArrayList<Book> bookList, String[] genres) {
        return null;
    }

    @Override
    public ArrayList<Book> filterByAuthor(ArrayList<Book> bookList, String[] authors) {
        return null;
    }

    @Override
    public ArrayList<Book> rankBooks(ArrayList<Book> bookList, String[] keywords){
        ArrayList<Book> rankedList = new ArrayList<Book>();


        return rankedList;
    }
}
