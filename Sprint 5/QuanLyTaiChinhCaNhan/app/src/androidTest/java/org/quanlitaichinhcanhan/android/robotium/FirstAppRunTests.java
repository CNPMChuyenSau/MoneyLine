package org.quanlitaichinhcanhan.android.robotium;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.vanluom.group11.quanlytaichinhcanhan.currency.list.CurrencyListActivity;
import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UiTestHelpersRobotium;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test the startup of the Main Activity for the very first time after installation.
 * The tests here require that the emulator is pre-set manually by:
 * - uninstalling the app from the device
 * - deleting the MoneyManagerEx directory from the internal storage
 */
@RunWith(AndroidJUnit4.class)
public class FirstAppRunTests
        extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public FirstAppRunTests() {
        super(MainActivity.class);

    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        solo = UiTestHelpersRobotium.setUp(this);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();

        UiTestHelpersRobotium.tearDown(solo);
    }

    @Ignore
    public void welcomeScreen() {
        assertTrue(solo.waitForText("Welcome to MoneyManagerEx", 1, 1000));
    }

    @Ignore
    public void changelog() {
        assertTrue(solo.waitForActivity(MainActivity.class, 2000));

        assertTrue(solo.waitForDialogToOpen());

        assertTrue(solo.waitForText("Changelog"));

        assertTrue(solo.searchText("OK", true));
        solo.clickOnText("OK", 1, true);
    }

    @Ignore
    public void tutorial() {
        solo.assertCurrentActivity("wrong activity", MainActivity.class);

        assertTrue(solo.waitForActivity(MainActivity.class, 2000));

//        assertTrue("tutorial activity should open", solo.waitForActivity(TutorialActivity.class, 2000));

//        System.out.println("waiting for text Accounts");
        assertTrue(solo.waitForText("Accounts", 1, 1000, false, true));
        assertTrue("can't find text Close", solo.waitForText("Close", 1, 1000, false, true));

    }

    public void currenciesCreated() {
        solo.clickOnActionBarHomeButton();
        solo.clickOnText("Entities");
        solo.clickOnText("Currencies");

        assertThat(solo.waitForActivity(CurrencyListActivity.class.getSimpleName())).isTrue();
        assertThat(solo.searchText("Bosnia and Herzegovina")).isTrue();
    }

    /**
     * This is the full test for the new installation of the app. Runs all the partial tests.
     */
    @Test
    public void runForTheFirstTime() {
        // First the tutorial should be shown.
        tutorial();
        solo.clickOnText("Close", 1, false);

        changelog();

        welcomeScreen();

        currenciesCreated();
    }
}
