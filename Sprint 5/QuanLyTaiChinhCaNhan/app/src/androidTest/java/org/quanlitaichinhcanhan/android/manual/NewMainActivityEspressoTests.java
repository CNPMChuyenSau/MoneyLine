package org.quanlitaichinhcanhan.android.manual;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UiTestHelpersEspresso;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;

/**
 * Espresso tests for the Main Activity.
 * The tests start with a fresh copy of preferences.
 */
@RunWith(AndroidJUnit4.class)
public class NewMainActivityEspressoTests {

    @Rule
    public final ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private UiTestHelpersEspresso helper;

    @Before
    public void setUp() {
        this.helper = new UiTestHelpersEspresso();
        helper.clearPreferences(InstrumentationRegistry.getContext());
    }

    @After
    public void tearDown() {
        this.helper.clearPreferences(InstrumentationRegistry.getContext());
        this.helper = null;
    }

    @Ignore
    public void welcomeScreen() {
        onView(withText("Welcome to MoneyManagerEx"))
                .check(matches(isDisplayed()));
    }

    @Ignore
    public void changelog() {
//        assertTrue(solo.waitForActivity(MainActivity.class, 2000));
//
//        assertTrue(solo.waitForDialogToOpen());

//        assertTrue(solo.waitForText("Changelog"));
        onView(withText("Changelog"))
                .check(matches(isDisplayed()));

//        assertTrue(solo.searchText("OK", true));
//        solo.clickOnText("OK", 1, true);
        onView(withText("OK"))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    @Ignore
    public void tutorial() {
        onView(withText("Accounts"))
            .check(matches(isDisplayed()));

        onView(withText("Close"))
            .check(matches(isDisplayed()));
    }

    /**
     * This is the full test for the new installation of the app. Runs all the partial tests.
     */
    @Test
    public void runAll() {
//        Intent intent = new Intent();


        // First the tutorial should be shown.
        tutorial();
        onView(withText("Close"))
            .perform(click());

        changelog();

        welcomeScreen();

        assertTrue(true);
    }

}
