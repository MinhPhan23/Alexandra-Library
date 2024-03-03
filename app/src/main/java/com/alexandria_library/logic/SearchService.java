package com.alexandria_library.logic;

import com.alexandria_library.data.IBookPersistentIntermediate;
import com.alexandria_library.dso.Booklist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchService implements ISearchService{
    private final IBookPersistentIntermediate data;
    private final IBookListRanker bookListRanker;

    public SearchService(IBookPersistentIntermediate data) {
        this. data = data;
        bookListRanker = new BookListRanker();
    }

    @Override
    public Booklist searchInput(String keywords) throws SearchServiceException {
        if (keywords == null || keywords.length() == 0) {
            throw new SearchServiceException("Could not search for empty text");
        }
        Booklist result = queryDatabase(keywords);
        result = bookListRanker.rankBooks(result, keywords.split(" "));
        return result;
    }

    private Booklist queryDatabase(String keywords) throws SearchServiceException{
        List<String> keywordList = Arrays.asList(keywords.split(" "));
        Booklist result = new Booklist();
        Booklist bookByName = data.searchName(keywords);
        Booklist bookByAuthor = data.searchAuthor(keywords);
        Booklist bookByGenre = data.searchGenre(keywordList);
        Booklist bookByTag = data.searchTag(keywordList);
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
