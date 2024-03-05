package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.data.IUserPersistentStub;
import com.alexandria_library.data.stub.UserPersistentStub;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Authentication;
import com.alexandria_library.logic.AuthenticationException;

import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {
    private Authentication authentication;
    private IUserPersistentStub data;
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
            authentication.register("Thor", "123abc", "123abc");
            assertEquals( "Thor", data.findUser("Thor").getUserName());
            assertEquals("123abc", data.findUser("Thor").getPassword());
        }
        catch (Exception ignored){

        }
    }

    @Test
    public void testRegisterExistNewUser() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        System.out.println("Test register existing user");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register("Minh", "1fdsa", "1fdsa");
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
            authentication.register("Thor", "1fdsa", "1abc");
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
            authentication.register(null, "1fdsa", "1abc");
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
            authentication.register("", "1fdsa", "1abc");
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
            authentication.register("Thor", null, "1abc");
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
            authentication.register("Thor", "", "1abc");
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
            authentication.register("Thor", "123abc", null);
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
            authentication.register("Thor", "123abc", "");
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
            User user = authentication.login("Minh", "123456");
            assertNotNull(user);
            assertEquals("Minh", user.getUserName());
            assertEquals("123456", user.getPassword());
        }
        catch (Exception ignored) {

        }
    }

    @Test
    public void testWrongPassword() {
        data = new UserPersistentStub();
        authentication = new Authentication(data);
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.login("Minh", "123");
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
            authentication.login("Thor", "123");
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
            authentication.login(null, "1fdsa");
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
            authentication.login("", "1fdsa");
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
            authentication.login("Thor", null);
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
            authentication.login("Thor", "");
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
}