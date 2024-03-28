package com.alexandria_library;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertThrows;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import com.alexandria_library.R;
import com.alexandria_library.presentation.Authentication.LoginActivity;

import junit.framework.AssertionFailedError;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LookUpBookTests {

    @Rule
    public ActivityTestRule<LoginActivity> testActivity = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp(){
        Intents.init();
    }

    @After
    public void tearDown(){
        Intents.release();
    }

    @Test
    public void LookUpBookTest() {
        //Credentials
        String username = "Tester";
        String password = "test";

        String bookName = "The Book Thief";
        String fakeBook = "Hello, wake up";

        //Account Registration
        onView(withId(R.id.user_mode_btn)).perform(click());
        onView(withId(R.id.register_btn)).perform(click());
        onView(withId(R.id.register_username_input)).perform(typeText(username));
        closeSoftKeyboard();
        onView(withId(R.id.register_password_input)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.register_confirm_password_input)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.Create_register_btn)).perform(click());

        //Log In
        onView(withId(R.id.user_mode_btn)).perform(click());
        onView(withId(R.id.login_userName_input)).perform(typeText(username));
        closeSoftKeyboard();
        onView(withId(R.id.login_password_input)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login_btn)).perform(click());

        //Search Real Book
        onView(withId(R.id.searchInput)).perform(typeText(bookName));
        closeSoftKeyboard();
        onView(withId(R.id.search_icon)).perform(click());
        onView(withId(R.id.search_book_title)).check(matches(withText(bookName)));

        //Search Fake Book
        onView(withId(R.id.searchInput)).perform(clearText());
        onView(withId(R.id.searchInput)).perform(typeText(fakeBook));
        closeSoftKeyboard();
        onView(withId(R.id.search_icon)).perform(click());

        //If the search prompt is invalid the search bar contents do not change
        //so the text should stay the same
        onView(withId(R.id.search_book_title)).check(matches(withText(bookName)));
    }
}
