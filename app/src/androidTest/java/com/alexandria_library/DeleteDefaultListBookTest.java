package com.alexandria_library;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertThrows;

import androidx.test.espresso.AmbiguousViewMatcherException;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.alexandria_library.presentation.Authentication.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DeleteDefaultListBookTest {

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
    public void DeleteDefaultListBookTest() {
        try{
            //Credentials
            String username = "Minh";
            String password = "123";

            String book1 = "The Seven Husbands of Evalyn Hugo";
            String book2 = "The Hunger Games";
            String book3 = "The Book Thief";
            String confirm = "Confirm";

            //Log In
            onView(withId(R.id.login_userName_input)).perform(typeText(username));
            closeSoftKeyboard();
            onView(withId(R.id.login_password_input)).perform(typeText(password));
            closeSoftKeyboard();
            onView(withId(R.id.login_btn)).perform(click());

            //Show Non-Empty Lists and delete one book from each
            onView(withId(R.id.all_btn)).perform(click());
            onView(withText(book1)).check(matches(isDisplayed()));
            onView(allOf(withId(R.id.book_title), withText(book1))).perform(click());
            onView(withId(R.id.add_book_to_list_btn)).perform(click());
            onView(withId(R.id.delete_book_from_list_btn)).perform(click());
            onView(withText(confirm)).perform(click());
            onView(withId(R.id.detail_book_close_btn)).perform(click());

            onView(withId(R.id.in_progress_btn)).perform(click());
            onView(withText(book2)).check(matches(isDisplayed()));
            onView(allOf(withId(R.id.book_title), withText(book2))).perform(click());
            onView(withId(R.id.add_book_to_list_btn)).perform(click());
            onView(withId(R.id.delete_book_from_list_btn)).perform(click());
            onView(withText(confirm)).perform(click());
            onView(withId(R.id.detail_book_close_btn)).perform(click());

            onView(withId(R.id.finished_btn)).perform(click());
            onView(withText(book3)).check(matches(isDisplayed()));
            onView(allOf(withId(R.id.book_title), withText(book3))).perform(click());
            onView(withId(R.id.add_book_to_list_btn)).perform(click());
            onView(withId(R.id.delete_book_from_list_btn)).perform(click());
            onView(withText(confirm)).perform(click());
            onView(withId(R.id.detail_book_close_btn)).perform(click());

            //Show Empty List
            //should all fail because they have been removed from their respective list
            onView(withId(R.id.all_btn)).perform(click());
            Exception exception4 = assertThrows(NoMatchingViewException.class, ()->{
                onView(withText(book1)).check(matches(isDisplayed()));
            });

            onView(withId(R.id.in_progress_btn)).perform(click());
            Exception exception5 = assertThrows(NoMatchingViewException.class, ()->{
                onView(withText(book2)).check(matches(isDisplayed()));
            });


            onView(withId(R.id.finished_btn)).perform(click());
            Exception exception6 = assertThrows(NoMatchingViewException.class, ()->{
                onView(allOf(withId(R.id.book_title), withText(book3))).check(matches(isDisplayed()));
            });

        }
        catch(AmbiguousViewMatcherException e){
            e.printStackTrace();
            assert false;
        }
    }
}
