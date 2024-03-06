package com.alexandria_library.tests.data;

import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.logic.SearchService;
import com.alexandria_library.logic.SearchServiceException;
import com.alexandria_library.tests.util.dbCopy;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class BookPersistentHSQLDBTest {

    SearchService searchService;

    private File tempDB;

    @Before
    public void setUp(){
        tempDB = dbCopy.dbCopy();

        searchService = new SearchService();
    }

    ////////////////////////////////////////////
    /////////////////UNIT TESTS/////////////////
    ////////////////////////////////////////////

    @Test
    public void searchInputTest(){
        System.out.println("Testing upload(Book, User)");
        try {
            System.out.println(searchService.searchInput("test idk whast going on").get(0).getName());
        } catch (SearchServiceException e) {
            System.out.println("Error fuckkkk");;
        }
    }
}
