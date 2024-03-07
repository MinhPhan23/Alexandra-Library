package com.alexandria_library.tests.dso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.alexandria_library.dso.Booklist;

import org.junit.Before;
import org.junit.Test;

public class BookListTest {
    @Before
    public void setUp() {
        System.out.println("Starting unit tests for BookList");
    }

    @Test
    public void testCreateBookList() {
        System.out.println("\nTest creating a book list");

        Booklist booklist = new Booklist();

        assertNotNull(booklist);
        assertEquals("", booklist.getName());
        assertEquals("", booklist.getDesc());
    }

    @Test
    public void testSetBookListName() {
        System.out.println("\nTest set book list name");

        Booklist booklist = new Booklist();

        String originalName = booklist.getName();
        String updatedName = "I have been changed!";
        booklist.setName(updatedName);

        assertNotEquals(originalName, updatedName);
        assertEquals(updatedName, booklist.getName());
    }

    @Test
    public void testSetBookListDesc() {
        System.out.println("\nTest set book list description ");

        Booklist booklist = new Booklist();

        String originalDesc = booklist.getDesc();
        String updatedDesc = "I have new description!";
        booklist.setDesc(updatedDesc);

        assertNotEquals(originalDesc, updatedDesc);
        assertEquals(updatedDesc, booklist.getDesc());
    }

}
