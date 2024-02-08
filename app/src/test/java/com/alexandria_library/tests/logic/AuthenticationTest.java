package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Authentication;

import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {
    private Authentication authentication;
    @Before
    public void setUp() {
        System.out.println("Starting Authentication test");
        authentication = new Authentication();
        assertNotNull(authentication);
    }

    @Test
    public void testInsertNonExistNewUser() {
        System.out.println("Test insert new user");
        assertTrue(authentication.insertNewUser("", "123"));
    }

    @Test
    public void testInsertExistNewUser() {
        System.out.println("Test insert existing user");
        assertTrue(authentication.insertNewUser("", "123"));
        assertFalse(authentication.insertNewUser("", "123"));
        assertFalse(authentication.insertNewUser("Minh", "fdsa"));
    }

    @Test
    public void testFindExist() {
        System.out.println("Test finding existing user");
        User user = authentication.findExist("Minh", "123456");
        assertNotNull(user);
        assertEquals("Minh", user.getUserName());
        assertEquals("123456", user.getPassword());
    }

    @Test
    public void testFindExistFail() {
        System.out.println("Test finding existing user with wrong password");
        User user = authentication.findExist("Minh", "123");
        assertNull(user);
    }
}
