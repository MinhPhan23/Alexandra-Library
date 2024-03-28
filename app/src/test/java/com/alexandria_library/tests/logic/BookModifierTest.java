package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.stub.BookPersistentInterStub;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IUser;
import com.alexandria_library.dso.Librarian;
import com.alexandria_library.dso.User;
import com.alexandria_library.dso.Book;
import com.alexandria_library.logic.BookModifier;
import com.alexandria_library.logic.IBookModifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BookModifierTest {
    private IBookModifier bookModifier;
    private IBookModifier bookModifierWithMock;
    private Booklist libraryBooks;
    private IUser librarian;
    private IUser fakeLibrarian;
    private IBookPersistent persistent;
    private IBookPersistent mockPersistent;

    @Before
    public void setUp() {
        persistent = new BookPersistentInterStub();
        mockPersistent = mock(BookPersistentInterStub.class);

        bookModifier = new BookModifier(persistent);
        bookModifierWithMock = new BookModifier(mockPersistent);
        libraryBooks = persistent.getBookList();

        this.librarian = mock(Librarian.class);
        this.fakeLibrarian = mock(User.class);
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

    @Test
    public void TestRemoveExistingBook(){
        // Stubbing behavior for libraryBooks mock
        Book bookToRemove = libraryBooks.get(0); // Create a book to remove
        Booklist listToRemove = new Booklist();
        listToRemove.add(bookToRemove);

        // Call the method under test
        bookModifierWithMock.deleteLibraryBook(bookToRemove, librarian);

        // Verify that the persistent deleteFromLibrary is used
        verify(mockPersistent).deleteLibraryBook(listToRemove, librarian);
    }

    @Test
    public void TestRemoveNonExistentBook(){
        Book bookToRemove = new Book(100, "fake", "fake", "fake", new ArrayList<>(), new ArrayList<>()); // Create a book to remove
        Booklist listToRemove = new Booklist();
        listToRemove.add(bookToRemove);

        // Call the method under test
        bookModifierWithMock.deleteLibraryBook(bookToRemove, librarian);

        // Verify that persistent deleteFromLibrary is used
        verify(mockPersistent).deleteLibraryBook(listToRemove, librarian);
    }

    @Test
    public void TestIncorrectCredentialsUpload(){
        String uniqueBookName = "UniqueBookName";
        String uniqueAuthorName = "UniqueAuthorName";
        String date = "2000-08-02";
        ArrayList<String> newTags = new ArrayList<>();
        ArrayList<String> newGenres = new ArrayList<>();
        newTags.add("newTag1");
        newGenres.add("newGenre1");

        // assert that the uploaded book with a fake librarian returns false
        boolean shouldBeFalse = bookModifierWithMock.uploadBook(fakeLibrarian, libraryBooks.size()+1, uniqueBookName, uniqueAuthorName, date, newTags, newGenres);
        assertFalse(shouldBeFalse);
    }

    @Test
    public void TestIncorrectCredentialsRemove(){
        Book bookToRemove = new Book(100,
                "fake name",
                "fake",
                "fake",
                new ArrayList<>(),
                new ArrayList<>()); // Create a book to remove

        // Call the method under test
        boolean shouldBeFalse = bookModifierWithMock.deleteLibraryBook(bookToRemove, fakeLibrarian);

        // assert that the deleted book with a fake librarian returns false
        assertFalse(shouldBeFalse);
    }
}
