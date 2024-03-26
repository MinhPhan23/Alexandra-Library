package com.alexandria_library.tests.system;

import androidx.test.espresso.intent.Intents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class LogInLogOutRegisterTests {


    @Before
    public void setUp(){
        Intents.init();
    }

    @After
    public void tearDown(){

    }

    @Test
    public void test(){
        onView().perform();
    }
}
