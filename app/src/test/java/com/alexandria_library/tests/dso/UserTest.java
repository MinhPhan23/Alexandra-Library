package com.alexandria_library.tests.dso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.alexandria_library.dso.User;

public class UserTest {
    private User normalUser;
    private User edgeUser;
    @Test
    public void testNormalUser(){
        normalUser = new User("Xiang Shi", "1234567890");
        assertNotNull(normalUser);
        assertEquals("Xiang Shi", normalUser.getUserName());
        assertEquals("1234567890", normalUser.getPassword());
        assertEquals(0, normalUser.getAllBookList().size());
        assertEquals(0, normalUser.getFinishedList().size());
        assertEquals(0, normalUser.getInProgressList().size());
    }
    @Test
    public void testEdgeUser(){
        edgeUser = new User(" ", " ");
        assertNotNull(edgeUser);
        assertEquals(" ", edgeUser.getUserName());
        assertEquals(" ", edgeUser.getPassword());
        assertEquals(0, edgeUser.getAllBookList().size());
        assertEquals(0, edgeUser.getFinishedList().size());
        assertEquals(0, edgeUser.getInProgressList().size());
    }
}
