package com.alexandria_library.tests;

import com.alexandria_library.tests.logic.BookListFilterITTest;
import com.alexandria_library.tests.logic.AuthenticationITest;
import com.alexandria_library.tests.logic.BookModifierITTest;
import com.alexandria_library.tests.logic.DefaultBooklistITest;
import com.alexandria_library.tests.logic.SearchServiceITest;
import com.alexandria_library.tests.logic.SideBarServiceITest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookListFilterITTest.class,
        SearchServiceITest.class,
        BookModifierITTest.class,
        DefaultBooklistITest.class,
        AuthenticationITest.class,
        SideBarServiceITest.class
})

public class IntegrationTests {
}
