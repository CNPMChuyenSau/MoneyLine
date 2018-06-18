package org.quanlitaichinhcanhan.android.espresso;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Espresso tests for the Main Activity.
 * Created by Alen Siljak on 24/09/2015.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTests {

    @Rule
    public final ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void openDashboard() {
        onView(withText("Money Manager Ex"))
                .check(matches(isDisplayed()));

        onView(withId(R.id.drawerLayout))
                .perform(DrawerActions.open());

        onView(withText("Entities"))
            .check(matches(isDisplayed()));

    }

    @Test
    public void isWelcomeViewDisplayed() {
        onView(withText("Welcome to MoneyManagerEx!"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void isAccountsListDisplayed() {
        onView(withText("Bank Accounts"))
                .check(matches(isDisplayed()));
    }

}
