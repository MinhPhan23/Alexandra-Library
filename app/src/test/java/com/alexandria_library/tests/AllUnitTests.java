package com.alexandria_library.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


//import all test files
import com.alexandria_library.tests.data.BookPersistentIntermediateTest;
import com.alexandria_library.tests.data.UserPersistentStubTest;
import com.alexandria_library.tests.dso.BookListTest;
import com.alexandria_library.tests.dso.BookTest;
import com.alexandria_library.tests.dso.ReaderTest;
import com.alexandria_library.tests.logic.AuthenticationTest;
import com.alexandria_library.tests.logic.BookListFilterTest;
import com.alexandria_library.tests.logic.BookListRankerTest;
import com.alexandria_library.tests.logic.BookModifierTest;
import com.alexandria_library.tests.logic.DefaultBooklistTest;
import com.alexandria_library.tests.logic.SearchServiceTest;
import com.alexandria_library.tests.logic.SideBarServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SearchServiceTest.class,
        BookListRankerTest.class,
        BookListFilterTest.class,
        AuthenticationTest.class,
        SideBarServiceTest.class,
        BookListTest.class,
        BookTest.class,
        ReaderTest.class,
        BookPersistentIntermediateTest.class,
        UserPersistentStubTest.class,
        DefaultBooklistTest.class,
        BookModifierTest.class
})

public class AllUnitTests {
}