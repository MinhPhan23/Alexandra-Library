package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.data.hsqldb.UserPersistentHSQLDB;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.Reader;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.DefaultBooklist;
import com.alexandria_library.logic.Exception.BooklistException;
import com.alexandria_library.tests.util.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultBooklistITest {
    private Reader reader;
    private IUserPersistent userData;
    private IBookPersistent bookData;
    private Book book1, book2;
    private Booklist newList;
    private DefaultBooklist defaultBooklist;

    @Before
    public void setUp() throws IOException {
        File tempDB = TestUtils.copyDB();
        bookData = new BookPersistentHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        userData = new UserPersistentHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));

        defaultBooklist = new DefaultBooklist(userData);

        userData.addNewUser("testUser", "testPassword");
        reader = (Reader) userData.findUser("testUser");

        book1 = bookData.getEachBooks("The Seven Husbands of Evalyn Hugo");
        book2 = bookData.getEachBooks("To Kill a Mockingbird");
        newList = new Booklist();

        assertNotNull(defaultBooklist);
    }

    @Test
    public void testAddBookToAll() {
        try {
            newList.add(book1);
            newList.add(book2);
            defaultBooklist.addBookToAll(reader, newList);

            Booklist allList = reader.getAllBooksList();
            assertEquals(2, allList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneAllList = cloneReader.getAllBooksList();
            for (int i = 0; i < allList.size(); i++) {
                Book b1 = allList.get(i);
                Book b2 = cloneAllList.get(i);
                assertTrue(b1.equals(b2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    public void testAddDuplicateBooktoAll() {
        try {
            newList.add(book1);
            defaultBooklist.addBookToAll(reader, newList);

            newList.add(book2);

            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.addBookToAll(reader, newList);
            });

            String expectedMessage = "The book(s) The Seven Husbands of Evalyn Hugo is already in list All";
            String actualMessage = exception.getMessage();

            assertNotNull(actualMessage);
            assertEquals(expectedMessage, actualMessage);

            Booklist allList = reader.getAllBooksList();
            assertEquals(1, allList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneAllList = cloneReader.getAllBooksList();
            for (int i = 0; i < allList.size(); i++) {
                Book b1 = allList.get(i);
                Book b2 = cloneAllList.get(i);
                assertTrue(b1.equals(b2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testAddMultipleDuplicateBookToAll() {
        try {
            newList.add(book1);
            newList.add(book2);
            defaultBooklist.addBookToAll(reader, newList);

            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.addBookToAll(reader, newList);
            });

            String expectedMessage = "The book(s) The Seven Husbands of Evalyn Hugo, To Kill a Mockingbird is already in list All";
            String actualMessage = exception.getMessage();

            assertNotNull(actualMessage);
            assertEquals(expectedMessage, actualMessage);

            Booklist allList = reader.getAllBooksList();
            assertEquals(2, allList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneAllList = cloneReader.getAllBooksList();
            for (int i = 0; i < allList.size(); i++) {
                Book b1 = allList.get(i);
                Book b2 = cloneAllList.get(i);
                assertTrue(b1.equals(b2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testAddBookToInProgress() {
        try {
            newList.add(book1);
            newList.add(book2);
            defaultBooklist.addBookToInProgress(reader, newList);

            Booklist inProgressList = reader.getInProgressList();
            assertEquals(2, inProgressList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneReaderInProgressList = cloneReader.getInProgressList();
            for (int i = 0; i < inProgressList.size(); i++) {
                Book b1 = inProgressList.get(i);
                Book b2 = cloneReaderInProgressList.get(i);
                assertTrue(b1.equals(b2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    public void testAddDuplicateBooktoInProgress() {
        try {
            newList.add(book1);
            defaultBooklist.addBookToInProgress(reader, newList);

            newList.add(book2);

            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.addBookToInProgress(reader, newList);
            });

            String expectedMessage = "The book(s) The Seven Husbands of Evalyn Hugo is already in list In Progress";
            String actualMessage = exception.getMessage();

            assertNotNull(actualMessage);
            assertEquals(expectedMessage, actualMessage);

            Booklist inProgressList = reader.getInProgressList();
            assertEquals(1, inProgressList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneReaderInProgressList = cloneReader.getInProgressList();
            for (int i = 0; i < inProgressList.size(); i++) {
                Book b1 = inProgressList.get(i);
                Book b2 = cloneReaderInProgressList.get(i);
                assertTrue(b1.equals(b2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testAddMultipleDuplicateBookToInProgress() {
        try {
            newList.add(book1);
            newList.add(book2);
            defaultBooklist.addBookToInProgress(reader, newList);

            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.addBookToInProgress(reader, newList);
            });

            String expectedMessage = "The book(s) The Seven Husbands of Evalyn Hugo, To Kill a Mockingbird is already in list In Progress";
            String actualMessage = exception.getMessage();

            assertNotNull(actualMessage);
            assertEquals(expectedMessage, actualMessage);

            Booklist inProgressList = reader.getInProgressList();
            assertEquals(2, inProgressList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneReaderInProgressList = cloneReader.getInProgressList();
            for (int i = 0; i < inProgressList.size(); i++) {
                Book b1 = inProgressList.get(i);
                Book b2 = cloneReaderInProgressList.get(i);
                assertTrue(b1.equals(b2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testAddBookToFinished() {
        try {
            newList.add(book1);
            newList.add(book2);
            defaultBooklist.addBookToFinished(reader, newList);

            Booklist finishedList = reader.getFinishedList();
            assertEquals(2, finishedList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneReaderfinishedList = cloneReader.getFinishedList();
            for (int i = 0; i < finishedList.size(); i++) {
                Book b1 = finishedList.get(i);
                Book b2 = cloneReaderfinishedList.get(i);
                assertTrue(b1.equals(b2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    public void testAddDuplicateBookToFinished() {
        try {
            newList.add(book1);
            defaultBooklist.addBookToFinished(reader, newList);

            newList.add(book2);

            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.addBookToFinished(reader, newList);
            });

            String expectedMessage = "The book(s) The Seven Husbands of Evalyn Hugo is already in list Finished";
            String actualMessage = exception.getMessage();

            assertNotNull(actualMessage);
            assertEquals(expectedMessage, actualMessage);

            Booklist finishedList = reader.getFinishedList();
            assertEquals(1, finishedList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneReaderFinishedList = cloneReader.getFinishedList();
            for (int i = 0; i < finishedList.size(); i++) {
                Book b1 = finishedList.get(i);
                Book b2 = cloneReaderFinishedList.get(i);
                assertTrue(b1.equals(b2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testAddMultipleDuplicateBookToFinished() {
        try {
            newList.add(book1);
            newList.add(book2);
            defaultBooklist.addBookToFinished(reader, newList);

            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.addBookToFinished(reader, newList);
            });

            String expectedMessage = "The book(s) The Seven Husbands of Evalyn Hugo, To Kill a Mockingbird is already in list Finished";
            String actualMessage = exception.getMessage();

            assertNotNull(actualMessage);
            assertEquals(expectedMessage, actualMessage);

            Booklist finishedList = reader.getFinishedList();
            assertEquals(2, finishedList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneReaderFinishedList = cloneReader.getFinishedList();
            for (int i = 0; i < finishedList.size(); i++) {
                Book b1 = finishedList.get(i);
                Book b2 = cloneReaderFinishedList.get(i);
                assertTrue(b1.equals(b2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testAllListTogether() {
        try {
            newList.add(book1);
            defaultBooklist.addBookToAll(reader, newList);

            Booklist allList = reader.getAllBooksList();
            assertEquals(1, allList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneAllList = cloneReader.getAllBooksList();
            Booklist cloneInProgressList = cloneReader.getInProgressList();
            Booklist cloneFinishedList = cloneReader.getFinishedList();
            assertEquals(1, cloneAllList.size());
            assertEquals(0, cloneInProgressList.size());
            assertEquals(0, cloneFinishedList.size());

            newList.add(book2);

            defaultBooklist.addBookToInProgress(cloneReader, newList);
            defaultBooklist.addBookToFinished(cloneReader, newList);

            allList = cloneReader.getAllBooksList();
            Booklist inProgressList = cloneReader.getInProgressList();
            Booklist finishedList = cloneReader.getFinishedList();
            assertEquals(1, allList.size());
            assertEquals(2, inProgressList.size());
            assertEquals(2, finishedList.size());

            cloneReader = (Reader) userData.findUser("testUser");
            cloneAllList = cloneReader.getAllBooksList();
            cloneInProgressList = cloneReader.getInProgressList();
            cloneFinishedList = cloneReader.getFinishedList();

            for (int i = 0; i < allList.size(); i++) {
                Book b1 = allList.get(i);
                Book b2 = cloneAllList.get(i);
                assertTrue(b1.equals(b2));
            }

            for (int i = 0; i < inProgressList.size(); i++) {
                Book b1 = inProgressList.get(i);
                Book b2 = cloneInProgressList.get(i);
                assertTrue(b1.equals(b2));
            }

            for (int i = 0; i < finishedList.size(); i++) {
                Book b1 = finishedList.get(i);
                Book b2 = cloneFinishedList.get(i);
                assertTrue(b1.equals(b2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }
}
