package com.alexandria_library.tests.logic;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.Reader;
import com.alexandria_library.logic.CustomBooklist;
import com.alexandria_library.logic.Exception.BooklistException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomBooklistTest {
    private Reader reader;
    private CustomBooklist customBooklist;
    private Book book1, book2;

    private Booklist newList1, newList2;
    @Before
    public void setUp() {
        reader = new Reader("testReader", "testPass", 1);

        customBooklist = new CustomBooklist();

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

        newList1 = new Booklist();
        newList1.add(book1);

        newList2 = new Booklist();
        newList2.add(book2);

        assertNotNull(customBooklist);
    }

    @Test
    public void testAddNewBooklistByName() {
        try {
            String listName1 = "list1";
            customBooklist.addBooklist(reader, listName1);
            ArrayList<Booklist> list = reader.getAllCustomList();
            assertEquals(list.get(0).getName(), listName1);

            String listName2 = "list2";
            customBooklist.addBooklist(reader, listName2);
            list = reader.getAllCustomList();
            assertEquals(list.get(1).getName(), listName2);
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testAddNewBooklistByBooklist() {
        try {
            Booklist list1 = new Booklist();
            list1.setName("list1");
            customBooklist.addBooklist(reader, list1);
            ArrayList<Booklist> list = reader.getAllCustomList();
            assertEquals(list.get(0), list1);

            Booklist list2 = new Booklist();
            list2.setName("list2");
            customBooklist.addBooklist(reader, list2);
            list = reader.getAllCustomList();
            assertEquals(list.get(1), list2);
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testAddDuplicateListByName() {
        String listName = "list1";
        Exception exception = assertThrows(BooklistException.class, () -> {
            customBooklist.addBooklist(reader, listName);
            customBooklist.addBooklist(reader, listName);
        });

        String expectedMessage = "The list list1 already exist";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddDuplicateListByBooklist() {
        Booklist list1 = new Booklist();
        list1.setName("list1");
        Exception exception = assertThrows(BooklistException.class, () -> {
            customBooklist.addBooklist(reader, list1);
            customBooklist.addBooklist(reader, list1);
        });

        String expectedMessage = "The list list1 already exist";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddBookToExistingList() {
        Booklist list1 = new Booklist();
        list1.setName("list1");
        Booklist list2 = new Booklist();
        list1.setName("list2");

        try {
            customBooklist.addBooklist(reader, list1);
            customBooklist.addBooklist(reader, list2);
            customBooklist.addBookToCustom(reader, newList1, list1);
            customBooklist.addBookToCustom(reader, newList2, list2);

            assertTrue(list1.contains(book1));
            assertTrue(list2.contains(book2));
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testAddBookToNotExistingList() {
        Booklist list1 = new Booklist();
        list1.setName("list1");

        Exception exception = assertThrows(BooklistException.class, () -> customBooklist.addBookToCustom(reader, newList1, list1));

        String expectedMessage = "The list list1 does not exist";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAddDuplicateBook() {
        Booklist list1 = new Booklist();
        list1.setName("list1");

        Exception exception = assertThrows(BooklistException.class, () -> {
            customBooklist.addBooklist(reader, list1);
            customBooklist.addBookToCustom(reader, newList1, list1);
            customBooklist.addBookToCustom(reader, newList1, list1);
        });

        String expectedMessage = "The book(s) Twilight is already in list list1";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
