package org.quanlitaichinhcanhan.android.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vanluom.group11.quanlytaichinhcanhan.settings.LookFeelSettingsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Look and Feel preferences
 */
@RunWith(AndroidJUnit4.class)
public class SettingsLookFeelTests {
    @Rule
    public final ActivityTestRule<LookFeelSettingsActivity> activityRule =
            new ActivityTestRule<>(LookFeelSettingsActivity.class);

    @Test
    public void activityOpens() {
        onView(withText("Look & Feel"))
            .check(matches(isDisplayed()));
    }

    @Test
    public void changeTheme() {
        onView(withText("Theme"))
            .check(matches(isDisplayed()))
            .perform(click());

        onView(withText("Material Dark"))
            .perform(click());

        // TODO: 25/09/2015 confirm that the color has changed

    }
}
