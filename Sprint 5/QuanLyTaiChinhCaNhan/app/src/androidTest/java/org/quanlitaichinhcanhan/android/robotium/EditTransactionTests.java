package org.quanlitaichinhcanhan.android.robotium;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.transactions.CheckingTransactionEditActivity;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextViewFontIcon;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UiTestHelpersRobotium;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Robotium test for Edit Transaction activity.
 */
@RunWith(AndroidJUnit4.class)
public class EditTransactionTests
    extends ActivityInstrumentationTestCase2<CheckingTransactionEditActivity> {

  private Solo solo;

  public EditTransactionTests() {
    super(CheckingTransactionEditActivity.class);
  }

    @Before
    public void setUp() throws Exception {
        solo = UiTestHelpersRobotium.setUp(this);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();

        UiTestHelpersRobotium.tearDown(solo);
    }

    @Test
    public void testActivityExists() {
        CheckingTransactionEditActivity activity = getActivity();
        assertNotNull(activity);
    }

    @Test
    public void testStatusChange() {
        solo.waitForActivity(CheckingTransactionEditActivity.class.getSimpleName());

        boolean spinnerFound = solo.searchText("None");
        assertThat(spinnerFound).isTrue();

        solo.pressSpinnerItem(0, 1);
        assertThat(solo.isSpinnerTextSelected(0, "Reconciled"));

        solo.pressSpinnerItem(0, 1);
        assertThat(solo.isSpinnerTextSelected(0, "Void"));

        solo.pressSpinnerItem(0, -2);
        assertThat(solo.isSpinnerTextSelected(0, "None"));

        solo.pressSpinnerItem(0, 4);
        assertThat(solo.isSpinnerTextSelected(0, "Duplicate"));

        solo.pressSpinnerItem(0, -1);
        assertThat(solo.isSpinnerTextSelected(0, "Follow up"));
    }

    @Test
    public void changeDate() {
        // Given
        // The date is today.
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        Locale defaultLocale = Locale.ENGLISH;
        int month = calendar.get(Calendar.MONTH);
        String monthDisplay = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, defaultLocale);
        String yearDisplay = calendar.getDisplayName(Calendar.YEAR, Calendar.LONG, defaultLocale);
        int year = calendar.get(Calendar.YEAR);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.LONG_DATE_PATTERN, defaultLocale);
        String todayFormatted = dateFormat.format(today);

        // expected date
        int expectedDay = 15;
        calendar.set(Calendar.DAY_OF_MONTH, expectedDay);
        Date expectedDate = calendar.getTime();
        String expectedDateDisplay = dateFormat.format(expectedDate);

        RobotoTextViewFontIcon dateView = (RobotoTextViewFontIcon) solo.getView(R.id.textViewDate);
        String displayedDate = dateView.getText().toString();

        assertThat(displayedDate).isEqualTo(todayFormatted);


        // When
        // changing the date to 15th
        solo.clickOnView(dateView);
        solo.waitForDialogToOpen(1000);
        System.out.println("binaryDialog open");

//        assertThat(solo.searchText(month + " " + year)).isTrue();
        solo.clickOnText(Integer.toString(expectedDay));

        // todo: this also does not work!!! Can't select a date.
//        solo.setDatePicker(0, year, month, expectedDay);

        solo.waitForDialogToClose(2000);

        // Then
        // The displayed date should show the 15th of this month
        String actualDate = dateView.getText().toString();

        assertThat(actualDate).isEqualTo(expectedDateDisplay);
    }
}