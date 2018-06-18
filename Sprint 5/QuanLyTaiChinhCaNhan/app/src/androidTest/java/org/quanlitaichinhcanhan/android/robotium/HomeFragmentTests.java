package org.quanlitaichinhcanhan.android.robotium;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;
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
public class HomeFragmentTests
        extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public HomeFragmentTests() {
        super(MainActivity.class);
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

    /**
     * Open/close expandable view of accounts.
     * Attention: groups must be manually collapsed prior to running the test.
     */
    @Test
    public void expandCollapseAccountList() {
        // Given

        String accountName = "cash, BAM";
        boolean initiallyExpanded = solo.searchText(accountName);
//        collapseAllGroups();

        // When

        solo.clickOnText("Bank Accounts");

        // Then

        boolean accountVisible = solo.searchText(accountName);
        if (initiallyExpanded) {
            assertThat(accountVisible).isFalse();
        } else {
            assertThat(accountVisible).isTrue();
        }

        // and again

        solo.clickOnText("Bank Accounts");

        accountVisible = solo.searchText(accountName);
        if (initiallyExpanded) {
            assertThat(accountVisible).isTrue();
        } else {
            assertThat(accountVisible).isFalse();
        }
    }

    private void collapseAllGroups() {
        if (solo.searchText("28 degrees")) {
            solo.clickOnText("Credit Card Accounts");
        }

        if (solo.searchText("brl")) {
            solo.clickOnText("Investment Accounts");
        }

        if (solo.searchText("HSBC savings")) {
            solo.clickOnText("Term Accounts");
        }
    }
}
