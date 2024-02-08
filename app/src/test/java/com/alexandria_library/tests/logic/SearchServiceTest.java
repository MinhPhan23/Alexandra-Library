package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.dso.Book;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SearchServiceTest {
    private SearchService searchService;
    @Before
    public void setUp() {
        System.out.println("Starting tests for SearchService");
        searchService = new SearchService();
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

        System.out.println("Test null String object");
        exception = assertThrows(SearchServiceException.class, () -> {
            searchService.searchInput(null);
        });
        expectedMessage = "Could not search for empty text";
        actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * This is an integration test
     */
    @Test
    public void testSearchByName() {
        System.out.println("Testing searching for books by name");
        String keywords = "The Three Alexander";
        String[] keyword = keywords.split(" ");
        try {
            ArrayList<Book> bookList = searchService.searchInput(keywords);
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
