package com.alexandria_library.tests.logic;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.BookListFilter;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookListFilterTest {
    Book book1 = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "1925-04-10",
            Arrays.asList("classic", "romance", "mystery"), Arrays.asList("fiction", "drama"));

    Book book2 = new Book(2, "To Kill a Mockingbird", "Harper Lee", "1960-07-11",
            Arrays.asList("drama", "social justice", "coming-of-age"), Arrays.asList("fiction", "historical"));

    Book book3 = new Book(3, "1984", "George Orwell", "1949-06-08",
            Arrays.asList("dystopian", "political", "science fiction"), Arrays.asList("fiction", "science fiction"));

    Book book4 = new Book(4, "Pride and Prejudice", "Jane Austen", "1813-01-28",
            Arrays.asList("romance", "classic", "humor"), Arrays.asList("fiction", "romantic"));

    Book book5 = new Book(5, "The Hobbit", "J.R.R. Tolkien", "1937-09-21",
            Arrays.asList("fantasy", "adventure", "epic"), Arrays.asList("fiction", "fantasy"));


    private BookListFilter bookListFilter;
    private Booklist sampleBookList;
    private Booklist emptyBookList;

    @Before
    public void setUp() {
        // Initialize your Booklist and IBookListFilter implementation
        bookListFilter = new BookListFilter();
        sampleBookList = new Booklist(Arrays.asList(book1, book2, book3, book4, book5));
        emptyBookList = new Booklist();
    }

    @Test
    public void testSortByTitle() {
        Booklist sortedList = bookListFilter.sortByTitle(sampleBookList);
        Booklist expectedList = new Booklist(Arrays.asList(book3, book4, book1, book5, book2));
        assertEquals(expectedList, sortedList);
    }

    @Test
    public void testSortByTitleEmpty(){
        Booklist sortedList = bookListFilter.sortByTitle(emptyBookList);
        Booklist expectedList = new Booklist(Arrays.asList());
        assertEquals(expectedList, sortedList);
    }

    @Test
    public void testSortByDate() {
        Booklist sortedList = bookListFilter.sortByDate(sampleBookList);
        Booklist expectedList = new Booklist(Arrays.asList(book4, book1, book5, book3, book2));
        assertEquals(expectedList, sortedList);
    }

    @Test
    public void testSortByDateEmpty(){
        Booklist sortedList = bookListFilter.sortByDate(emptyBookList);
        Booklist expectedList = new Booklist(Arrays.asList());
        assertEquals(expectedList, sortedList);
    }

    @Test
    public void testSortByAuthor() {
        Booklist sortedList = bookListFilter.sortByAuthor(sampleBookList);
        Booklist expectedList = new Booklist(Arrays.asList(book1, book3, book2, book5, book4));
        assertEquals(expectedList, sortedList);
    }

    @Test
    public void testSortByAuthorEmpty(){
        Booklist sortedList = bookListFilter.sortByAuthor(emptyBookList);
        Booklist expectedList = new Booklist(Arrays.asList());
        assertEquals(expectedList, sortedList);
    }

    @Test
    public void testFilterByTag() {
        String[] tagsToFilter = {"romance", "classic"};
        Booklist filteredList = bookListFilter.filterByTag(sampleBookList, tagsToFilter);
        Booklist expectedList = new Booklist(Arrays.asList(book1, book4));
        assertEquals(expectedList, filteredList);
    }

    @Test
    public void testFilterByTagEmpty(){
        String[] tagsToFilter = {"romance", "classic"};
        Booklist filteredList = bookListFilter.filterByTag(emptyBookList, tagsToFilter);
        Booklist expectedList = new Booklist(Arrays.asList());
        assertEquals(expectedList, filteredList);
    }

    @Test
    public void testFilterByGenre() {
        String[] genresToFilter = {"fiction", "fantasy"};
        Booklist filteredList = bookListFilter.filterByGenre(sampleBookList, genresToFilter);
        Booklist expectedList = new Booklist(Arrays.asList(book5));
        assertEquals(expectedList, filteredList);
    }

    @Test
    public void testFilterByGenreEmpty(){
        String[] genresToFilter = {"fiction", "fantasy"};
        Booklist filteredList = bookListFilter.filterByGenre(emptyBookList, genresToFilter);
        Booklist expectedList = new Booklist(Arrays.asList());
        assertEquals(expectedList, filteredList);
    }

    @Test
    public void testFilterByAuthor() {
        String[] authorsToFilter = {"Jane Austen", "J.R.R. Tolkien"};
        Booklist filteredList = bookListFilter.filterByAuthor(sampleBookList, authorsToFilter);
        Booklist expectedList = new Booklist(Arrays.asList(book4, book5));
        assertEquals(expectedList, filteredList);
    }

    @Test
    public void testFilterByAuthorEmpty(){
        String[] authorsToFilter = {"Jane Austen", "J.R.R. Tolkien"};
        Booklist filteredList = bookListFilter.filterByAuthor(emptyBookList, authorsToFilter);
        Booklist expectedList = new Booklist(Arrays.asList());
        assertEquals(expectedList, filteredList);
    }
}
