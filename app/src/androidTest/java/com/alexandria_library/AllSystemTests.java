package com.alexandria_library;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    AddDefaultListBookTests.class,
        BookFilterTests.class,
        LibrarianTest.class,
        LookUpBookTests.class,
        UserTest.class
})
public class AllSystemTests {
}
