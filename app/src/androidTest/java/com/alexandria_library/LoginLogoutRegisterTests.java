package com.alexandria_library;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;

import androidx.test.espresso.AmbiguousViewMatcherException;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import com.alexandria_library.dso.Book;
import com.alexandria_library.presentation.Authentication.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginLogoutRegisterTests {

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
    public void test() {
        try{
            //onView(withId(R.id.)).perform();

            //Credentials
            String username1 = "Tester";
            String password1 = "test";

            String username2 = "Andrei";
            String password2 = "123";

            //Account Registration
            onView(withId(R.id.user_mode_btn)).perform(click());
            onView(withId(R.id.register_btn)).perform(click());
            onView(withId(R.id.register_username_input)).perform(typeText(username1));

            onView(withId(R.id.register_password_input)).perform(typeText(password1));

            onView(withId(R.id.register_confirm_password_input)).perform(typeText(password1));

            onView(withId(R.id.Create_register_btn)).perform(click());

            //Log In
            onView(withId(R.id.login_userName_input)).perform(typeText(username1));

            onView(withId(R.id.login_password_input)).perform(typeText(password1));

            onView(withId(R.id.login_btn)).perform(click());

            //Show Empty List
            onView(withId(R.id.all_btn)).perform(click());

            //Log Out
            onView(withId(R.id.account_btn)).perform(click());
            onView(withId(R.id.log_out_btn)).perform(click());

            //Log Into diffirent account
            onView(withId(R.id.login_userName_input)).perform(typeText(username2));

            onView(withId(R.id.login_password_input)).perform(typeText(password2));

            onView(withId(R.id.login_btn)).perform(click());

            //Show Non-Empty List
            onView(withId(R.id.all_btn)).perform(click());

            //Log Out
            onView(withId(R.id.account_btn)).perform(click());
            onView(withId(R.id.log_out_btn)).perform(click());

            //Log In
            onView(withId(R.id.login_userName_input)).perform(typeText(username1));

            onView(withId(R.id.login_password_input)).perform(typeText(password1));

            onView(withId(R.id.login_btn)).perform(click());

            //Show Empty List
            onView(withId(R.id.all_btn)).perform(click());
        }
        catch(AmbiguousViewMatcherException e){
            e.printStackTrace();
            assert false;
        }
    }
}