package com.alexandria_library.tests.dso;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.dso.Book;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class BookTest {
    @Before
    public void setUp() {
        System.out.println("Starting unit tests for Book");
    }

    @Test
    public void testCreateBook() {
        System.out.println("\nTest creating book");

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                new String[]{"Young Adult", "Twilight 1", "Vampires"},
                new String[]{"Urban Fantasy", "Paranormal", "Romance"});

        assertNotNull(book);
        assertEquals(1, book.getID());
        assertEquals("Twilight", book.getName());
        assertEquals("Stephenie Meyer", book.getAuthor());
        assertEquals("October 5, 2005", book.getDate());
        assertEquals(3, book.getTags().length);
        assertEquals(3, book.getGenres().length);
    }

    @Test
    public void testSetBookID() {
        System.out.println("\nTest set book id");

        int updatedID = 2;

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                new String[]{"Young Adult", "Twilight 1", "Vampires"},
                new String[]{"Urban Fantasy", "Paranormal", "Romance"});

        int originalID = book.getID();
        book.setID(updatedID);

        assertNotEquals(originalID, updatedID);
        assertEquals(updatedID, book.getID());
    }

    @Test
    public void testSetBookName() {
        System.out.println("\nTest set book name");

        String updatedName = "I have been changed!";

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                new String[]{"Young Adult", "Twilight 1", "Vampires"},
                new String[]{"Urban Fantasy", "Paranormal", "Romance"});

        String originalName = book.getName();
        book.setName(updatedName);

        assertNotEquals(originalName, updatedName);
        assertEquals(updatedName, book.getName());
    }

    @Test
    public void testSetBookAuthor() {
        System.out.println("\nTest set book author");

        String updatedAuthor = "I have been changed!";

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                new String[]{"Young Adult", "Twilight 1", "Vampires"},
                new String[]{"Urban Fantasy", "Paranormal", "Romance"});

        String originalAuthor = book.getAuthor();
        book.setAuthor(updatedAuthor);

        assertNotEquals(originalAuthor, updatedAuthor);
        assertEquals(updatedAuthor, book.getAuthor());
    }

    @Test
    public void testSetBookDate() {
        System.out.println("\nTest set book date");

        String updatedDate = "I have been changed!";

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                new String[]{"Young Adult", "Twilight 1", "Vampires"},
                new String[]{"Urban Fantasy", "Paranormal", "Romance"});

        String originalDate = book.getDate();
        book.setDate(updatedDate);

        assertNotEquals(originalDate, updatedDate);
        assertEquals(updatedDate, book.getDate());
    }

    @Test
    public void testSetBookTags() {
        System.out.println("\nTest set book tags");

        String[] updatedTags = new String[]{"I have been ", "Changed"};

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                new String[]{"Young Adult", "Twilight 1", "Vampires"},
                new String[]{"Urban Fantasy", "Paranormal", "Romance"});

        String[] originalTags = book.getTags();
        book.setTags(updatedTags);

        assertFalse(Arrays.equals(originalTags, updatedTags));
        assertArrayEquals(updatedTags, book.getTags());
    }

    @Test
    public void testSetBookGenres() {
        System.out.println("\nTest set book genres");

        String[] updatedGenres = new String[]{"I have been ", "Changed"};

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                new String[]{"Young Adult", "Twilight 1", "Vampires"},
                new String[]{"Urban Fantasy", "Paranormal", "Romance"});

        String[] originalGenres = book.getGenres();
        book.setGenres(updatedGenres);

        assertFalse(Arrays.equals(originalGenres, updatedGenres));
        assertArrayEquals(updatedGenres, book.getGenres());
    }

    @Test
    public void testBookEquals() {
        System.out.println("\nTest comparing book");

        Book book1 = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                new String[]{"Young Adult", "Twilight 1", "Vampires"},
                new String[]{"Urban Fantasy", "Paranormal", "Romance"});

        Book book2 = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                new String[]{"Young Adult", "Twilight 1", "Vampires"},
                new String[]{"Urban Fantasy", "Paranormal", "Romance"});

        Book book3 = new Book(2 ,
                "To Kill a Mockingbird",
                "Harper Lee",
                "July 11, 1960",
                new String[]{"High School", "Literature"},
                new String[]{"Classics", "Fiction", "Historical Fiction", "Young Adult"});

        assertTrue(book1.equals(book2));
        assertFalse(book1.equals(book3));
    }

    @Test
    public void testBookToString() {
        System.out.println("\nTest printing book object");

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                new String[]{"Young Adult", "Twilight 1", "Vampires"},
                new String[]{"Urban Fantasy", "Paranormal", "Romance"});


        String original = "Book{id = 1, name = 'Twilight', author = 'Stephenie Meyer', " +
                "date = 'October 5, 2005', tag(s) = [Young Adult, Twilight 1, Vampires], " +
                "genre(s) = [Urban Fantasy, Paranormal, Romance]}";
        String test = book.toString();

        assertEquals(original, test);
    }



}
