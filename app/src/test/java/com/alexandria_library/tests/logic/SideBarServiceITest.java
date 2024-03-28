package com.alexandria_library.tests.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.data.hsqldb.UserPersistentHSQLDB;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IUser;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Authentication;
import com.alexandria_library.logic.BookListRanker;
import com.alexandria_library.logic.SideBarService;
import com.alexandria_library.tests.util.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;

import java.io.File;
import java.io.IOException;

public class SideBarServiceITest {
    private SideBarService sideBarService;
    private IBookPersistent data;
    private File tempDB;

    private final String name1 = "Thor";
    private final String pass1 = "123abc";
    private final String name2 = "Mika";
    private final String pass2 = "abcdef";
    private IUser user;
    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        user = new User(name1, pass1, 6);
        data = new BookPersistentHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        sideBarService = new SideBarService(user, data);
        assertNotNull(sideBarService);
    }

    @Test
    public void testDefaultConstructor(){
        sideBarService = null;
        sideBarService = new SideBarService(user);
        assertNotNull(sideBarService);
    }

    @Test
    public void testGetUser(){
        IUser getter = sideBarService.getUser();
        assertNotNull(getter);
        boolean equals = getter.getUserName().equals(user.getUserName())
                && getter.getPassword().equals(user.getPassword());
        assertTrue(equals);
    }

    @Test
    public void testGetUser2(){
        IUser diffUser = new User(name2, pass2, 8);
        IUser getter = sideBarService.getUser();
        boolean equals = getter.getUserName().equals(diffUser.getUserName())
                && getter.getPassword().equals(diffUser.getPassword());
        assertFalse(equals);
    }

    @Test
    public void testGetBookList(){
        Booklist getter = sideBarService.getBookList();
        Booklist persistenceGetter = data.getBookList();
        assertTrue(getter.size() == persistenceGetter.size());

        for(int i = 0; i<getter.size(); i++){
            assertTrue(getter.get(i).equals(persistenceGetter.get(i)));
        }
    }

    @After
    public void tearDown() throws IOException {
        if (tempDB != null && tempDB.exists()) {
            tempDB.delete();
        }
    }
}
