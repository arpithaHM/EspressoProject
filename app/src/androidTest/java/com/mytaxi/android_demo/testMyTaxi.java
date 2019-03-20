package com.mytaxi.android_demo;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
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
        onView(withId(R.id.edt_username)).perform(typeText("crazydog335"));
        onView(withId(R.id.edt_password)).perform(typeText("venture"));
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(3000);

    }

    @Test
    public void validateALoginFunctionality() throws InterruptedException {

        onView(withId(R.id.searchContainer)).perform(click()).check(matches(isDisplayed()));

    }

    @Test
    public void validateBSearchDriverFunctionality() throws InterruptedException {
        onView(withId(R.id.searchContainer)).perform(click()).check(matches(isDisplayed()));

        onView(withId(R.id.textSearch)).perform(typeTextIntoFocusedView("sa"), closeSoftKeyboard());

        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.textViewDriverName)).check(matches(withText("Sarah Scott")));
        Espresso.pressBack();
        Thread.sleep(2000);
    }

    @Test
    public void validateCCallFunctionality() throws InterruptedException {
        onView(withId(R.id.searchContainer)).perform(click()).check(matches(isDisplayed()));

        onView(withId(R.id.textSearch)).perform(typeTextIntoFocusedView("sa"), closeSoftKeyboard());

        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.textViewDriverName)).check(matches(withText("Sarah Scott")));
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).perform(click());
        Thread.sleep(3000);
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressBack();

        Thread.sleep(2000);
    }

    private String getString(int resId){
        return getInstrumentation().getTargetContext().getString(resId);
    }


    @After
    public void tearDown() throws Exception{
        onView(withContentDescription(getString(R.string.navigation_drawer_open))).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_logout));
        Intents.release();
    }
}
