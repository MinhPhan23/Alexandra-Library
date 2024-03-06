package com.alexandria_library.tests.data;

import static org.junit.Assert.assertNotNull;

import com.alexandria_library.application.Main;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.data.stub.BookPersistentInterStub;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Librarian;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BookPersistentTest {

    //STUB TEST VARIABLES
    private IBookPersistent dataStub;

    private IBookPersistent dataReal;

    Book book;

    Librarian librarian;
    List<String> defaultTags = new ArrayList<>();
    List<String>defaultGenres = new ArrayList<>();

    @Before
    public void setUp(){
        defaultTags.add("c");
        defaultTags.add("d");
        defaultTags.add("e");
        defaultGenres.add("f");
        defaultGenres.add("g");
        defaultGenres.add("h");
        book = new Book(0, "a", "b", "0000-00-00", defaultTags, defaultGenres);
        librarian = new Librarian("test", "test", 0);

        dataReal = new BookPersistentHSQLDB(Main.getDBPathName());
        dataStub = new BookPersistentInterStub();

        assertNotNull(dataReal);
        assertNotNull(dataStub);
        assertNotNull(librarian);
        assertNotNull(book);
        assertNotNull(defaultTags);
        assertNotNull(defaultGenres);
    }

    /////////////////////////////STUB TESTS//////////////////////////////

    @Test
    public void credentialsTest1(){
        System.out.println("Testing checkCredentials(User user) 1");

    }

    @Test
    public void updateTest1(){
        System.out.println("Testing update(Book book, User user) 1");
    }

    @Test
    public void searchAuthorTest1(){
        System.out.println("Testing searchAuthor(String author) 1");
    }

    @Test
    public void searchNameTest1(){
        System.out.println("Testing searchName(String bookName) 1");
    }

    @Test
    public void getBookListTest1(){
        System.out.println("Testing getBookList() 1");
    }

    ///////////////////////////REAL TESTS//////////////////////////////

    @Test
    public void credentialsTest2(){
        System.out.println("Testing checkCredentials(User user) 2");
    }

    @Test
    public void updateTest2(){
        System.out.println("Testing update(Book book, User user) 2");
    }

    @Test
    public void searchAuthorTest2(){
        System.out.println("Testing searchAuthor(String author) 2");
    }

    @Test
    public void searchNameTest2(){
        System.out.println("Testing searchName(String bookName) 2");
    }

    @Test
    public void getBookListTest2(){
        System.out.println("Testing getBookList() 2");
    }
}
