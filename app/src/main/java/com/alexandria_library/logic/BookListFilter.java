package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;

import java.util.ArrayList;
import java.util.Arrays;

public class BookListFilter implements IBookListFilter {
    @Override
    public ArrayList<Book> sortByTitle(ArrayList<Book> bookList) {
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
        ArrayList<Book> filteredBooks = new ArrayList<>();

        for (Book book : bookList) {
            if (containsAll(book.getTags(), tags)) {
                filteredBooks.add(book);
            }
        }

        return filteredBooks;
    }

    @Override
    public ArrayList<Book> filterByGenre(ArrayList<Book> bookList, String[] genres) {
        ArrayList<Book> filteredBooks = new ArrayList<>();

        for (Book book : bookList) {
            if (containsAll(book.getGenres(), genres)) {
                filteredBooks.add(book);
            }
        }

        return filteredBooks;
    }

    @Override
    public ArrayList<Book> filterByAuthor(ArrayList<Book> bookList, String[] authors) {
        ArrayList<Book> filteredBooks = new ArrayList<>();

        for (Book book : bookList) {
            if (Arrays.asList(authors).contains(book.getAuthor())) {
                filteredBooks.add(book);
            }
        }

        return filteredBooks;
    }

    private static boolean containsAll(String[] bookTags, String[] filterTags) {
        for (String filterTag : filterTags) {
            boolean containsString = false;
            for (String bookTag : bookTags) {
                if (filterTag.equals(bookTag)) {
                    containsString = true;
                    break;
                }
            }
            if (!containsString) {
                return false;
            }
        }
        return true;
    }
}
