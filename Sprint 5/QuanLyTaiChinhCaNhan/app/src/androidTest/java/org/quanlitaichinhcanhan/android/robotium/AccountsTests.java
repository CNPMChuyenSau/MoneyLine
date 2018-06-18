package org.quanlitaichinhcanhan.android.robotium;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.account.AccountListActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UiTestHelpersRobotium;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Accounts list tests.
 */
@RunWith(AndroidJUnit4.class)
public class AccountsTests
        extends ActivityInstrumentationTestCase2<AccountListActivity> {

    private Solo solo;

    public AccountsTests() {
        super(AccountListActivity.class);
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

    @Test
    public void canCreateAndDeleteAccount() {
        // Given

        UiTestHelpersRobotium robot = new UiTestHelpersRobotium(solo);
        final String accountName = "Test Account";

        // When

        robot.clickOnFloatingButton();

        solo.waitForDialogToOpen();
        View view = solo.getView(R.id.editTextAccountName);
        EditText editText = (EditText) view;
        solo.enterText(editText, accountName);
        robot.clickDone();
        solo.waitForDialogToClose();

        // delete
//        solo.clickLongOnText(accountName);
        solo.clickOnText(accountName);
        solo.clickOnText("Delete");
        solo.clickOnText("OK");

        // Then

        assertThat(solo.searchText(accountName)).isFalse();
    }

    @Test
    public void getNotificationForAccountThatCanNotBeDeleted() {
        solo.clickOnText("cash, EUR");
        solo.clickOnText("Delete");

        solo.waitForDialogToOpen();

        assertThat(solo.searchText("can not be deleted")).isTrue();

        solo.clickOnText("OK");
    }
}
