package com.alexandria_library.tests.data;

import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.logic.SearchService;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.Exception.SearchServiceException;
import com.alexandria_library.tests.util.TestUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class BookPersistentHSQLDBTest {

    SearchService searchService;

    private File tempDB;

    @Before
    public void setUp() throws IOException {
        tempDB = TestUtils.copyDB();
        BookPersistentHSQLDB db = new BookPersistentHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));

        searchService = new SearchService(db);
    }

    ////////////////////////////////////////////
    /////////////////UNIT TESTS/////////////////
    ////////////////////////////////////////////

    @Test
    public void dummyTest() throws SearchServiceException{
        System.out.println("Testing upload(Book, User)");
        Booklist result = searchService.searchInput("The");
        for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).getName());
        }
    }
}
