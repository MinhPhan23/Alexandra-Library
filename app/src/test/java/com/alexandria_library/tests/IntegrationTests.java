package com.alexandria_library.tests;

import com.alexandria_library.tests.logic.BookListFilterITTest;
import com.alexandria_library.tests.logic.AuthenticationITest;
import com.alexandria_library.tests.logic.BookModifierITTest;
import com.alexandria_library.tests.logic.SearchServiceITest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookListFilterITTest.class,
        SearchServiceITest.class,
        AuthenticationITest.class,
        BookModifierITTest.class
})
public class IntegrationTests {
}
