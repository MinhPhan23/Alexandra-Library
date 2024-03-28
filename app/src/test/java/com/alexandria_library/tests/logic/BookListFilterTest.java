package com.alexandria_library.tests.logic;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.BookListFilter;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

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

    Book book6 = new Book(6, "bbaa", "fake", "1999-09-21",
            Arrays.asList("classic","niu"), Arrays.asList("funny", "haha"));


    private BookListFilter bookListFilter;
    private BookListFilter mockBookListFilter;
    private Booklist sampleBookList;
    private Booklist emptyBookList;
    private IBookPersistent bookPersistent;

    @Before
    public void setUp() {
        // Initialize your Booklist and IBookListFilter implementation
        bookListFilter = new BookListFilter();
        sampleBookList = new Booklist(Arrays.asList(book1, book2, book3, book4, book5, book6));
        emptyBookList = new Booklist();
        bookPersistent = mock(BookPersistentHSQLDB.class);
        mockBookListFilter = mock(BookListFilter.class);
    }

    @Test
    public void testSortByTitle() {
        Booklist sortedList = bookListFilter.sortByTitle(sampleBookList);
        Booklist expectedList = new Booklist(Arrays.asList(book3, book4, book1, book5, book2, book6));
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
        Booklist expectedList = new Booklist(Arrays.asList(book4, book1, book5, book3, book2, book6));
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
        Booklist expectedList = new Booklist(Arrays.asList(book1, book3, book2, book5, book4, book6));
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
        Booklist expectedList = new Booklist();
        expectedList.add(book1);
        expectedList.add(book4);
        expectedList.add(book6);
        Booklist filteredList = bookListFilter.filterByTag(sampleBookList, tagsToFilter);
        assertEquals(3, filteredList.size());
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
        Booklist allBooks = new Booklist();
        allBooks.add(book1);
        allBooks.add(book2);
        allBooks.add(book3);
        allBooks.add(book4);
        allBooks.add(book5);
        Booklist filteredList = bookListFilter.filterByGenre(sampleBookList, genresToFilter);
        assertEquals(5, filteredList.size());
        assertEquals(filteredList, allBooks);
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

    @Test
    public void testGetFilteredList(){
        String[] tags = new String[]{"classic", "romance", "mystery",
                "drama", "social justice", "coming-of-age"};
        String[] genres = new String[]{"fiction", "drama", "historical"};
        Booklist booklist = new Booklist(Arrays.asList(book1, book2));

        when(mockBookListFilter.getFilteredList(sampleBookList, tags, genres)).thenReturn(booklist);

        assertEquals(mockBookListFilter.getFilteredList(sampleBookList, tags, genres), booklist);
    }

    @Test
    public void testGetAllTags(){
        ArrayList<String> tags = new ArrayList<>(Arrays.asList("classic", "romance", "mystery",
                                                    "drama", "social justice", "coming-of-age"));
        when(bookPersistent.getAllTags()).thenReturn(tags);

        assertEquals(bookPersistent.getAllTags(), tags);
    }

    @Test
    public void testGetAllGenres(){
        ArrayList<String> genres = new ArrayList<>(Arrays.asList("fiction", "drama", "historical"));
        when(bookPersistent.getAllGenres()).thenReturn(genres);

        assertEquals(bookPersistent.getAllGenres(), genres);
    }
}
