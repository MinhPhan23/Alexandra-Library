package com.alexandria_library.logic;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.data.stub.BookPersistentInterStub;
import com.alexandria_library.dso.Booklist;

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
        List<String> keywordList = Arrays.asList(keywords.split(" "));
        Booklist result = new Booklist();
        Booklist bookByName = data.searchName(keywords);
        Booklist bookByAuthor = data.searchAuthor(keywords);
        Booklist bookByGenre = new Booklist();
        Booklist bookByTag = new Booklist();

        if(data instanceof BookPersistentHSQLDB){
            bookByGenre = ((BookPersistentHSQLDB) data).searchGenre(keywords);
            bookByTag = ((BookPersistentHSQLDB) data).searchTag(keywords);
        }
        else if(data instanceof BookPersistentInterStub){
            bookByGenre = ((BookPersistentInterStub) data).searchGenre(keywordList);
            bookByTag = ((BookPersistentInterStub) data).searchTag(keywordList);
        }

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
