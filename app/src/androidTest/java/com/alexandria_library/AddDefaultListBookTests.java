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

import android.media.MediaPlayer;

import androidx.test.espresso.AmbiguousViewMatcherException;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.alexandria_library.R;
import com.alexandria_library.presentation.Authentication.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AddDefaultListBookTests {

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
    public void AddDefaultListBookTest() {
        try{

            //Credentials
            String username1 = "Tester";
            String password1 = "test";

            String bookName1 = "The Book Thief";
            String bookName2 = "The Fault in Our Stars";
            String bookName3 = "The Hunger Games";
            String confirm = "Confirm";

            //Account Registration
            onView(ViewMatchers.withId(R.id.user_mode_btn)).perform(click());
            onView(withId(R.id.register_btn)).perform(click());
            onView(withId(R.id.register_username_input)).perform(typeText(username1));
            closeSoftKeyboard();
            onView(withId(R.id.register_password_input)).perform(typeText(password1));
            closeSoftKeyboard();
            onView(withId(R.id.register_confirm_password_input)).perform(typeText(password1));
            closeSoftKeyboard();
            onView(withId(R.id.Create_register_btn)).perform(click());

            //Log In
            onView(withId(R.id.login_userName_input)).perform(typeText(username1));
            closeSoftKeyboard();
            onView(withId(R.id.login_password_input)).perform(typeText(password1));
            closeSoftKeyboard();
            onView(withId(R.id.login_btn)).perform(click());

            //show the current state of the lists and fail to find any book
            onView(withId(R.id.all_btn)).perform(click());
            Exception exception1 = assertThrows(NoMatchingViewException.class, ()->{
                onView(withText(bookName1)).check(matches(isDisplayed()));
            });
            onView(withId(R.id.in_progress_btn)).perform(click());
            Exception exception2 = assertThrows(NoMatchingViewException.class, ()->{
                onView(withText(bookName2)).check(matches(isDisplayed()));
            });
            onView(withId(R.id.finished_btn)).perform(click());
            Exception exception3 = assertThrows(NoMatchingViewException.class, ()->{
                onView(withText(bookName3)).check(matches(isDisplayed()));
            });

            //switch to the library view & add book & show that you cant add duplicates
            onView(withId(R.id.library_btn)).perform(click());
            onView(withText(bookName1)).perform(click());
            onView(withId(R.id.add_book_to_list_btn)).perform(click());

            //adds once to each list
            onView(withId(R.id.add_to_all_list)).perform(click());
            onView(withText(confirm)).perform(click());

            //tries to add twice to show duplicates dont work
            onView(withId(R.id.add_to_all_list)).perform(click());
            onView(withText(confirm)).perform(click());

            //closes the book view
            onView(withId(R.id.detail_book_close_btn)).perform(click());

            //opens the close up view of a second book
            onView(withText(bookName2)).perform(click());
            onView(withId(R.id.add_book_to_list_btn)).perform(click());
            onView(withId(R.id.add_to_inprogress_list)).perform(click());
            onView(withText(confirm)).perform(click());
            onView(withId(R.id.detail_book_close_btn)).perform(click());

            //opens the close up view of a third book
            onView(withText(bookName3)).perform(click());
            onView(withId(R.id.add_book_to_list_btn)).perform(click());
            onView(withId(R.id.add_to_finish_list)).perform(click());
            onView(withText(confirm)).perform(click());
            onView(withId(R.id.detail_book_close_btn)).perform(click());

            //checks that all list has book1
            onView(withId(R.id.all_btn)).perform(click());
            onView(allOf(withId(R.id.book_title), withText(bookName1)));

            //checks that inprogress list has book2
            onView(withId(R.id.in_progress_btn)).perform(click());
            onView(allOf(withId(R.id.book_title), withText(bookName2)));

            //checks that finished list has book3
            onView(withId(R.id.finished_btn)).perform(click());
            onView(allOf(withId(R.id.book_title), withText(bookName3)));

        }
        catch(AmbiguousViewMatcherException e){
            e.printStackTrace();
            assert false;
        }
    }
}
