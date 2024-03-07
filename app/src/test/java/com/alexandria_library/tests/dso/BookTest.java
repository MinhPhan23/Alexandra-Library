package com.alexandria_library.tests.dso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.dso.Book;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookTest {
    @Before
    public void setUp() {
        System.out.println("Starting unit tests for Book");
    }

    @Test
    public void testCreateBook() {
        System.out.println("\nTest creating book");
        String[] tagsArray = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String[] genreArray = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        List<String> tags = new ArrayList<>(Arrays.asList(tagsArray));
        List<String> genres = new ArrayList<>(Arrays.asList(genreArray));

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                tags,
                genres
                );

        assertNotNull(book);
        assertEquals(1, book.getID());
        assertEquals("Twilight", book.getName());
        assertEquals("Stephenie Meyer", book.getAuthor());
        assertEquals("October 5, 2005", book.getDate());
        assertEquals(3, book.getTags().size());
        assertEquals(3, book.getGenres().size());
    }

    @Test
    public void testSetBookID() {
        System.out.println("\nTest set book id");

        String[] tagsArray = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String[] genreArray = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        List<String> tags = new ArrayList<>(Arrays.asList(tagsArray));
        List<String> genres = new ArrayList<>(Arrays.asList(genreArray));

        int updatedID = 2;

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                tags,
                genres
                );

        int originalID = book.getID();
        book.setID(updatedID);

        assertNotEquals(originalID, updatedID);
        assertEquals(updatedID, book.getID());
    }

    @Test
    public void testSetBookName() {
        System.out.println("\nTest set book name");

        String[] tagsArray = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String[] genreArray = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        List<String> tags = new ArrayList<>(Arrays.asList(tagsArray));
        List<String> genres = new ArrayList<>(Arrays.asList(genreArray));

        String updatedName = "I have been changed!";

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                tags,
                genres);

        String originalName = book.getName();
        book.setName(updatedName);

        assertNotEquals(originalName, updatedName);
        assertEquals(updatedName, book.getName());
    }

    @Test
    public void testSetBookAuthor() {
        System.out.println("\nTest set book author");

        String[] tagsArray = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String[] genreArray = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        List<String> tags = new ArrayList<>(Arrays.asList(tagsArray));
        List<String> genres = new ArrayList<>(Arrays.asList(genreArray));

        String updatedAuthor = "I have been changed!";

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                tags,
                genres);

        String originalAuthor = book.getAuthor();
        book.setAuthor(updatedAuthor);

        assertNotEquals(originalAuthor, updatedAuthor);
        assertEquals(updatedAuthor, book.getAuthor());
    }

    @Test
    public void testSetBookDate() {
        System.out.println("\nTest set book date");

        String[] tagsArray = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String[] genreArray = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        List<String> tags = new ArrayList<>(Arrays.asList(tagsArray));
        List<String> genres = new ArrayList<>(Arrays.asList(genreArray));

        String updatedDate = "I have been changed!";

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                tags,
                genres);

        String originalDate = book.getDate();
        book.setDate(updatedDate);

        assertNotEquals(originalDate, updatedDate);
        assertEquals(updatedDate, book.getDate());
    }

    @Test
    public void testSetBookTags() {
        System.out.println("\nTest set book tags");

        String[] tagsArray = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String[] genreArray = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        String[] updatedTagsArray = new String[]{"I have been ", "Changed"};
        List<String> tags = new ArrayList<>(Arrays.asList(tagsArray));
        List<String> genres = new ArrayList<>(Arrays.asList(genreArray));
        List<String> updatedTags = new ArrayList<>(Arrays.asList(updatedTagsArray));



        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                tags,
                genres);

        List<String> originalTags = book.getTags();
        book.setTags(updatedTags);

        assertNotEquals(originalTags, updatedTags);
        assertEquals(updatedTags, book.getTags());
    }

    @Test
    public void testSetBookGenres() {
        System.out.println("\nTest set book genres");

        String[] tagsArray = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String[] genreArray = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        String[] updatedGenresArray = new String[]{"I have been ", "Changed"};
        List<String> tags = new ArrayList<>(Arrays.asList(tagsArray));
        List<String> genres = new ArrayList<>(Arrays.asList(genreArray));
        List<String> updatedGenres = new ArrayList<>(Arrays.asList(updatedGenresArray));

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                tags,
                genres);

        List<String> originalGenres = book.getGenres();
        book.setGenres(updatedGenres);

        assertNotEquals(originalGenres, updatedGenres);
        assertEquals(updatedGenres, book.getGenres());
    }

    @Test
    public void testBookEquals() {
        System.out.println("\nTest comparing book");

        String[] tagsArray = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String[] genreArray = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        String[] tagsArray2 = new String[]{"High School", "Literature"};
        String[] genreArray2 = new String[]{"Classics", "Fiction", "Historical Fiction", "Young Adult"};
        List<String> tags = new ArrayList<>(Arrays.asList(tagsArray));
        List<String> genres = new ArrayList<>(Arrays.asList(genreArray));
        List<String> tags2 = new ArrayList<>(Arrays.asList(tagsArray2));
        List<String> genres2 = new ArrayList<>(Arrays.asList(genreArray2));



        Book book1 = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                tags,
                genres);

        Book book2 = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                tags,
                genres);

        Book book3 = new Book(2 ,
                "To Kill a Mockingbird",
                "Harper Lee",
                "July 11, 1960",
                tags2,
                genres2);

        assertTrue(book1.equals(book2));
        assertFalse(book1.equals(book3));
    }

    @Test
    public void testBookToString() {
        System.out.println("\nTest printing book object");

        String[] tagsArray = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String[] genreArray = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        List<String> tags = new ArrayList<>(Arrays.asList(tagsArray));
        List<String> genres = new ArrayList<>(Arrays.asList(genreArray));

        Book book = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                tags,
                genres);


        String original = "Book{id = 1, name = 'Twilight', author = 'Stephenie Meyer', " +
                "date = 'October 5, 2005', tag(s) = [Young Adult, Twilight 1, Vampires], " +
                "genre(s) = [Urban Fantasy, Paranormal, Romance]}";
        String test = book.toString();

        assertEquals(original, test);
    }

}
