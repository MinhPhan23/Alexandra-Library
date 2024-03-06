package com.alexandria_library.tests.data;

import static org.junit.Assert.assertNotNull;

import com.alexandria_library.application.Main;
import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.dso.Book;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BookPersistentHSQLDBTest {

    private IBookPersistent data;

    Book book;

    List<String> defaultTags;
    List<String>defaultGenres;

    @Before
    public void setUp(){
        defaultTags = new ArrayList<>();
        defaultGenres = new ArrayList<>();
        defaultTags.add("c");
        defaultTags.add("d");
        defaultTags.add("e");
        defaultGenres.add("f");
        defaultGenres.add("g");
        defaultGenres.add("h");
        book = new Book(0, "a", "b", "0000-00-00", defaultTags, defaultGenres);
        data = new BookPersistentHSQLDB(Main.getDBPathName());
        assertNotNull(book);
        assertNotNull(data);
        assertNotNull(defaultTags);
        assertNotNull(defaultGenres);
    }

    ////////////////////////////////////////////
    /////////////////UNIT TESTS/////////////////
    ////////////////////////////////////////////

    @Test
    public void uploadTest(){
        System.out.println("Testing upload(Book, User)");
        System.out.println(data.getBookList().get(0).getName());
    }

    @Test
    public void deleteLibraryBookTest(){
        System.out.println("Testing deleteLibraryBook(Booklist, User");
    }

    @Test
    public void searchTagByBookTest(){
        System.out.println("Testing searchTagByBook(Book)");
    }

    @Test
    public void searchTagTest(){
        System.out.println("Testing searchTag(String)");
    }

    @Test
    public void searchGenreTest(){
        System.out.println("Testing searchGenre(String)");
    }

    @Test
    public void getUserCustomListTest(){
        System.out.println("Testing getUserCustomList(User)");
    }

    @Test
    public void getUserInProgressListTest(){
        System.out.println("Testing getUserInProgressList(User)");
    }

    @Test
    public void getUserFinishedListTest(){
        System.out.println("Testing getUserFinishedList(User)");
    }

    ////////////////////////////////////////////
    /////////////INTEGRATION TESTS//////////////
    ////////////////////////////////////////////
}
