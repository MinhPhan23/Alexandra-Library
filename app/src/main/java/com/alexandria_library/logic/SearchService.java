package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;

import java.util.ArrayList;

public class SearchService implements ISearchService{
    private final BookPersistentIntermediate data;
    private final InfoOrganizer infoOrganizer;
    public SearchService() {
        data = new BookPersistentIntermediate();
        infoOrganizer = new InfoOrganizer();
    }

    @Override
    public ArrayList<Book> searchInput(String keywords) throws SearchServiceException {
        if (keywords == null || keywords.length() == 0) {
            throw new SearchServiceException("Could not search for empty text");
        }
        String[] keywordList = keywords.split(" ");
        ArrayList<Book> result = queryDatabase(keywordList);
        result = infoOrganizer.sort(result, keywordList);
        return result;
    }

    private ArrayList<Book> queryDatabase(String[] keywordList) throws SearchServiceException{
        ArrayList<Book> result = new ArrayList<>();
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
            e.printStackTrace();
            throw new SearchServiceException("Could not search for the result, please try again");
        }
        return result;
    }
}
