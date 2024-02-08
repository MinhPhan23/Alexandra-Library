package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;
import com.alexandria_library.data.stub.BookPersistentInterStub;

import java.util.ArrayList;

public class SearchService implements ISearchService{
    private final BookPersistentInterStub data;
    //private final InfoOrganizer infoOrganizer;
    public SearchService() {
        data = new BookPersistentInterStub();
        //infoOrganizer = new InfoOrganizer();
    }

    @Override
    public ArrayList<Book> searchInput(String keywords) throws SearchServiceException {
        if (keywords == null || keywords.length() == 0) {
            throw new SearchServiceException("Could not search for empty text");
        }
        ArrayList<Book> result = queryDatabase(keywords);
        //result = infoOrganizer.sort(result, keywordList);
        return result;
    }

    private ArrayList<Book> queryDatabase(String keywords) throws SearchServiceException{
        String[] keywordList = keywords.split(" ");
        ArrayList<Book> result = new ArrayList<>();
        ArrayList<Book> bookByName = data.searchName(keywords);
        ArrayList<Book> bookByAuthor = data.searchAuthor(keywords);
        ArrayList<Book> bookByGenre = data.searchGenre(keywordList);
        ArrayList<Book> bookByTag = data.searchTag(keywordList);
        try {
            result.addAll(bookByName);
            result.addAll(bookByAuthor);
            result.addAll(bookByGenre);
            result.addAll(bookByTag);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            throw new SearchServiceException("Could not search for the result, please try again");
        }
        return result;
    }
}
