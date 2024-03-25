package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertTrue;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.stub.BookPersistentInterStub;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IUser;
import com.alexandria_library.dso.Librarian;
import com.alexandria_library.logic.BookListFilter;
import com.alexandria_library.logic.BookModifier;
import com.alexandria_library.logic.IBookModifier;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BookModifierTest {
    private IBookModifier bookModifier;
    private Booklist libraryBooks;
    private IUser librarian;
    private IBookPersistent persistent;

    @Before
    public void setUp() {
        persistent = new BookPersistentInterStub();
        bookModifier = new BookModifier(persistent);
        libraryBooks = persistent.getBookList();
        this.librarian = new Librarian("xxxx", "123", 20);
    }

    @Test
    public void TestAddNewBookWithOneTagAndGenre(){
        String uniqueBookName = "UniqueBookName";
        String uniqueAuthorName = "UniqueAuthorName";
        String date = "2000-08-02";
        ArrayList<String> newTags = new ArrayList<>();
        ArrayList<String> newGenres = new ArrayList<>();
        newTags.add("newTag1");
        newGenres.add("newGenre1");

        boolean shouldBeTrue = bookModifier.uploadBook(librarian, libraryBooks.size()+1, uniqueBookName, uniqueAuthorName, date, newTags, newGenres);
        assertTrue(shouldBeTrue);
    }

    @Test
    public void TestAddNewBookWithTwoTagsAndGenres(){
        String name = "Two tags and two genres";
        String author = "Xiang";
        String date = "2024-06-05";
        ArrayList<String> newTags = new ArrayList<>();
        ArrayList<String> newGenres = new ArrayList<>();
        newTags.add("Tag1");
        newTags.add("Tage2");
        newGenres.add("Genres2");
        newGenres.add("Genres1");
        boolean shouldBeTrue = bookModifier.uploadBook(librarian, libraryBooks.size()+1, name, author, date, newTags, newGenres);
        assertTrue(shouldBeTrue);
    }

    @Test
    public void TestAddNewBookWithThreeTagsAndGenres(){
        String name = "3 tags and 3 genres";
        String author = "Xiang";
        String date = "2024-06-05";
        ArrayList<String> newTags = new ArrayList<>();
        ArrayList<String> newGenres = new ArrayList<>();
        newTags.add("Tag1");
        newTags.add("Tage2");
        newTags.add("Tag3");
        newGenres.add("Genres1");
        newGenres.add("Genres2");
        newGenres.add("Genres3");
        boolean shouldBeTrue = bookModifier.uploadBook(librarian, libraryBooks.size()+1, name, author, date, newTags, newGenres);
        assertTrue(shouldBeTrue);
    }
}
