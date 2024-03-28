package com.alexandria_library;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import com.alexandria_library.presentation.Authentication.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LibrarianRegisterLoginLogoutTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp(){
        Intents.init();
    }

    @After
    public void tearDown(){
        Intents.release();
    }

    @Test
    public void LoginLogoutRegisterTest() {

        //Credentials
        String username = "Librarian";
        String password = "1234";

        //Account Registration
        onView(withId(R.id.register_btn)).perform(click());
        onView(withId(R.id.librarian_mode_btn)).perform(click());
        onView(withId(R.id.register_username_input)).perform(typeText(username));
        onView(withId(R.id.register_password_input)).perform(typeText(password));
        onView(withId(R.id.register_confirm_password_input)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.Create_register_btn)).perform(click());

        //Log In
        onView(withId(R.id.login_userName_input)).perform(typeText(username));
        onView(withId(R.id.login_password_input)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login_btn)).perform(click());

        //Check if the add button is shown
        onView(withId(R.id.librarian_add_btn));

        //Log Out
        onView(withId(R.id.account_btn)).perform(click());
        onView(withId(R.id.log_out_btn)).perform(click());
    }
}