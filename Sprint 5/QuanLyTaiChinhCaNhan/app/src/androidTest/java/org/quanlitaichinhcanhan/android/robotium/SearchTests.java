package org.quanlitaichinhcanhan.android.robotium;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.search.SearchActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UiTestHelpersRobotium;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Various Search activity tests.
 */
@RunWith(AndroidJUnit4.class)
public class SearchTests
        extends ActivityInstrumentationTestCase2<SearchActivity> {

    private Solo solo;

    public SearchTests() {
        super(SearchActivity.class);
    }

    @Before
    public void setUp() {
        solo = UiTestHelpersRobotium.setUp(this);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();

        UiTestHelpersRobotium.tearDown(solo);
    }

    @Test
    public void formOpens() {
        assertThat(solo.getView(R.id.action_search).isShown()).isTrue();
    }

    /**
     * This test illustrates another bug in Espresso. It is next to impossible to select a
     * child menu in expandable list view, in this case a SubCategory.
     * Let's see if it can be resolved with Robotium (comes to the rescue!)
     */
    @Test
    public void searchForSubcategory() {
        // Given

        assertThat(solo.searchText("Select Category")).isTrue();

        // When

        solo.clickOnText("Select Category");

        // Select category.
        solo.clickOnText("Food");
//        solo.clickOnText("Dining out");
        solo.clickOnView(solo.getView(R.id.selector, 5));

        // Then

        solo.waitForActivity(SearchActivity.class.getName());
        assertThat(solo.searchText("Food : Dining out")).isTrue();
    }
}
