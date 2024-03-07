package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertNotNull;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Reader;
import com.alexandria_library.logic.CustomBooklist;
import com.alexandria_library.logic.DefaultBooklist;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultBooklistTest {
    private Reader reader;
    private DefaultBooklist defaultBooklist;
    private Book book1, book2;
    @Before
    public void setUp() {
        defaultBooklist = new DefaultBooklist();

        String[] tagsArray = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String[] genreArray = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        String[] tagsArray2 =  new String[]{"Post Apocalyptic", "Survival", "Hunger Games 1"};
        String[] genreArray2 = new String[]{"Dystopia", "Science Fiction", "Fantasy", "Young Adult", "Action"};
        List<String> tags = new ArrayList<>(Arrays.asList(tagsArray));
        List<String> genres = new ArrayList<>(Arrays.asList(genreArray));
        List<String> tags2 = new ArrayList<>(Arrays.asList(tagsArray2));
        List<String> genres2 = new ArrayList<>(Arrays.asList(genreArray2));

        book1 = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                tags,
                genres);

        book2 = new Book(5 ,
                "The Hunger Games",
                "Suzanne Collins",
                "September 14, 2008",
                tags2,
                genres2);

        assertNotNull(defaultBooklist);
    }

    @Test
    public void testAddBookToAll() {

    }

    @Test
    public void testAddDuplicateBookToAll() {

    }
}
