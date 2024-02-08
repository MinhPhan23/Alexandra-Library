package com.alexandria_library.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


//import all test files
import com.alexandria_library.tests.logic.AuthenticationTest;
import com.alexandria_library.tests.logic.InfoOrganizerTest;
import com.alexandria_library.tests.logic.SearchServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SearchServiceTest.class,
        InfoOrganizerTest.class,
        AuthenticationTest.class,

})

public class AllUnitTests {

}