package com.alexandria_library.tests.data;

import com.alexandria_library.data.stub.UserPersistentStub;
import com.alexandria_library.dso.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserPersistentStubTest {
    private UserPersistentStub userStub;
    @Before
    public void setUp(){
        userStub = new UserPersistentStub();
    }

    @Test
    public void testAddNewUser() {
        boolean result = userStub.addNewUser("testUser", "testPassword");
        assertTrue("User should be added successfully", result);
    }

    @Test
    public void testFindExistingUserByUserNameAndPassword() {
        String userName = "Xiang";
        String password = "123";
        User user = userStub.findUser(userName, password);
        assertNotNull("User should be found", user);
        assertEquals("User name should match", userName, user.getUserName());
    }

    @Test
    public void testFindExistingUserByUserName() {
        String userName = "Andrei";
        User user = userStub.findUser(userName);
        assertNotNull("User should be found", user);
        assertEquals("User name should match", userName, user.getUserName());
    }

    @Test
    public void testUserNotFound() {
        String userName = "NonExistentUser";
        User user = userStub.findUser(userName);
        assertNull("User should not be found", user);
    }
}
