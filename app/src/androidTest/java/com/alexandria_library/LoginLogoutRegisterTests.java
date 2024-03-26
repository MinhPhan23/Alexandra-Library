package com.alexandria_library;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.espresso.intent.Intents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginLogoutRegisterTests {


    @Before
    public void setUp(){
        Intents.init();
    }

    @After
    public void tearDown(){

    }
}