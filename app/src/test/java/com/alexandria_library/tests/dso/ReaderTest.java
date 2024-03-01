package com.alexandria_library.tests.dso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.alexandria_library.dso.Reader;
import com.alexandria_library.dso.Book;

public class ReaderTest {
    private Reader reader;
    private Book book1, book2;

    @Before
    public void setUp(){
        reader = new Reader("testReader", "testPass");

        book1 = new Book(1 ,
                "Twilight",
                "Stephenie Meyer",
                "October 5, 2005",
                new String[]{"Young Adult", "Twilight 1", "Vampires"},
                new String[]{"Urban Fantasy", "Paranormal", "Romance"});

        book2 = new Book(5 ,
                "The Hunger Games",
                "Suzanne Collins",
                "September 14, 2008",
                new String[]{"Post Apocalyptic", "Survival", "Hunger Games 1"},
                new String[]{"Dystopia", "Science Fiction", "Fantasy", "Young Adult", "Action"});

        System.out.println("Starting unit tests for Reader");
    }
    @Test
    public void testNormalReader(){
        assertNotNull(reader);
        assertEquals("testUser", reader.getUserName());
        assertEquals("testPass", reader.getPassword());
        assertEquals(0, reader.getAllBooksList().size());
        assertEquals(0, reader.getFinishedList().size());
        assertEquals(0, reader.getInProgressList().size());
    }
    @Test
    public void testEdgeReader(){
        Reader edgeReader;
        edgeReader = new Reader(" ", " ");
        assertNotNull(edgeReader);
        assertEquals(" ", edgeReader.getUserName());
        assertEquals(" ", edgeReader.getPassword());
        assertEquals(0, edgeReader.getAllBooksList().size());
        assertEquals(0, edgeReader.getFinishedList().size());
        assertEquals(0, edgeReader.getInProgressList().size());
    }
    @Test
    public void testAddBookToAll(){
        assertTrue(reader.addBookToAll(book1));
        assertEquals(1, reader.getAllBooksList().size());
        assertEquals(book1, reader.getAllBooksList().get(0));
    }
    @Test
    public void testAddBookToInProgress() {
        assertTrue(reader.addBookToInProgress(book1));
        assertEquals(1, reader.getInProgressList().size());
        assertEquals(book1, reader.getInProgressList().get(0));
    }
    @Test
    public void testAddBookToFinished() {
        assertTrue(reader.addBookToFinished(book1));
        assertEquals(1, reader.getFinishedList().size());
        assertEquals(book1, reader.getFinishedList().get(0));
    }
    @Test
    public void testMultipleBooks() {
        reader.addBookToAll(book1);
        reader.addBookToInProgress(book1);
        reader.addBookToFinished(book2);

        assertEquals(1, reader.getAllBooksList().size());
        assertEquals(1, reader.getInProgressList().size());
        assertEquals(1, reader.getFinishedList().size());

        assertTrue(reader.getAllBooksList().contains(book1));
        assertTrue(reader.getInProgressList().contains(book1));
        assertTrue(reader.getFinishedList().contains(book2));
    }
}
