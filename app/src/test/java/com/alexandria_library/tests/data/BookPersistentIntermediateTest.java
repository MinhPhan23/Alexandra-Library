package com.alexandria_library.tests.data;

import com.alexandria_library.data.stub.BookPersistentInterStub;
import com.alexandria_library.dso.Book;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookPersistentIntermediateTest {

    BookPersistentInterStub database;
    Book book;

    @Before
    public void setUp(){
        System.out.println("Starting Unit Tests for BookPersistentIntermediate");
        database = new BookPersistentInterStub();
        String[] tags = {"c", "d", "e"};
        String[] genres = {"f", "g", "h"};
        book = new Book(0, "a", "b", "0000-00-00", tags, genres);
        assertNotNull(database);
        assertNotNull(book);
    }

    @Test
    public void BookDeleteTest(){
        System.out.println("Testing delete(Book)");
        int sizeBefore;
        int sizeAfter;
        database.upload(book, null);
        sizeBefore = database.getBookList().size();
        database.delete(book, null);
        sizeAfter = database.getBookList().size();
        Book testBook = database.search(book);
        assertEquals(sizeBefore, sizeAfter + 1);
        assertNull(testBook);
    }

    @Test
    public void ListDeleteTest(){
        System.out.println("Testing delete(ArrayList)");
        int sizeBefore;
        int sizeAfter;
        sizeBefore = database.getBookList().size();
        Book book1 = book;
        Book book2 = book;
        Book book3 = book;
        book1.setID(1);
        book2.setID(2);
        book3.setID(3);
        ArrayList<Book> testList = new ArrayList<Book>();
        testList.add(book1);
        testList.add(book2);
        testList.add(book3);
        database.delete(testList, null);
        sizeAfter = database.getBookList().size();
        testList = database.search(testList);
        assertEquals(sizeBefore, sizeAfter + testList.size());
        assertNull(testList);
    }

    @Test
    public void checkBookTest(){
        System.out.println("Testing checkBook()");
        int status;

        book.setName("");
        status = database.checkBook(book);
        assertEquals(status, 1);
        book.setName(null);
        status = database.checkBook(book);
        assertEquals(status, 1);
        book.setName("a");
        status = database.checkBook(book);
        assertEquals(status, 0);

        book.setAuthor("");
        status = database.checkBook(book);
        assertEquals(status, 2);
        book.setAuthor(null);
        status = database.checkBook(book);
        assertEquals(status, 2);
        book.setAuthor("b");
        status = database.checkBook(book);
        assertEquals(status, 0);

        book.setTags(null);
        status = database.checkBook(book);
        assertEquals(status, 2);
        String[] tags = {"c", "d", "e"};
        book.setTags(tags);
        status = database.checkBook(book);
        assertEquals(status, 0);

        book.setGenres(null);
        status = database.checkBook(book);
        assertEquals(status, 2);
        String[] genres = {"f", "g", "h"};
        book.setTags(genres);
        status = database.checkBook(book);
        assertEquals(status, 0);

        book.setDate(null);
        status = database.checkBook(book);
        assertEquals(status, 2);
        String date = "0000-00-00";
        book.setDate(date);
        status = database.checkBook(book);
        assertEquals(status, 0);
    }

    @Test
    public void checkListTest(){
        System.out.println("Testing checkList()");
        int status;
        Book book1 = book;
        Book book2 = book;
        Book book3 = book;
        Book book4 = book;
        book1.setID(1);
        book2.setID(2);
        book3.setID(3);
        book4.setID(4);
        ArrayList<Book> testList = new ArrayList<Book>();
        testList.add(book1);
        testList.add(book2);
        testList.add(book3);

        book4.setName("");
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(status, 1);
        testList.remove(3);
        book4 = book;
        book4.setID(4);

        book4.setName(null);
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(status, 1);
        testList.remove(3);
        book4 = book;
        book4.setID(4);

        book4.setAuthor("");
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(status, 2);
        testList.remove(3);
        book4 = book;
        book4.setID(4);

        book4.setAuthor(null);
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(status, 2);
        testList.remove(3);
        book4 = book;
        book4.setID(4);

        book4.setTags(null);
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(status, 3);
        testList.remove(3);
        book4 = book;
        book4.setID(4);

        book4.setGenres(null);
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(status, 4);
        testList.remove(3);
        book4 = book;
        book4.setID(4);

        book4.setDate(null);
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(status, 5);
        testList.remove(3);
        book4 = book;
        book4.setID(4);
    }

    @Test
    public void checkCredentialsTest(){
        System.out.println("Testing checkCredentials(User)");
        assertEquals(database.checkCredentials(null), 0);
    }
    @Test
    public void uploadTest(){
        System.out.println("Testing upload(Book, User)");
        int status;
        Book testBook;
        int sizeBefore = database.getBookList().size();
        testBook = database.search(book);
        assertNull(testBook);
        database.upload(book, null);
        int sizeAfter = database.getBookList().size();
        testBook = database.search(book);
        assertNotNull(testBook);
        assertEquals(sizeBefore, sizeAfter - 1);
        database.delete(book, null);
    }
    @Test
    public void updateTest(){
        System.out.println("Testing update(Book, User)");
        Book testBook = book;
        int status;
        testBook.setName("");
        status = database.update(testBook, null);
        assertEquals(status, -1);
        testBook.setName("a");
        status = database.update(testBook, null);
        assertEquals(status, 1);
        database.upload(testBook, null);
        testBook.setName("T");
        testBook.setAuthor("E");
        String[] tags = {"S"};
        testBook.setTags(tags);
        String[] genres = {"T"};
        testBook.setGenres(genres);
        status = database.update(testBook, null);
        assertEquals(status, 0);
        Book newBook = database.search(testBook);
        assertEquals(newBook.getName(), testBook.getName());
        assertEquals(newBook.getAuthor(), testBook.getAuthor());
        assertArrayEquals(newBook.getTags(), testBook.getTags());
        assertArrayEquals(newBook.getGenres(), testBook.getGenres());
        database.delete(newBook, null);
    }
    @Test
    public void searchTagTest(){
        System.out.println("Testing searchTag(String[])");
        String[] tags = null;
        ArrayList<Book> testList;
        int status;
        testList = database.searchTag(tags);
        assertEquals(testList.size(), 0);

        tags = new String[]{"c", "d", "e"};
        testList = database.searchTag(tags);
        assertEquals(testList.size(), 0);

        status = database.upload(book, null);
        assertEquals(status, 0);
        testList = database.searchTag(tags);
        assertEquals(testList.size(), 1);
        database.delete(book, null);
    }
    @Test
    public void searchGenreTest(){
        System.out.println("Testing searchGenre(String[])");
        String[] genres = null;
        ArrayList<Book> testList;
        int status;
        testList = database.searchGenre(genres);
        assertEquals(testList.size(), 0);

        genres = new String[]{"f", "g", "h"};
        testList = database.searchTag(genres);
        assertEquals(testList.size(), 0);

        status = database.upload(book, null);
        assertEquals(status, 0);
        testList = database.searchGenre(genres);
        assertEquals(testList.size(), 1);
        database.delete(book, null);
    }
    @Test
    public void searchAuthorTest(){
        System.out.println("Testing searchAuthor(String)");
        String author = null;
        ArrayList<Book> testList;
        int status;
        testList = database.searchAuthor(author);
        assertEquals(testList.size(), 0);

        author = "b";
        testList = database.searchAuthor(author);
        assertEquals(testList.size(), 0);

        status = database.upload(book, null);
        assertEquals(status, 0);
        testList = database.searchAuthor(author);
        assertEquals(testList.size(), 1);
        database.delete(book, null);
    }
    @Test
    public void searchNameTest(){
        System.out.println("Testing searchName(String)");
        String name = null;
        ArrayList<Book> testList;
        int status;
        testList = database.searchName(name);
        assertEquals(testList.size(), 0);

        name = "a";
        testList = database.searchName(name);
        assertEquals(testList.size(), 0);

        status = database.upload(book, null);
        assertEquals(status, 0);
        testList = database.searchName(name);
        assertEquals(testList.size(), 1);
        database.delete(book, null);
    }
    @Test
    public void searchBookTest(){
        System.out.println("Testing Book search(Book)");
        int status;
        Book testBook;
        testBook = database.search((Book)null);
        assertNull(testBook);

        testBook = database.search(book);
        assertNull(testBook);

        status = database.upload(book, null);
        assertEquals(status, 0);
        testBook = database.search(book);
        assertEquals(testBook.getName(), book.getName());
        assertEquals(testBook.getAuthor(), book.getAuthor());
        assertArrayEquals(testBook.getTags(), book.getTags());
        assertArrayEquals(testBook.getGenres(), book.getGenres());
        assertEquals(testBook.getDate(), book.getDate());
        assertEquals(testBook.getID(), book.getID());
        database.delete(book, null);
    }

    @Test
    private void searchArrayListTest(){
        System.out.println("Testing ArrayList search(ArrayList)");
        ArrayList<Book> testList;
        ArrayList<Book> searchList;
        Book book1 = book;
        Book book2 = book;
        Book book3 = book;
        book1.setID(1);
        book2.setID(2);
        book3.setID(3);
        searchList = new ArrayList<Book>();
        searchList.add(book1);
        searchList.add(book2);
        searchList.add(book3);

        testList = database.search((ArrayList<Book>) null);
        assertEquals(testList.size(), 0);

        testList = database.search(searchList);
        assertEquals(testList.size(), 0);

        database.upload(book1, null);
        database.upload(book2, null);
        database.upload(book3, null);
        testList = database.search(searchList);
        assertEquals(testList.size(), 3);
    }
    @Test
    public void searchGeneralTest(){
        System.out.println("Testing ArrayList search(PreparedStatement)");
        ArrayList<Book> testList;
        testList = database.search((PreparedStatement) null);
        assertNull(testList);
    }
    @Test
    public void addBookTest(){
        System.out.println("Testing addBook(ArrayList, Book)");
        int status;
        Book testBook;
        int sizeBefore = database.getBookList().size();
        testBook = database.search(book);
        assertNull(testBook);
        database.upload(book, null);
        database.upload(book, null);
        database.upload(book, null);
        int sizeAfter = database.getBookList().size();
        testBook = database.search(book);
        assertNotNull(testBook);
        assertEquals(sizeBefore, sizeAfter - 1);
        database.delete(book, null);
    }
    @Test
    public void similarStringArraysTest(){
        System.out.println("Testing similarStringArrays(String[], String[])");
        boolean similar;
        String[] array1 = {"a", "b", "c"};
        String[] array2 = {"a", "c", "b", "d", "e", "x"};
        String[] array3 = {"a"};
        String[] array4 = {"x"};
        similar = database.similarStringArrays(null, null);
        assert(!similar);
        similar = database.similarStringArrays(array1, array2);
        assert(similar);
        similar = database.similarStringArrays(array1, array3);
        assert(similar);
        similar = database.similarStringArrays(array1, array4);
        assert(!similar);
        similar = database.similarStringArrays(array1, null);
        assert(!similar);
        similar = database.similarStringArrays(array2, array4);
        assert(similar);
    }
    @Test
    public void getBookListTest(){
        System.out.println("Testing getBookList()");
        ArrayList<Book> testListBefore;
        ArrayList<Book> testListAfter;
        int sizeBefore;
        int sizeAfter;
        Book testBook;
        int statusBefore;
        int statusAfter;
        Book book1 = book;
        Book book2 = book;
        Book book3 = book;
        book1.setID(1);
        book2.setID(2);
        book3.setID(3);

        sizeBefore = database.getBookList().size();
        testListBefore = database.getBookList();
        testBook = database.search(book1);
        assertNull(testBook);
        testBook = database.search(book2);
        assertNull(testBook);
        testBook = database.search(book3);
        assertNull(testBook);

        database.upload(book1, null);
        database.upload(book2, null);
        database.upload(book3, null);
        sizeAfter = database.getBookList().size();
        testListAfter = database.getBookList();
        assertEquals(sizeBefore, sizeAfter - 3);

        testBook = database.search(book1);
        assertNotNull(testBook);
        testBook = database.search(book2);
        assertNotNull(testBook);
        testBook = database.search(book3);
        assertNotNull(testBook);
    }
}
