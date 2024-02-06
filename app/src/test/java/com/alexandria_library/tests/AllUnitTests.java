package com.alexandria_library.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


//import all test files
import com.alexandria_library.tests.logic.SearchServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SearchServiceTest.class
})

public class AllUnitTests {

}