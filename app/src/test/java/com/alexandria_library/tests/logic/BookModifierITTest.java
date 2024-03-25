package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertTrue;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IUser;
import com.alexandria_library.dso.Librarian;
import com.alexandria_library.logic.BookListFilter;
import com.alexandria_library.logic.BookModifier;
import com.alexandria_library.logic.IBookModifier;
import com.alexandria_library.tests.util.TestUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BookModifierITTest {
    private IBookModifier bookModifier;
    private Booklist libraryBooks;
    private IUser librarian;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final IBookPersistent persistent = new BookPersistentHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        libraryBooks = persistent.getBookList();
        this.bookModifier = new BookModifier();
        this.librarian = new Librarian("xxxx", "123", 20);
    }

    @Test
    public void uploadNewBookTest(){
        String uniqueBookName = "UniqueBookName";
        String uniqueAuthorName = "UniqueAuthorName";
        String date = "2000-08-02";
        ArrayList<String> newTags = new ArrayList<>();
        ArrayList<String> newGenres = new ArrayList<>();
        newTags.add("newTag1");
        newTags.add("newTag2");
        newGenres.add("newGenre1");
        newGenres.add("newGenre2");

        boolean shouldBeTrue = bookModifier.uploadBook(librarian, uniqueBookName, uniqueAuthorName, date, newTags, newGenres);
        assertTrue(shouldBeTrue);
    }
}
