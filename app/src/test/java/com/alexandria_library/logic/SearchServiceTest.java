package com.alexandria_library.logic;

import org.junit.*;
import static org.junit.Assert.*;
import com.alexandria_library.logic.SearchService;
import com.alexandria_library.dso.*;

import java.util.ArrayList;

public class SearchServiceTest {
    private SearchService searchService;
    @Before
    public void setUp() {
        System.out.println("Starting unit tests for SearchService");
        searchService = new SearchService();
        assertNotNull(searchService);
    }

    @Test
    public void testSearchByName() {
        System.out.println("Testing searching for books by name");
        String keywords = "The Three Musketeers";
        String[] keyword = keywords.split(" ");
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
    }
}
