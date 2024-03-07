package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.BookListFilter;
import com.alexandria_library.tests.util.TestUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BookListFilterITest {
    private BookListFilter bookListFilter;
    private Booklist libraryBooks;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
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
    @Test
    public void testSortByData(){
        Booklist result = bookListFilter.sortByDate(libraryBooks);
        if(result.size() >= 2){
            String previous = result.get(0).getDate();
            for(int i = 1; i<result.size(); i++){
                String current = result.get(i).getDate();
                assertTrue(previous.compareTo(current) > 0);
                previous = current;
            }
        }
    }

    @Test
    public void testSortByAuthor(){
        Booklist result = bookListFilter.sortByAuthor(libraryBooks);
        if(result.size() >= 2){
            String previous = result.get(0).getAuthor();
            for(int i = 1; i<result.size(); i++){
                String current = result.get(i).getAuthor();
                assertTrue(previous.compareTo(current) > 0);
                previous = current;
            }
        }
    }
    @Test
    public void testFailureFilterByTag(){
        String[] nonExistTags = {"xxx", "uuu", "xxx"};
        Booklist getFiltered = bookListFilter.filterByTag(libraryBooks, nonExistTags);
        assertEquals(0, getFiltered.size());
    }
    @Test
    public void testSuccussFitlerByTag1(){
        String[] existTags = {"LGBT"};
        Booklist getFiltered = bookListFilter.filterByTag(libraryBooks, existTags);
        for(int i = 0; i<getFiltered.size(); i++){
            List<String> tags = getFiltered.get(i).getTags();
            assertTrue(tags.contains("LGBT"));
        }
    }
    @Test
    public void testSuccussFitlerByTag2(){
        String[] existTags = {"High School", "Coming of Age"};
        Booklist getFiltered = bookListFilter.filterByTag(libraryBooks, existTags);
        for(int i = 0; i<getFiltered.size(); i++){
            List<String> tags = getFiltered.get(i).getTags();
            assertTrue(tags.contains("High School")|| tags.contains("Coming of Age"));
        }
    }

    @Test
    public void testSuccussFitlerByTag3(){
        String[] existTags = {"Books About Books", "Coming of Age", "Hunger Games 1"};
        Booklist getFiltered = bookListFilter.filterByTag(libraryBooks, existTags);
        System.out.println(getFiltered.toString());
        for(int i = 0; i<getFiltered.size(); i++){
            List<String> tags = getFiltered.get(i).getTags();

            assertTrue(tags.contains("Books About Books")
                            ||tags.contains("Coming of Age")
                            ||tags.contains("Hunger Games 1"));
        }
    }

    @Test
    public void testFailureFilterByGenre(){
        String[] nonExistTags = {"xxx", "uuu", "xxx"};
        Booklist getFiltered = bookListFilter.filterByGenre(libraryBooks, nonExistTags);
        assertEquals(0, getFiltered.size());
    }
    @Test
    public void testSuccussFitlerByGenre1(){
        String[] existGenre = {"Romance"};
        Booklist getFiltered = bookListFilter.filterByGenre(libraryBooks, existGenre);
        for(int i = 0; i<getFiltered.size(); i++){
            List<String> genre = getFiltered.get(i).getGenres();
            assertTrue(genre.contains("Romance"));
        }
    }
    @Test
    public void testSuccussFitlerByGenre2(){
        String[] existGenre = {"Science Fiction", "Classics"};
        Booklist getFiltered = bookListFilter.filterByGenre(libraryBooks, existGenre);
        for(int i = 0; i<getFiltered.size(); i++){
            List<String> genres = getFiltered.get(i).getGenres();
            assertTrue(genres.contains("Science Fiction")|| genres.contains("Classics"));
        }
    }

    @Test
    public void testSuccussFitlerByGenre3(){
        String[] existGenre = {"Dystopia", "Fiction", "War"};
        Booklist getFiltered = bookListFilter.filterByTag(libraryBooks, existGenre);
        System.out.println(getFiltered.toString());
        for(int i = 0; i<getFiltered.size(); i++){
            List<String> genre = getFiltered.get(i).getGenres();

            assertTrue(genre.contains("Dystopia")
                    ||genre.contains("Fiction")
                    ||genre.contains("War"));
        }
    }

    @Test
    public void testFailureFilterByAuthor(){
        String[] nonExistAuthors = {"xxx", "uuu", "xxx"};
        Booklist getFiltered = bookListFilter.filterByAuthor(libraryBooks, nonExistAuthors);
        assertEquals(0, getFiltered.size());
    }
    @Test
    public void testSuccussFitlerByAuthor1(){
        String[] existAuthor = {"Taylor Jenkins Reid"};
        Booklist getFiltered = bookListFilter.filterByAuthor(libraryBooks, existAuthor);
        for(int i = 0; i<getFiltered.size(); i++){
            assertEquals(getFiltered.get(i).getAuthor(),"Taylor Jenkins Reid");
        }

    }
    @Test
    public void testGetFilteredList(){
        String[] existGenre = {"Romance"};
        String[] existTags = {"LGBT"};
        Booklist fitered = bookListFilter.getFilteredList(libraryBooks, existTags, existGenre);
        System.out.println(fitered.toString());
        assertFalse(fitered.isEmpty());
    }
}
