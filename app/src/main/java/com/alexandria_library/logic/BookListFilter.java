package com.alexandria_library.logic;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BookListFilter implements IBookListFilter {
    IBookPersistent bookPersistent;
    public BookListFilter(){
        bookPersistent = Service.getBookPersistent();
    }
    @Override
    public Booklist sortByTitle(Booklist bookList) {
        Booklist sortedList = new Booklist(bookList);
        sortedList.sort(Comparator.comparing(Book::getName));
        return sortedList;
    }

    @Override
    public Booklist sortByDate(Booklist bookList) {
        Booklist sortedList = new Booklist(bookList);
        sortedList.sort(Comparator.comparing(Book::getDate));
        return sortedList;
    }

    @Override
    public Booklist sortByAuthor(Booklist bookList) {
        Booklist sortedList = new Booklist(bookList);
        sortedList.sort(Comparator.comparing(Book::getAuthor));
        return sortedList;
    }

    @Override
    public Booklist filterByTag(Booklist bookList, String[] tags) {
        Booklist filteredBooks = new Booklist();

        for (Book book : bookList) {
            if (containsAll(book.getTags(), tags)) {
                filteredBooks.add(book);
            }
        }
        if (filteredBooks.isEmpty()){
            filteredBooks = new Booklist(bookList);
        }

        return filteredBooks;
    }

    @Override
    public Booklist filterByGenre(Booklist bookList, String[] genres) {
        Booklist filteredBooks = new Booklist();

        for (Book book : bookList) {
            if (containsAll(book.getGenres(), genres)) {
                filteredBooks.add(book);
            }
        }
        if (filteredBooks.isEmpty()){
            filteredBooks = new Booklist(bookList);
        }

        return filteredBooks;
    }

    @Override
    public Booklist filterByAuthor(Booklist bookList, String[] authors) {
        Booklist filteredBooks = new Booklist();

        for (Book book : bookList) {
            if (Arrays.asList(authors).contains(book.getAuthor())) {
                filteredBooks.add(book);
            }
        }

        return filteredBooks;
    }

    @Override
    public Booklist getFilteredList(Booklist books, String[] tags, String[] genres){
        Booklist tagFiltered = filterByTag(books, tags);
        return filterByGenre(tagFiltered, genres);
    }

    private static boolean containsAll(List<String> bookTags, String[] filterTags) {
        for (String filterTag : filterTags) {

            for (String bookTag : bookTags) {
                if (filterTag.equals(bookTag)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> getAllTags(IBookPersistent bookPersistent){
        ArrayList<String> allTags = new ArrayList<>();
        if(bookPersistent instanceof BookPersistentHSQLDB){
            allTags = bookPersistent.getAllTags();
        }
        return allTags;
    }

    @Override
    public ArrayList<String > getAllGenre(IBookPersistent bookPersistent){
        ArrayList<String> allGenres = new ArrayList<>();
        if(bookPersistent instanceof BookPersistentHSQLDB){
            allGenres = bookPersistent.getAllGenres();
        }
        return allGenres;
    }
}
