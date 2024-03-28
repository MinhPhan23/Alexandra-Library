package com.alexandria_library.acceptanceTest;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.AllOf.allOf;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import com.alexandria_library.R;
import com.alexandria_library.presentation.Authentication.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BookFilterTests {

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
    public void FilterBookTest() {
        //Credentials
        String username = "Carlo";
        String password = "123";

        String selectedTag = "Love";
        String selectedGenre = "Contemporary";

        String expectedBook = "The Fault in Our Stars";

        //Log In
        onView(withId(R.id.user_mode_btn)).perform(click());
        onView(withId(R.id.login_userName_input)).perform(typeText(username));
        closeSoftKeyboard();
        onView(withId(R.id.login_password_input)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login_btn)).perform(click());

        //Select filter criteria
        onView(withId(R.id.filter_open_btn)).perform(click());
        onView(withText(selectedTag)).perform(click());
        onView(withText(selectedGenre)).perform(click());
        onView(withId(R.id.filter_button)).perform(click());

        //Check for correct book
        onView(withId(R.id.filter_book_title)).check(matches(withText(expectedBook)));

    }

    @Test
    public void EmptyFilterTest() {
        //Credentials
        String username = "Carlo";
        String password = "123";

        //Log In
        onView(withId(R.id.user_mode_btn)).perform(click());
        onView(withId(R.id.login_userName_input)).perform(typeText(username));
        closeSoftKeyboard();
        onView(withId(R.id.login_password_input)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login_btn)).perform(click());

        //Open filter panel with no selected filters
        onView(withId(R.id.filter_open_btn)).perform(click());
        onView(withId(R.id.filter_button)).perform(click());

        //Check that there are 5 elements
        onView(allOf(withId(R.id.filter_book_title),
                withText("The Fault in Our Stars"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.filter_book_title),
                withText("The Book Thief"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.filter_book_title),
                withText("The Hunger Games"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.filter_book_title),
                withText("The Seven Husbands of Evalyn Hugo"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.filter_book_title),
                withText("To Kill a Mockingbird"))).check(matches(isDisplayed()));

    }

}


