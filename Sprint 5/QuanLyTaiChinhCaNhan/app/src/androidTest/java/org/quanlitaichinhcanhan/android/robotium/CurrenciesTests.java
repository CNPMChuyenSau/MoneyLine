package org.quanlitaichinhcanhan.android.robotium;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.vanluom.group11.quanlytaichinhcanhan.currency.list.CurrencyListActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UiTestHelpersRobotium;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test sample class. Use as a template for other tests.
 */
@RunWith(AndroidJUnit4.class)
public class CurrenciesTests
        extends ActivityInstrumentationTestCase2<CurrencyListActivity> {

    private Solo solo;

    public CurrenciesTests() {
        super(CurrencyListActivity.class);
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
        assertThat(solo.waitForActivity(getActivity().getClass().getSimpleName())).isTrue();
    }

//    @Test
//    public void canCreateAndDeleteCurrency() {
//        // Given
//
//        UiTestHelpersRobotium robot = new UiTestHelpersRobotium(solo);
//        final String accountName = "Test Currency";
//
//        // When
//
//        robot.clickOnFloatingButton();
//
//        solo.waitForDialogToOpen();
//        EditText editText = solo.getEditText(0);
//        solo.enterText(editText, accountName);
//
//        // select symbol
//        solo.pressSpinnerItem(0, 2);
//        assertThat(solo.isSpinnerTextSelected(0, "AFN"));
//
//        robot.clickDone();
//        solo.waitForDialogToClose();
//
//        // delete
////        solo.clickLongOnText(accountName);
//        solo.clickOnText(accountName);
//        solo.clickOnText("Delete");
//        solo.clickOnText("OK");
//
//        // Then
//
//        assertThat(solo.searchText(accountName)).isFalse();
//    }

    @Test
    public void getNotificationForCurrencyThatCanNotBeDeleted() {
        solo.clickOnText("Australian Dollar");
        solo.clickOnText("Delete");

        solo.waitForDialogToOpen();

        assertThat(solo.searchText("can not be deleted")).isTrue();

        solo.clickOnText("OK");
    }

}
