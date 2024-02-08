package com.alexandria_library.tests.dso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.alexandria_library.dso.User;
import com.alexandria_library.dso.Book;

public class UserTest {
    private User user;
    private Book book1, book2;

    @Before
    public void setUp(){
        user = new User("testUser", "testPass");

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

        System.out.println("Starting unit tests for User");
    }
    @Test
    public void testNormalUser(){
        assertNotNull(user);
        assertEquals("testUser", user.getUserName());
        assertEquals("testPass", user.getPassword());
        assertEquals(0, user.getAllBookList().size());
        assertEquals(0, user.getFinishedList().size());
        assertEquals(0, user.getInProgressList().size());
    }
    @Test
    public void testEdgeUser(){
        User edgeUser;
        edgeUser = new User(" ", " ");
        assertNotNull(edgeUser);
        assertEquals(" ", edgeUser.getUserName());
        assertEquals(" ", edgeUser.getPassword());
        assertEquals(0, edgeUser.getAllBookList().size());
        assertEquals(0, edgeUser.getFinishedList().size());
        assertEquals(0, edgeUser.getInProgressList().size());
    }
    @Test
    public void testAddBookToAll(){
        assertTrue(user.addBookToAll(book1));
        assertEquals(1, user.getAllBookList().size());
        assertEquals(book1, user.getAllBookList().get(0));
    }
    @Test
    public void testAddBookToInProgress() {
        assertTrue(user.addBookToInProgress(book1));
        assertEquals(1, user.getInProgressList().size());
        assertEquals(book1, user.getInProgressList().get(0));
    }
    @Test
    public void testAddBookToFinished() {
        assertTrue(user.addBookToFinished(book1));
        assertEquals(1, user.getFinishedList().size());
        assertEquals(book1, user.getFinishedList().get(0));
    }
    @Test
    public void testMultipleBooks() {
        user.addBookToAll(book1);
        user.addBookToInProgress(book1);
        user.addBookToFinished(book2);

        assertEquals(1, user.getAllBookList().size());
        assertEquals(1, user.getInProgressList().size());
        assertEquals(1, user.getFinishedList().size());

        assertTrue(user.getAllBookList().contains(book1));
        assertTrue(user.getInProgressList().contains(book1));
        assertTrue(user.getFinishedList().contains(book2));
    }
}
