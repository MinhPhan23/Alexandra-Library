package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.data.IBookPersistentStub;
import com.alexandria_library.data.stub.BookPersistentInterStub;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.ISearchService;
import com.alexandria_library.logic.SearchService;
import com.alexandria_library.logic.SearchServiceException;

import org.junit.Before;
import org.junit.Test;

public class SearchServiceTest {
    private ISearchService searchService;
    private IBookPersistentStub data;
    @Before
    public void setUp() {
        System.out.println("Starting tests for SearchService");
        data = new BookPersistentInterStub();
        searchService = new SearchService(data);
        assertNotNull(searchService);
    }

    @Test
    public void testEmptySearch() {
        System.out.println("Test empty keywords String");
        Exception exception = assertThrows(SearchServiceException.class, () -> {
            searchService.searchInput("");
        });

        String expectedMessage = "Could not search for empty text";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void testNullSearch() {
        System.out.println("Test null String object");
        Exception exception = assertThrows(SearchServiceException.class, () -> {
            searchService.searchInput(null);
        });
        String expectedMessage = "Could not search for empty text";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExactSearchName(){
        System.out.println("Testing searching for books by name");
        String keywords = "The Book Thief";
        try {
            Booklist bookList = searchService.searchInput(keywords);
            for (Book book : bookList) {
                String bookName = book.getName();
                assertTrue(bookName.contains(keywords));
            }
        } catch (SearchServiceException ignored) {

        }
    }

    @Test
    public void testExactSearchAuthor() {
        System.out.println("Testing searching for authors by name");
        String keywords = "Harper Lee";
        try {
            Booklist bookList = searchService.searchInput(keywords);
            for (Book book : bookList) {
                String authorName = book.getAuthor();
                assertTrue(authorName.contains(keywords));
            }
        } catch (SearchServiceException ignored) {

        }
    }
    @Test
    public void testMixBookAndAuthor() {
        System.out.println("Testing searching for books by name");
        String keywords = "The Three Alexander";
        String[] keyword = keywords.split(" ");
        try {
            Booklist bookList = searchService.searchInput(keywords);
            for (Book book : bookList) {
                String bookName = book.getName();
                boolean check = false;
                for (String word : keyword) {
                    if (bookName.contains(word)) {
                        check = true;
                        break;
                    }
                }
                assertTrue(check);
            }
        } catch (SearchServiceException ignored) {

        }
    }
}
