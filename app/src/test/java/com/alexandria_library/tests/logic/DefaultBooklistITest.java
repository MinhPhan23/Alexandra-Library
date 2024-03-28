package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.data.hsqldb.UserPersistentHSQLDB;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.Reader;
import com.alexandria_library.logic.DefaultBooklist;
import com.alexandria_library.logic.Exception.BooklistException;
import com.alexandria_library.tests.util.TestUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class DefaultBooklistITest {
    private Reader reader;
    private IUserPersistent userData;
    private Book book1, book2, book3;
    private Booklist newList;
    private DefaultBooklist defaultBooklist;

    @Before
    public void setUp() throws IOException {
        File tempDB = TestUtils.copyDB();
        IBookPersistent bookData = new BookPersistentHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        userData = new UserPersistentHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));

        defaultBooklist = new DefaultBooklist(userData);

        userData.addNewUser("testUser", "testPassword");
        reader = (Reader) userData.findUser("testUser");

        book1 = bookData.getEachBooks("The Seven Husbands of Evalyn Hugo");
        book2 = bookData.getEachBooks("To Kill a Mockingbird");
        book3 = bookData.getEachBooks("The Fault in Our Stars");
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
                assertTrue(cloneAllList.get(i).equals(allList.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    public void testAddDuplicateBookToAll() {
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
    public void testAddDuplicateBookToInProgress() {
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
            Booklist cloneReaderFinishedList = cloneReader.getFinishedList();
            for (int i = 0; i < finishedList.size(); i++) {
                Book b1 = finishedList.get(i);
                Book b2 = cloneReaderFinishedList.get(i);
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

    @Test
    public void testRemoveBookFromAll() {
        try {
            newList.add(book1);
            newList.add(book2);
            defaultBooklist.addBookToAll(reader, newList);

            Booklist allList = reader.getAllBooksList();
            assertEquals(2, allList.size());

            newList.remove(0);
            defaultBooklist.removeBookFromAll(reader, newList);

            allList = reader.getAllBooksList();
            assertEquals(1, allList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneAllList = cloneReader.getAllBooksList();
            for (int i = 0; i < allList.size(); i++) {
                Book b1 = allList.get(i);
                Book b2 = cloneAllList.get(i);
                assertTrue(b1.equals(b2));
            }

            newList.remove(0);
            newList.add(book1);
            defaultBooklist.removeBookFromAll(reader, newList);

            allList = reader.getAllBooksList();
            assertEquals(0, allList.size());

            cloneReader = (Reader) userData.findUser("testUser");
            cloneAllList = cloneReader.getAllBooksList();
            assertEquals(0, cloneAllList.size());
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    public void testRemoveNonExistingBookFromAll() {
        try {
            newList.add(book1);
            defaultBooklist.addBookToAll(reader, newList);

            newList.remove(0);
            newList.add(book2);

            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.removeBookFromAll(reader, newList);
            });

            String expectedMessage = "The book(s) To Kill a Mockingbird is not in list All";
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
    public void testRemoveMultipleNonExistingBookFromAll() {
        try {
            newList.add(book1);
            defaultBooklist.addBookToAll(reader, newList);

            newList.add(book2);
            newList.add(book3);
            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.removeBookFromAll(reader, newList);
            });

            String expectedMessage = "The book(s) To Kill a Mockingbird, The Fault in Our Stars is not in list All";
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
    public void testRemoveBookFromInProgress() {
        try {
            newList.add(book1);
            newList.add(book2);
            defaultBooklist.addBookToInProgress(reader, newList);

            Booklist inProgressList = reader.getInProgressList();
            assertEquals(2, inProgressList.size());

            newList.remove(0);
            defaultBooklist.removeBookFromInProgress(reader, newList);

            inProgressList = reader.getInProgressList();
            assertEquals(1, inProgressList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneReaderInProgressList = cloneReader.getInProgressList();
            for (int i = 0; i < inProgressList.size(); i++) {
                Book b1 = inProgressList.get(i);
                Book b2 = cloneReaderInProgressList.get(i);
                assertTrue(b1.equals(b2));
            }

            newList.remove(0);
            newList.add(book1);
            defaultBooklist.removeBookFromInProgress(reader, newList);

            inProgressList = reader.getAllBooksList();
            assertEquals(0, inProgressList.size());

            cloneReader = (Reader) userData.findUser("testUser");
            cloneReaderInProgressList = cloneReader.getInProgressList();
            assertEquals(0, cloneReaderInProgressList.size());
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    public void testRemoveNonExistingBookFromInProgress() {
        try {
            newList.add(book1);
            defaultBooklist.addBookToInProgress(reader, newList);

            newList.remove(0);
            newList.add(book2);

            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.removeBookFromInProgress(reader, newList);
            });

            String expectedMessage = "The book(s) To Kill a Mockingbird is not in list In Progress";
            String actualMessage = exception.getMessage();

            assertNotNull(actualMessage);
            assertEquals(expectedMessage, actualMessage);

            Booklist inProgressList = reader.getInProgressList();
            assertEquals(1, inProgressList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneInProgressList = cloneReader.getInProgressList();
            for (int i = 0; i < inProgressList.size(); i++) {
                Book b1 = inProgressList.get(i);
                Book b2 = cloneInProgressList.get(i);
                assertTrue(b1.equals(b2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testRemoveMultipleNonExistingBookFromInProgress() {
        try {
            newList.add(book1);
            defaultBooklist.addBookToInProgress(reader, newList);

            newList.add(book2);
            newList.add(book3);
            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.removeBookFromInProgress(reader, newList);
            });

            String expectedMessage = "The book(s) To Kill a Mockingbird, The Fault in Our Stars is not in list In Progress";
            String actualMessage = exception.getMessage();

            assertNotNull(actualMessage);
            assertEquals(expectedMessage, actualMessage);

            Booklist inProgressList = reader.getInProgressList();
            assertEquals(1, inProgressList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneInProgressList = cloneReader.getInProgressList();
            for (int i = 0; i < inProgressList.size(); i++) {
                Book b1 = inProgressList.get(i);
                Book b2 = cloneInProgressList.get(i);
                assertTrue(b1.equals(b2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testRemoveBookFromFinished() {
        try {
            newList.add(book1);
            newList.add(book2);
            defaultBooklist.addBookToFinished(reader, newList);

            Booklist finishedList = reader.getFinishedList();
            assertEquals(2, finishedList.size());

            newList.remove(0);
            defaultBooklist.removeBookFromFinished(reader, newList);

            finishedList = reader.getFinishedList();
            assertEquals(1, finishedList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneFinishedList = cloneReader.getFinishedList();
            for (int i = 0; i < finishedList.size(); i++) {
                Book b1 = finishedList.get(i);
                Book b2 = cloneFinishedList.get(i);
                assertTrue(b1.equals(b2));
            }

            newList.remove(0);
            newList.add(book1);
            defaultBooklist.removeBookFromFinished(reader, newList);

            finishedList = reader.getFinishedList();
            assertEquals(0, finishedList.size());

            cloneReader = (Reader) userData.findUser("testUser");
            cloneFinishedList = cloneReader.getFinishedList();
            assertEquals(0, cloneFinishedList.size());
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    public void testRemoveNonExistingBookFromFinished() {
        try {
            newList.add(book1);
            defaultBooklist.addBookToFinished(reader, newList);

            newList.remove(0);
            newList.add(book2);

            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.removeBookFromFinished(reader, newList);
            });

            String expectedMessage = "The book(s) To Kill a Mockingbird is not in list Finished";
            String actualMessage = exception.getMessage();

            assertNotNull(actualMessage);
            assertEquals(expectedMessage, actualMessage);

            Booklist finishedList = reader.getFinishedList();
            assertEquals(1, finishedList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneFinishedList = cloneReader.getFinishedList();
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

    @Test
    public void testRemoveMultipleNonExistingBookFromFinished() {
        try {
            newList.add(book1);
            defaultBooklist.addBookToFinished(reader, newList);

            newList.add(book2);
            newList.add(book3);
            Exception exception = assertThrows(BooklistException.class, () -> {
                defaultBooklist.removeBookFromFinished(reader, newList);
            });

            String expectedMessage = "The book(s) To Kill a Mockingbird, The Fault in Our Stars is not in list Finished";
            String actualMessage = exception.getMessage();

            assertNotNull(actualMessage);
            assertEquals(expectedMessage, actualMessage);

            Booklist finishedList = reader.getFinishedList();
            assertEquals(1, finishedList.size());

            Reader cloneReader = (Reader) userData.findUser("testUser");
            Booklist cloneFinishedList = cloneReader.getFinishedList();
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
