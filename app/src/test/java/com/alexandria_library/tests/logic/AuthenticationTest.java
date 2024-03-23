package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.data.stub.UserPersistentStub;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Authentication;
import com.alexandria_library.logic.Exception.AuthenticationException;

import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {
    private Authentication authentication;
    private IUserPersistent data;
    @Before
    public void setUp() {
        System.out.println("Starting Authentication test");
        authentication = new Authentication();
        assertNotNull(authentication);
        authentication = new Authentication(new UserPersistentStub());
        assertNotNull(authentication);
    }

    @Test
    public void testNormalRegister() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register new user");
        try {
            authentication.register("Thor", "123abc", "123abc", false);
            assertEquals( "Thor", data.findUser("Thor").getUserName());
            assertEquals("123abc", data.findUser("Thor").getPassword());
        }
        catch (Exception e){
            assert(false);
            System.out.println("Something wrong with the test");
            e.printStackTrace();
        }
    }

    @Test
    public void testRegisterExistNewUser() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register existing user");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register("Minh", "1fdsa", "1fdsa", false);
        });

        String expectedMessage = "Username already exist";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNotMatchingDoublePassword() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register with non matching passwords");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register("Thor", "1fdsa", "1abc", false);
        });

        String expectedMessage = "Double password does not match";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNullUsernameRegister() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register with null username");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register(null, "1fdsa", "1abc", false);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEmptyUsernameRegister() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register with empty username");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register("", "1fdsa", "1abc", false);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNullPasswordRegister() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register with null password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register("Thor", null, "1abc", false);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEmptyPasswordRegister() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register with empty password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register("Thor", "", "1abc", false);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNull2PasswordRegister() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register with null double password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register("Thor", "123abc", null, false);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEmpty2PasswordRegister() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register with empty double password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register("Thor", "123abc", "", false);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testLoginSuccess() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test finding existing user");
        try {
            User user = authentication.login("Minh", "123456", false);
            assertNotNull(user);
            assertEquals("Minh", user.getUserName());
            assertEquals("123456", user.getPassword());
        }
        catch (Exception e) {
            assert(false);
            System.out.println("Something wrong with the test");
            e.printStackTrace();
        }
    }

    @Test
    public void testWrongPassword() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.login("Minh", "123", false);
        });
        String expectedMessage = "Password is not correct";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNotExistingUSer() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.login("Thor", "123", false);
        });
        String expectedMessage = "Username does not exist";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNullUsernameLogin() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test login with null username");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.login(null, "1fdsa", false);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEmptyUsernameLogin() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register with empty username");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.login("", "1fdsa", false);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNullPasswordLogin() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register with null password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.login("Thor", null, false);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEmptyPasswordLogin() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register with empty password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.login("Thor", "", false);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
}