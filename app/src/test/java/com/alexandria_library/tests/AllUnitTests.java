package com.alexandria_library.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


//import all test files
import com.alexandria_library.tests.data.BookPersistentIntermediateTest;
import com.alexandria_library.tests.data.UserPersistentStubTest;
import com.alexandria_library.tests.dso.BookTest;
import com.alexandria_library.tests.dso.UserTest;
import com.alexandria_library.tests.logic.AuthenticationTest;
import com.alexandria_library.tests.logic.InfoOrganizerTest;
import com.alexandria_library.tests.logic.SearchServiceTest;
import com.alexandria_library.tests.logic.SideBarServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SearchServiceTest.class,
        InfoOrganizerTest.class,
        AuthenticationTest.class,
        SideBarServiceTest.class,
        BookTest.class,
        UserTest.class,
        BookPersistentIntermediateTest.class,
        UserPersistentStubTest.class
})

public class AllUnitTests {
}