package com.alexandria_library.tests.logic;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.logic.SideBarService;
import com.alexandria_library.data.IBookPersistentIntermediate;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class SideBarServiceTest {
    @Mock
    private IBookPersistentIntermediate mockBookData;
    private User testUser;
    private SideBarService sideBarService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testUser = new User("userTest", "passwordTest");
        sideBarService = new SideBarService(testUser);
        assertNotNull(sideBarService);
        sideBarService = new SideBarService(testUser, mockBookData);
        assertNotNull(sideBarService);
    }
    @Test
    public void testGetUser() {
        assertEquals("should return correct user", testUser, sideBarService.getUser());
    }
    @Test
    public void testGetBookList() {
        Book book1 = new Book(1 , "The Seven Husbands of Evalyn Hugo", "Taylor Jenkins Reid", "June 13, 2017",
                new ArrayList<>(Arrays.asList("LGBT", "Adult")),
                new ArrayList<>(Arrays.asList("Romance", "Contemporary", "Historical Fiction")));
        Book book2 = new Book(4 , "The Book Thief", "Markus Zusak", "September 1, 2005",
                new ArrayList<>(Arrays.asList("World War II", "Holocaust", "Books About Books")),
                new ArrayList<>(Arrays.asList("War", "Classics", "Historical Fiction", "Young Adult")));
        Booklist mockBookList = new Booklist();
        mockBookList.add(book1);
        mockBookList.add(book2);
        when(mockBookData.getBookList()).thenReturn(mockBookList);

        ArrayList<Book> bookList = sideBarService.getBookList();
        assertNotNull("getBookList should not return null", bookList);
        assertEquals("getBookList should return the correct book list size", 2, bookList.size());
        assertEquals("The first book in the list should be Book1", "The Seven Husbands of Evalyn Hugo", bookList.get(0).getName());
        verify(mockBookData, times(1)).getBookList();
    }
}
