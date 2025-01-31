package com.alexandria_library.tests.logic;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.BookListRanker;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class BookListRankerTest {
    Book book1 = new Book(0, "The Three Musketeers", "Alexandre Dumas", "July 1844", new ArrayList<>(),  new ArrayList<>(Arrays.asList("Novel", "Adventure", "historical novel", "Historical Fiction", "Adventure fiction")));

    Book book2 = new Book(1, "The Three Body Problem", "Liu Cixin", "2008", new ArrayList<>(),  new ArrayList<>(Arrays.asList("Novel", "Science fiction", "Speculative novel", "Chinese science fiction")));
    Book book3 = new Book(2, "The three", "Sarah Lotz", "20 May 2014", new ArrayList<>(), new ArrayList<>(Arrays.asList("Thriller", "Horror fiction", "Suspense", "Psychological Fiction", "Dystopian Fiction", "Supernatural fiction", "Religious Fiction")));

    Book book4 = new Book(3, "The three little pigs tales", "Emma Hall", "2 September 2014", new ArrayList<>(), new ArrayList<>(Arrays.asList("Folktale")));
    Book book5 = new Book(4, "The Three Mercenaries", "Minh Phan", "6 February 2023", new ArrayList<>(), new ArrayList<>(Arrays.asList("Novel", "Science Fiction", "Fantasy")));

    Booklist bookList = new Booklist(Arrays.asList(book1, book2, book3, book4, book5));

    BookListRanker bookListRanker;
    @Before
    public void setUp() {
        System.out.println("Starting unit tests for SearchService");
        bookListRanker = new BookListRanker();
        assertNotNull(bookListRanker);
    }
    @Test
    public void testRankBook1() {
        String keywords = "The three";
        String[] keywordList = keywords.split(" ");
        Booklist rankedBookList = bookListRanker.rankBooks(bookList, keywordList);
        Booklist expected = new Booklist(Arrays.asList(book3, book1, book2, book5, book4));
        assertEquals(expected, rankedBookList);
    }

    @Test
    public void testRankBook2() {
        String keywords = "The three Alexander";
        String[] keywordList = keywords.split(" ");
        Booklist rankedBookList = bookListRanker.rankBooks(bookList, keywordList);
        Booklist expected = new Booklist(Arrays.asList(book1, book3, book2, book5, book4));
        assertEquals(expected, rankedBookList);
    }

    @Test
    public void testRankBook3() {
        String keywords = "The three science fiction novel";
        String[] keywordList = keywords.split(" ");
        Booklist rankedBookList = bookListRanker.rankBooks(bookList, keywordList);
        Booklist expected = new Booklist(Arrays.asList(book3, book2, book5, book1, book4));
        assertEquals(expected, rankedBookList);
    }
}
