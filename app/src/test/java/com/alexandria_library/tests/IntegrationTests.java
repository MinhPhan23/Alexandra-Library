package com.alexandria_library.tests;
import com.alexandria_library.tests.logic.BookListFilterITtest;
import com.alexandria_library.tests.logic.SearchServiceIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookListFilterITtest.class,
        SearchServiceIT.class
})
public class IntegrationTests {
}
