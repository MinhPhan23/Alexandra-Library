package com.alexandria_library;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertThrows;

import android.text.Layout;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.alexandria_library.presentation.Authentication.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LibrarianTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

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

        //Switch to Librarian mode
        onView(withId(R.id.librarian_mode_btn)).perform(click());

        //Log In Fail
        onView(withId(R.id.login_userName_input)).perform(typeText(username));
        onView(withId(R.id.login_password_input)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login_btn)).perform(click());

        //Account Registration
        onView(withId(R.id.register_btn)).perform(click());
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
        onView(withId(R.id.librarian_add_btn)).check(matches(isDisplayed()));;

        //Log Out
        onView(withId(R.id.account_btn)).perform(click());
        onView(withId(R.id.log_out_btn)).perform(click());

        //Check get back to log in page
        onView(withId(R.id.login_btn)).check(matches(isDisplayed()));
    }

    @Test
    public void addBookTest() {
        //Credentials
        String username = "Minh";
        String password = "123";

        //Switch to Librarian mode
        onView(withId(R.id.librarian_mode_btn)).perform(click());

        //Book info
        String name = "Hamlet";
        String author = "William Shakespeare";
        String tags = "Literature,Revenge,Play";
        String genres = "Tragedy,Drama";
        String date = "July 26, 1602";

        //Log In
        onView(withId(R.id.login_userName_input)).perform(typeText(username));
        onView(withId(R.id.login_password_input)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login_btn)).perform(click());

        //Add the book
        onView(withId(R.id.librarian_add_btn)).perform(click());
        onView(withId(R.id.add_book_name)).perform(typeText(name));
        onView(withId(R.id.add_book_author)).perform(typeText(author));
        onView(withId(R.id.add_book_tags)).perform(typeText(tags));
        onView(withId(R.id.add_book_genres)).perform(typeText(genres));
        onView(withId(R.id.add_book_date)).perform(typeText(date));
        closeSoftKeyboard();
        onView(withId(R.id.add_book_create_btn)).perform(click());
        onView(withId(R.id.add_book_cancel_btn)).perform(click());

        //Find the book
        onView(withId(R.id.library_btn)).perform(click());
        onView(withText("Hamlet")).check(matches(isDisplayed()));

        //Log Out
        onView(withId(R.id.account_btn)).perform(click());
        onView(withId(R.id.log_out_btn)).perform(click());

        //Log In
        onView(withId(R.id.login_userName_input)).perform(typeText(username));
        onView(withId(R.id.login_password_input)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login_btn)).perform(click());

        //Find the book
        onView(withId(R.id.library_btn)).perform(click());
        onView(withText("Hamlet")).check(matches(isDisplayed()));

        //Log Out
        onView(withId(R.id.account_btn)).perform(click());
        onView(withId(R.id.log_out_btn)).perform(click());

        //Log In as Reader
        onView(withId(R.id.user_mode_btn)).perform(click());
        onView(withId(R.id.login_userName_input)).perform(typeText("xiang"));
        onView(withId(R.id.login_password_input)).perform(typeText("123"));
        closeSoftKeyboard();
        onView(withId(R.id.login_btn)).perform(click());

        //Find the book
        onView(withId(R.id.library_btn)).perform(click());
        onView(withText("Hamlet")).check(matches(isDisplayed()));
    }
}