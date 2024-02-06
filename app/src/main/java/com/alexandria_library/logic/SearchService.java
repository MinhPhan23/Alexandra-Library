package com.alexandria_library.logic;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.IBookPersistentIntermediate;
import com.alexandria_library.dso.Book;

import java.util.ArrayList;

public class SearchService implements ISearchService{
    BookPersistentIntermediate data;
    public SearchService() {
        data = new BookPersistentIntermediate();
    }

    @Override
    public ArrayList<Book> searchInput(String keywords) {
        ArrayList<Book> result = new ArrayList<Book>();
        String[] keywordList = keywords.split(" ");
        ArrayList<Book> bookByName = data.searchByName(keywordList);
        ArrayList<Book> bookByAuthor = data.searchByAuthor(keywordList);
        ArrayList<Book> bookByGenre = data.searchByGenre(keywordList);
        ArrayList<Book> bookByTag = data.searchByTag(keywordList);
        try {
            result.addAll(bookByName);
            result.addAll(bookByAuthor);
            result.addAll(bookByGenre);
            result.addAll(bookByTag);
        }
        catch (NullPointerException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public ArrayList<Book> filterInput(String[] filters) {
        ArrayList<Book> result = null;
        return result;
    }

}
