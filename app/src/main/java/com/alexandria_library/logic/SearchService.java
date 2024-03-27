package com.alexandria_library.logic;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.data.stub.BookPersistentInterStub;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.Exception.SearchServiceException;

import java.util.Arrays;
import java.util.List;

public class SearchService implements ISearchService{
    private final IBookPersistent data;
    private final IBookListRanker bookListRanker;

    public SearchService(){
        bookListRanker = new BookListRanker();
        data = Service.getBookPersistent();
    }
    public SearchService(IBookPersistent data) {
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
        Booklist result = new Booklist();
        Booklist bookByName = data.searchName(keywords);
        Booklist bookByAuthor = data.searchAuthor(keywords);
        Booklist bookByGenre = data.searchGenre(keywords);
        Booklist bookByTag = data.searchTag(keywords);

        if(bookByName == null && bookByAuthor == null && bookByGenre == null && bookByTag == null) {
            throw new SearchServiceException("Could not search for the result, please try again");
        }
        else {
            result = addToList(result, bookByName);
            result = addToList(result, bookByAuthor);
            result = addToList(result, bookByGenre);
            result = addToList(result, bookByTag);
        }
        return result;
    }

    private Booklist addToList(Booklist mainList, Booklist addition){
        if(addition != null){
            mainList.addAll(addition);
        }
        return mainList;
    }
}
