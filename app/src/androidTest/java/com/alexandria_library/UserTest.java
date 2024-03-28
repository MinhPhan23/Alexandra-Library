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

import androidx.test.espresso.AmbiguousViewMatcherException;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.alexandria_library.presentation.Authentication.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class UserTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> testActivity = new ActivityScenarioRule<>(LoginActivity.class);

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
        try{

            //Credentials
            String username1 = "Tester";
            String password1 = "test";

            String username2 = "Andrei";
            String password2 = "123";

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

            //Show Empty List
            onView(withId(R.id.all_btn)).perform(click());
            //should fail because the list is completely empty
            Exception exception1 = assertThrows(NoMatchingViewException.class, ()->{
                onView(withText("The Seven Husbands of Evalyn Hugo")).check(matches(isDisplayed()));
            });

            Exception exception2 = assertThrows(NoMatchingViewException.class, ()->{
                onView(withText("To Kill a Mockingbird")).check(matches(isDisplayed()));
            });

            Exception exception3 = assertThrows(NoMatchingViewException.class, ()->{
                onView(withText("The Fault in Our Stars")).check(matches(isDisplayed()));
            });

            //Log Out
            onView(withId(R.id.account_btn)).perform(click());
            onView(withId(R.id.log_out_btn)).perform(click());

            //Log Into diffirent account
            onView(withId(R.id.login_userName_input)).perform(typeText(username2));
            closeSoftKeyboard();
            onView(withId(R.id.login_password_input)).perform(typeText(password2));
            closeSoftKeyboard();
            onView(withId(R.id.login_btn)).perform(click());

            //Show Non-Empty List
            onView(withId(R.id.all_btn)).perform(click());
            //should find all 3 no problem
            onView(withText("The Seven Husbands of Evalyn Hugo")).check(matches(isDisplayed()));
            onView(withText("To Kill a Mockingbird")).check(matches(isDisplayed()));
            onView(withText("The Fault in Our Stars")).check(matches(isDisplayed()));

            //Log Out
            onView(withId(R.id.account_btn)).perform(click());
            onView(withId(R.id.log_out_btn)).perform(click());

            //Log In
            onView(withId(R.id.login_userName_input)).perform(typeText(username1));
            closeSoftKeyboard();
            onView(withId(R.id.login_password_input)).perform(typeText(password1));
            closeSoftKeyboard();
            onView(withId(R.id.login_btn)).perform(click());

            //Show Empty List
            onView(withId(R.id.all_btn)).perform(click());
            //should all fail again because the list is completely empty
            Exception exception4 = assertThrows(NoMatchingViewException.class, ()->{
                onView(withText("The Seven Husbands of Evalyn Hugo")).check(matches(isDisplayed()));
            });

            Exception exception5 = assertThrows(NoMatchingViewException.class, ()->{
                onView(withText("To Kill a Mockingbird")).check(matches(isDisplayed()));
            });

            Exception exception6 = assertThrows(NoMatchingViewException.class, ()->{
                onView(withText("The Fault in Our Stars")).check(matches(isDisplayed()));
            });
        }
        catch(AmbiguousViewMatcherException e){
            e.printStackTrace();
            assert false;
        }
    }
}