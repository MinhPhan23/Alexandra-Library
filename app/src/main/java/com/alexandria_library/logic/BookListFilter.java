package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BookListFilter implements IBookListFilter {
    @Override
    public ArrayList<Book> sortByTitle(ArrayList<Book> bookList) {
        ArrayList<Book> sortedList = new ArrayList<>(bookList);
        sortedList.sort(Comparator.comparing(Book::getName));
        return sortedList;
    }

    @Override
    public ArrayList<Book> sortByDate(ArrayList<Book> bookList) {
        ArrayList<Book> sortedList = new ArrayList<>(bookList);
        sortedList.sort(Comparator.comparing(Book::getDate));
        return sortedList;
    }

    @Override
    public ArrayList<Book> sortByAuthor(ArrayList<Book> bookList) {
        ArrayList<Book> sortedList = new ArrayList<>(bookList);
        sortedList.sort(Comparator.comparing(Book::getAuthor));
        return sortedList;
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

    private static boolean containsAll(List<String> bookTags, String[] filterTags) {
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
