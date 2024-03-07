package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.data.hsqldb.UserPersistentHSQLDB;
import com.alexandria_library.data.stub.UserPersistentStub;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Authentication;
import com.alexandria_library.logic.AuthenticationException;
import com.alexandria_library.tests.util.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class AuthenticationITest {
    private Authentication authentication;
    private IUserPersistent data;
    private File tempDB;

    private final String name1 = "Thor";
    private final String pass1 = "123abc";
    private final String name2 = "Mika";
    private final String pass2 = "abcdef";
    @Before
    public void setUp() throws IOException {
        tempDB = TestUtils.copyDB();
        data = new UserPersistentHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        authentication = new Authentication(data);
    }

    @Test
    public void testNormalRegister() {
        System.out.println("Test register new user");
        try {
            authentication.register(name1, pass1, pass1);
            assertEquals( name1, data.findUser(name1).getUserName());
            assertEquals(pass1, data.findUser(name1).getPassword());
        }
        catch (Exception e){
            assert(false);
            System.out.println("Something wrong with the test");
            e.printStackTrace();
        }
    }

    @Test
    public void testRegisterExistNewUser() {
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
        System.out.println("Test register with non matching passwords");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register("Mika", pass1, pass2);
        });

        String expectedMessage = "Double password does not match";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNullUsernameRegister() {
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
        System.out.println("Test register with null password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register(name2, null, "1abc");
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEmptyPasswordRegister() {
        System.out.println("Test register with empty password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register(name2, "", "1abc");
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNull2PasswordRegister() {
        System.out.println("Test register with null double password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register(name2, "123abc", null);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEmpty2PasswordRegister() {
        System.out.println("Test register with empty double password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.register(name2, "123abc", "");
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testLoginSuccess() {
        System.out.println("Test finding existing user");
        try {
            User user = authentication.login(name1, pass1);
            assertNotNull(user);
            assertEquals(name1, user.getUserName());
            assertEquals(pass1, user.getPassword());
        }
        catch (Exception e) {
            assert(false);
            System.out.println("Something wrong with the test");
            e.printStackTrace();
        }
    }

    @Test
    public void testWrongPassword() {
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.login(name1, pass2);
        });
        String expectedMessage = "Password is not correct";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNotExistingUSer() {
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.login(name2, pass2);
        });
        String expectedMessage = "Username does not exist";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNullUsernameLogin() {
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
        System.out.println("Test register with null password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.login(name1, null);
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEmptyPasswordLogin() {
        System.out.println("Test register with empty password");
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authentication.login(name1, "");
        });

        String expectedMessage = "Username and Password cannot be empty";
        String actualMessage = exception.getMessage();

        assertNotNull(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testRegisterSecondNewUser() {
        System.out.println("Test register second new user");
        try {
            authentication.register(name2, pass2, pass2);
            assertEquals( name2, data.findUser(name2).getUserName());
            assertEquals(pass2, data.findUser(pass2).getPassword());
        }
        catch (Exception e){
            assert(false);
            System.out.println("Something wrong with the test");
            e.printStackTrace();
        }
    }

    @Test
    public void testExistingUserIntact() {
        try {
            User user = authentication.login(name1, pass1);
            assertNotNull(user);
            assertEquals(name1, user.getUserName());
            assertEquals(pass1, user.getPassword());

            user = authentication.login(name2, pass2);
            assertNotNull(user);
            assertEquals(name2, user.getUserName());
            assertEquals(pass2, user.getPassword());

            user = authentication.login("xiang", "123");
            assertNotNull(user);
            assertEquals("xiang", user.getUserName());
            assertEquals("123", user.getPassword());
        }
        catch (Exception e){
            assert(false);
            System.out.println("Something wrong with the test");
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        this.tempDB.delete();
    }
}