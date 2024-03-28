package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.ISearchService;
import com.alexandria_library.logic.SearchService;
import com.alexandria_library.logic.Exception.SearchServiceException;
import com.alexandria_library.tests.util.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class SearchServiceITest {
    private ISearchService searchService;
    private File tempDB;
    @Before
    public void setUp() throws IOException {
        System.out.println("Starting tests for SearchService");
        this.tempDB = TestUtils.copyDB();
        IBookPersistent bookPersistent = new BookPersistentHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        searchService = new SearchService(bookPersistent);
        assertNotNull(searchService);
    }

    @Test
    public void testEmptySearch() {
        System.out.println("Test empty keywords String");
        assertThrows(SearchServiceException.class, () -> {
            searchService.searchInput("");
        });
    }

    @Test
    public void testEmptySearchReturnMessage() {
        System.out.println("Test empty keywords String");
        String returnMessage = "";
        String expectedMessage = "Could not search for empty text";
        try{
            searchService.searchInput("");
        } catch (SearchServiceException e) {
            returnMessage = e.getMessage();
        }

        assertTrue(returnMessage.contains(expectedMessage));
    }

    @Test
    public void testNullSearch() {
        System.out.println("Test null String object");
        assertThrows(SearchServiceException.class, () -> {
            searchService.searchInput(null);
        });
    }

    @Test
    public void testNullSearchReturnMessage() {
        System.out.println("Test empty keywords String");
        String returnMessage = "";
        String expectedMessage = "Could not search for empty text";
        try{
            searchService.searchInput("");
        } catch (SearchServiceException e) {
            returnMessage = e.getMessage();
        }

        assertTrue(returnMessage.contains(expectedMessage));
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
        } catch (SearchServiceException e) {
            assert(false);
            System.out.println("Something wrong with the test");
            e.printStackTrace();
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
        } catch (SearchServiceException e) {
            assert(false);
            System.out.println("Something wrong with the test");
            e.printStackTrace();
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
        } catch (SearchServiceException e) {
            assert(false);
            System.out.println("Something wrong with the test");
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        this.tempDB.delete();
    }
}
