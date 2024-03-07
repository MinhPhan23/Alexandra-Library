package com.alexandria_library.tests.data;

import static org.junit.Assert.assertTrue;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.BookListFilter;
import com.alexandria_library.logic.IBookListFilter;
import com.alexandria_library.tests.util.TestUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class BookListFilterIT {
    private BookListFilter bookListFilter;
    private Booklist libraryBooks;
    private File tempDB;

    @Before
    public void setUp() throws IOException{
        this.tempDB = TestUtils.copyDB();
        final IBookPersistent persistent = new BookPersistentHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        libraryBooks = persistent.getBookList();
        this.bookListFilter = new BookListFilter();
    }

    @Test
    public void testSortByTitle(){
        Booklist result = bookListFilter.sortByTitle(libraryBooks);
        if(result.size() >= 2){
            String previous = result.get(0).getName();
            for(int i = 1; i<result.size(); i++){
                String current = result.get(i).getName();
                assertTrue(previous.compareTo(current) > 0);
                previous = current;
            }
        }
    }
}
