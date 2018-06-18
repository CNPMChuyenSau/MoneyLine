package org.quanlitaichinhcanhan.android.espresso;

import android.support.test.rule.ActivityTestRule;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Category;
import com.vanluom.group11.quanlytaichinhcanhan.search.SearchActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * This is not operational because it is not possible to select a subcategory - a child item
 * in expandable list view.
 * There is the correct test in Robotium test suite.
 *
 * Various Search activity tests.
 */
public class SearchTests {
    @Rule
    public final ActivityTestRule<SearchActivity> activityRule =
        new ActivityTestRule<>(SearchActivity.class);

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void formOpens() {
//        Espresso.onView(allOf(withText("Search")))
        onView(withId(R.id.action_search))
            .check(matches(isDisplayed()));
    }

    /**
     * This test illustrates another bug in Espresso. It is next to impossible to select a
     * child menu in expandable list view, in this case a SubCategory.
     */
    @Test
    public void searchForSubcategory() {
        formOpens();

        onView(withHint("Select Category"))
            .check(matches(isDisplayed()))
            .perform(click());

        onView(withText("Food"))
            .check(matches(isDisplayed()))
            .perform(click());

        onData(allOf(is(instanceOf(Category.class))))
            .atPosition(5)
            .onChildView(withId(R.id.selector))
            .perform(click());

        formOpens();

        onView(withHint("Select Category"))
            .check(matches(isDisplayed()))
            .check(matches(withText("Food : Dining out")));
    }
}
