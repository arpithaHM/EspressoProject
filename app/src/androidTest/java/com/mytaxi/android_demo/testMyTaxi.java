package com.mytaxi.android_demo;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class testMyTaxi {

    private static final String userName = "crazydog335";
    private static final String password = "venture";
    private static final String stringToBeSearched = "sa";
    private static final String driverName = "Sarah Scott";

    @Rule
    public ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mActivity = null;
    private UiDevice mDevice;

    @Before
    public void setUp() throws Exception{
        Intents.init();
        mActivity = activity.getActivity();
        mDevice =
                UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_username)).perform(typeText(userName));
        onView(withId(R.id.edt_password)).perform(typeText(password));
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(3000);
        Log.println(Log.INFO, "Login to app ", "user logged in to app");
    }

    @Test
    public void validateALoginFunctionality() throws InterruptedException {
        try{
        onView(withId(R.id.searchContainer)).perform(click()).check(matches(isDisplayed()));
        Log.println(Log.INFO, "validate login", "Login successful");}
        catch(Exception e){
            e.printStackTrace();
            Log.println(Log.INFO, "validate login", "Login unsuccessful");
        }
    }

    @Test
    public void validateBSearchDriverFunctionality() throws InterruptedException {
        try {
            onView(withId(R.id.searchContainer)).perform(click()).check(matches(isDisplayed()));

            onView(withId(R.id.textSearch)).perform(typeTextIntoFocusedView(stringToBeSearched), closeSoftKeyboard());

            onView(withText(driverName))
                    .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            onView(withText(driverName))
                    .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                    .perform(click());
            Thread.sleep(2000);
            onView(withId(R.id.textViewDriverName)).check(matches(withText(driverName)));
            Espresso.pressBack();
            Thread.sleep(2000);
            Log.println(Log.INFO, "validate serach driver", "able to search driver with the given text");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Log.println(Log.INFO, "validate serach driver", "not able to serach driver");
        }
    }

    @Test
    public void validateCCallFunctionality() throws InterruptedException {
        try {
            onView(withId(R.id.searchContainer)).perform(click()).check(matches(isDisplayed()));

            onView(withId(R.id.textSearch)).perform(typeTextIntoFocusedView(stringToBeSearched), closeSoftKeyboard());

            onView(withText(driverName))
                    .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            onView(withText(driverName))
                    .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                    .perform(click());
            Thread.sleep(2000);
            onView(withId(R.id.textViewDriverName)).check(matches(withText(driverName)));
            onView(withId(R.id.fab)).check(matches(isDisplayed()));
            onView(withId(R.id.fab)).perform(click());
            Thread.sleep(3000);
            mDevice.pressBack();
            mDevice.pressBack();
            mDevice.pressBack();
            mDevice.pressBack();

            Thread.sleep(2000);
            Log.println(Log.INFO,"Validate driver call functionality", "Driver call functionality working as expected");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Log.println(Log.INFO, "Validate driver call functionality", "Unable to place call to driver");

        }
    }

    private String getString(int resId){
        return getInstrumentation().getTargetContext().getString(resId);
    }


    @After
    public void tearDown() throws Exception{
        try {
            onView(withContentDescription(getString(R.string.navigation_drawer_open))).perform(click());
            Thread.sleep(2000);

            onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_logout));
            Intents.release();
            Log.println(Log.INFO, "Validate Logout functionality", "Logout successful");
        }
        catch(Exception e){
            e.printStackTrace();
            Log.println(Log.INFO, "Validate Logout functionality", "Logout unsuccessful");
        }
    }
}
