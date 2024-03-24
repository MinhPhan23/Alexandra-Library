package com.alexandria_library.tests.data;

import com.alexandria_library.data.stub.BookPersistentInterStub;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.Librarian;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookPersistentIntermediateTest {

    BookPersistentInterStub database;
    Book book;

    Librarian librarian;
    List<String> defaultTags = new ArrayList<>();
    List<String>defaultGenres = new ArrayList<>();

    @Before
    public void setUp(){
        System.out.println("Starting Unit Tests for BookPersistentIntermediate");
        database = new BookPersistentInterStub();
        defaultTags.add("c");
        defaultTags.add("d");
        defaultTags.add("e");
        defaultGenres.add("f");
        defaultGenres.add("g");
        defaultGenres.add("h");
        book = new Book(0, "a", "b", "0000-00-00", defaultTags, defaultGenres);
        librarian = new Librarian("test", "test", 0);
        assertNotNull(database);
        assertNotNull(librarian);
        assertNotNull(book);
        assertNotNull(defaultTags);
        assertNotNull(defaultGenres);
    }

    @Test
    public void BookDeleteTest(){
        System.out.println("Testing delete(Book)");
        int sizeBefore;
        int sizeAfter;
        database.upload(book, librarian);
        sizeBefore = database.getBookList().size();
        database.delete(book, librarian);
        sizeAfter = database.getBookList().size();
        Book testBook = database.search(book);
        assertEquals(sizeAfter + 1, sizeBefore);
        assertNull(testBook);
    }

    @Test
    public void ListDeleteTest(){
        System.out.println("Testing delete(ArrayList)");
        int sizeBefore;
        int sizeAfter;
        Booklist testList1;
        Booklist testList2;
        sizeBefore = database.getBookList().size();
        Book book1 = new Book(1,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book2 = new Book(2,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book3 = new Book(3,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        book1.setID(1);
        book2.setID(2);
        book3.setID(3);
        testList1 = new Booklist();
        testList1.add(book1);
        testList1.add(book2);
        testList1.add(book3);
        database.delete(testList1, librarian);
        sizeAfter = database.getBookList().size();
        testList2 = database.search(testList1);
        assertEquals(sizeAfter + testList1.size(), sizeBefore);
        assertEquals(0, testList2.size());
    }

    @Test
    public void checkBookTest1(){
        System.out.println("Testing checkBook() 1");
        int status;

        book.setName("");
        status = database.checkBook(book);
        assertEquals(1, status);
        book.setName(null);
        status = database.checkBook(book);
        assertEquals(1, status);
        book.setName("a");
        status = database.checkBook(book);
        assertEquals(0, status);
    }

    @Test
    public void checkBookTest2() {
        System.out.println("Testing checkBook() 2");
        int status;
        book.setAuthor("");
        status = database.checkBook(book);
        assertEquals(2, status);
        book.setAuthor(null);
        status = database.checkBook(book);
        assertEquals(2, status);
        book.setAuthor("b");
        status = database.checkBook(book);
        assertEquals(0, status);
    }

    @Test
    public void checkBookTest3() {
        System.out.println("Testing checkBook() 3");
        int status;
        book.setTags(null);
        status = database.checkBook(book);
        assertEquals(3, status);
        book.setTags(defaultTags);
        status = database.checkBook(book);
        assertEquals(0, status);
    }

    @Test
    public void checkBookTest4() {
        System.out.println("Testing checkBook() 4");
        int status;
        book.setGenres(null);
        status = database.checkBook(book);
        assertEquals(4, status);
        book.setGenres(defaultGenres);
        status = database.checkBook(book);
        assertEquals(0, status);
    }

    @Test
    public void checkBookTest5() {
        System.out.println("Testing checkBook() 5");
        int status;
        book.setDate(null);
        status = database.checkBook(book);
        assertEquals(5, status);
        String date = "0000-00-00";
        book.setDate(date);
        status = database.checkBook(book);
        assertEquals(0, status);
    }

    @Test
    public void checkListTest1(){
        System.out.println("Testing checkList()");
        int status;
        Book book1 = new Book(1,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book2 = new Book(2,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book3 = new Book(3,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book4 = new Book(4,"a", "b", "0000-00-00", defaultTags, defaultGenres);

        Booklist testList;

        testList = new Booklist();
        testList.add(0, book1);
        testList.add(1, book2);
        testList.add(2, book3);

        book4.setName("");
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(1, status);
        testList.remove(3);
        book4 = book;
        book4.setID(4);

        book4.setName(null);
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(1, status);
        testList.remove(3);
        book4.setName("a");
        book4.setID(4);

    }

    @Test
    public void checkListTest2() {
        System.out.println("Testing checkList()");
        int status;
        Book book1 = new Book(1,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book2 = new Book(2,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book3 = new Book(3,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book4 = new Book(4,"a", "b", "0000-00-00", defaultTags, defaultGenres);

        Booklist testList;

        testList = new Booklist();
        testList.add(0, book1);
        testList.add(1, book2);
        testList.add(2, book3);

        book4.setAuthor("");
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(2, status);
        testList.remove(3);
        book4 = book;
        book4.setID(4);

        book4.setAuthor(null);
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(2, status);
        testList.remove(3);
        book4 = book;
        book4.setAuthor("b");
        book4.setID(4);
    }

    @Test
    public void checkListTest3() {
        System.out.println("Testing checkList()");
        int status;
        Book book1 = new Book(1,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book2 = new Book(2,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book3 = new Book(3,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book4 = new Book(4,"a", "b", "0000-00-00", defaultTags, defaultGenres);

        Booklist testList;

        testList = new Booklist();
        testList.add(0, book1);
        testList.add(1, book2);
        testList.add(2, book3);

        book4.setTags(null);
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(3, status);
        testList.remove(3);
        book4 = book;
        book4.setTags(defaultTags);
        book4.setID(4);
    }

    @Test
    public void checkListTest4() {
        System.out.println("Testing checkList()");
        int status;
        Book book1 = new Book(1,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book2 = new Book(2,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book3 = new Book(3,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book4 = new Book(4,"a", "b", "0000-00-00", defaultTags, defaultGenres);

        Booklist testList;

        testList = new Booklist();
        testList.add(0, book1);
        testList.add(1, book2);
        testList.add(2, book3);

        book4.setGenres(null);
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(4, status);
        testList.remove(3);
        book4 = book;
        book4.setGenres(defaultGenres);
        book4.setID(4);
    }

    @Test
    public void checkListTest5() {
        System.out.println("Testing checkList()");
        int status;
        Book book1 = new Book(1,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book2 = new Book(2,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book3 = new Book(3,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book4 = new Book(4,"a", "b", "0000-00-00", defaultTags, defaultGenres);

        Booklist testList;

        testList = new Booklist();
        testList.add(0, book1);
        testList.add(1, book2);
        testList.add(2, book3);

        book4.setDate(null);
        testList.add(book4);
        status = database.checkList(testList);
        assertEquals(5, status);
        testList.remove(3);
        book4 = book;
        book4.setID(4);
    }
    @Test
    public void uploadTest(){
        System.out.println("Testing upload(Book, User)");
        int sizeBefore;
        int sizeAfter;
        Book testBook;
        sizeBefore = database.getBookList().size();
        testBook = database.search(book);
        assertNull(testBook);
        database.upload(book, librarian);
        sizeAfter = database.getBookList().size();
        testBook = database.search(book);
        assertNotNull(testBook);
        assertEquals(sizeAfter - 1, sizeBefore);
        database.delete(book, librarian);
    }
    @Test
    public void updateTest(){
        System.out.println("Testing update(Book, User)");
        Book testBook = book;
        int status;
        testBook.setName("");
        status = database.update(testBook, librarian);
        assertEquals(-1, status);

        testBook.setName("a");
        status = database.update(testBook, librarian);
        assertEquals(1, status);

        database.upload(testBook, librarian);
        testBook.setName("T");
        testBook.setAuthor("E");
        List<String>tags = new ArrayList<>();
        tags.add("S");
        testBook.setTags(tags);
        List<String>genres = new ArrayList<>();
        genres.add("T");
        testBook.setGenres(genres);
        status = database.update(testBook, librarian);
        assertEquals(0, status);

        Book newBook = database.search(testBook);
        assertEquals(testBook.getName(), newBook.getName());
        assertEquals(testBook.getAuthor(), newBook.getAuthor());
        assertEquals(testBook.getTags(), newBook.getTags());
        assertEquals(testBook.getGenres(), newBook.getGenres());
        database.delete(newBook, librarian);
    }
    @Test
    public void searchTagTest(){
        System.out.println("Testing searchTag(String[])");
        boolean status;
        String[] tagsArray = null;
        Booklist testList;

        List<String>tags = new ArrayList<>();
        testList = database.searchTag(tags);
        assertEquals(0, testList.size());

        tagsArray = new String[]{"c", "d", "e"};
        tags = new ArrayList<>(Arrays.asList(tagsArray));
        testList = database.searchTag(tags);
        assertEquals(0, testList.size());

        status = database.upload(book, librarian);
        assertTrue(status);
        testList = database.searchTag(tags);
        assertEquals(1, testList.size());
        database.delete(book, librarian);
    }
    @Test
    public void searchGenreTest(){
        System.out.println("Testing searchGenre(String[])");
        boolean status;
        String[] genresArray = null;
        Booklist testList;

        List<String>genres = new ArrayList<>();
        testList = database.searchGenre(genres);
        assertEquals(0, testList.size());

        genresArray = new String[]{"f", "g", "h"};
        genres = new ArrayList<>(Arrays.asList(genresArray));
        testList = database.searchTag(genres);
        assertEquals(0, testList.size());

        status = database.upload(book, librarian);
        assertTrue(status);
        testList = database.searchGenre(genres);
        assertEquals(1, testList.size());
        database.delete(book, librarian);
    }
    @Test
    public void searchAuthorTest(){
        System.out.println("Testing searchAuthor(String)");
        boolean status;
        String author = null;
        Booklist testList;

        testList = database.searchAuthor(author);
        assertEquals(0, testList.size());

        author = "b";
        testList = database.searchAuthor(author);
        assertEquals(0, testList.size());

        status = database.upload(book, librarian);
        assertTrue(status);
        testList = database.searchAuthor(author);
        assertEquals(1, testList.size());
        database.delete(book, librarian);
    }
    @Test
    public void searchNameTest(){
        System.out.println("Testing searchName(String)");
        boolean status;
        String name = null;
        Booklist testList;

        testList = database.searchName(name);
        assertEquals(0, testList.size());

        name = "a";
        testList = database.searchName(name);
        assertEquals(0, testList.size());

        status = database.upload(book, librarian);
        assertTrue(status);
        testList = database.searchName(name);
        assertEquals(1, testList.size());
        database.delete(book, librarian);
    }
    @Test
    public void searchBookTest(){
        System.out.println("Testing Book search(Book)");
        boolean status;
        Book testBook;
        testBook = database.search((Book)null);
        assertNull(testBook);

        testBook = database.search(book);
        assertNull(testBook);

        status = database.upload(book, librarian);
        assertTrue(status);

        testBook = database.search(book);
        assertEquals(book.getName(), testBook.getName());
        assertEquals(book.getAuthor(), testBook.getAuthor());
        assertEquals(book.getTags(), testBook.getTags());
        assertEquals(book.getGenres(), testBook.getGenres());
        assertEquals(book.getDate(), testBook.getDate());
        assertEquals(book.getID(), testBook.getID());
        database.delete(book, librarian);
    }

    @Test
    public void searchArrayListTest(){
        System.out.println("Testing ArrayList search(ArrayList)");
        Booklist testList;
        Booklist searchList;
        Book book1 = new Book(1,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book2 = new Book(2,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        Book book3 = new Book(3,"a", "b", "0000-00-00", defaultTags, defaultGenres);
        book1.setID(1);
        book2.setID(2);
        book3.setID(3);
        searchList = new Booklist();
        searchList.add(book1);
        searchList.add(book2);
        searchList.add(book3);

        testList = database.search((Booklist) null);
        assertEquals(0, testList.size());

        testList = database.search(searchList);
        assertEquals(3, testList.size());

        database.upload(book1, librarian);
        database.upload(book2, librarian);
        database.upload(book3, librarian);
        testList = database.search(searchList);
        assertEquals(3, testList.size());
    }
    @Test
    public void searchSQLTest(){
        System.out.println("Testing ArrayList search(PreparedStatement)");
        Booklist testList;
        testList = database.search((PreparedStatement) null);
        assertNull(testList);
    }
    @Test
    public void addBookTest(){
        System.out.println("Testing addBook(ArrayList, Book)");
        int sizeBefore;
        int sizeAfter;
        Book testBook;

        sizeBefore = database.getBookList().size();
        testBook = database.search(book);
        assertNull(testBook);

        database.upload(book, librarian);
        database.upload(book, librarian);
        database.upload(book, librarian);
        sizeAfter = database.getBookList().size();
        testBook = database.search(book);
        assertNotNull(testBook);

        assertEquals(sizeAfter - 1, sizeBefore);
        database.delete(book, librarian);
    }
    @Test
    public void similarStringArraysTest1(){
        System.out.println("Testing similarStringArrays(String[], String[]) 1");
        boolean similar;
        similar = database.similarStringArrays(null, null);
        assert(!similar);
    }

    @Test
    public void similarStringArraysTest2(){
        System.out.println("Testing similarStringArrays(String[], String[]) 2");
        boolean similar;
        String[] array1 = {"a", "b", "c"};
        List<String>one = new ArrayList<>(Arrays.asList(array1));
        String[] array2 = {"a", "c", "b", "d", "e", "x"};
        List<String>two = new ArrayList<>(Arrays.asList(array2));

        similar = database.similarStringArrays(one, two);
        assert(similar);
    }

    @Test
    public void similarStringArraysTest3(){
        System.out.println("Testing similarStringArrays(String[], String[]) 3");
        boolean similar;
        String[] array1 = {"a", "b", "c"};
        List<String>one = new ArrayList<>(Arrays.asList(array1));
        String[] array2 = {"a"};
        List<String>two = new ArrayList<>(Arrays.asList(array2));

        similar = database.similarStringArrays(one, two);
        assert(similar);
    }

    @Test
    public void similarStringArraysTest4(){
        System.out.println("Testing similarStringArrays(String[], String[]) 4");
        boolean similar;
        String[] array1 = {"a", "b", "c"};
        List<String>one = new ArrayList<>(Arrays.asList(array1));
        String[] array2 = {"x"};
        List<String>two = new ArrayList<>(Arrays.asList(array2));

        similar = database.similarStringArrays(one, two);
        assert(!similar);
    }

    @Test
    public void similarStringArraysTest5(){
        System.out.println("Testing similarStringArrays(String[], String[]) 5");
        boolean similar;
        String[] array1 = {"a", "b", "c"};
        List<String>one = new ArrayList<>(Arrays.asList(array1));

        similar = database.similarStringArrays(one, null);
        assert(!similar);
    }

    @Test
    public void similarStringArraysTest6(){
        System.out.println("Testing similarStringArrays(String[], String[]) 6");
        boolean similar;
        String[] array2 = {"a", "c", "b", "d", "e", "x"};
        List<String>two = new ArrayList<>(Arrays.asList(array2));
        String[] array4 = {"x"};
        List<String>four = new ArrayList<>(Arrays.asList(array4));

        similar = database.similarStringArrays(two, four);
        assert(similar);
    }


    @Test
    public void getBookListTest(){
        System.out.println("Testing getBookList()");
        int sizeBefore;
        int sizeAfter;
        Book testBook;
        Book book1 = new Book(31,"a", "b1", "0000-00-00", defaultTags, defaultGenres);
        Book book2 = new Book(32,"a", "b2", "0000-00-00", defaultTags, defaultGenres);
        Book book3 = new Book(33,"a", "b3", "0000-00-00", defaultTags, defaultGenres);

        sizeBefore = database.getBookList().size();
        testBook = database.search(book1);
        assertNull(testBook);
        testBook = database.search(book2);
        assertNull(testBook);
        testBook = database.search(book3);
        assertNull(testBook);

        database.upload(book1, librarian);
        database.upload(book2, librarian);
        database.upload(book3, librarian);
        sizeAfter = database.getBookList().size();
        assertEquals(sizeBefore + 3, sizeAfter);

        testBook = database.search(book1);
        assertNotNull(testBook);
        testBook = database.search(book2);
        assertNotNull(testBook);
        testBook = database.search(book3);
        assertNotNull(testBook);
        database.delete(book1, librarian);
        database.delete(book2, librarian);
        database.delete(book3, librarian);
    }
}
