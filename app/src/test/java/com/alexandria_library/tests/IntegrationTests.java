package com.alexandria_library.tests;

import com.alexandria_library.tests.logic.BookListFilterITest;
import com.alexandria_library.tests.logic.AuthenticationITest;
import com.alexandria_library.tests.logic.SearchServiceITest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookListFilterITest.class,
        SearchServiceITest.class,
        AuthenticationITest.class
})
public class IntegrationTests {
}
