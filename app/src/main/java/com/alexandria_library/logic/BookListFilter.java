package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BookListFilter implements IBookListFilter {
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
