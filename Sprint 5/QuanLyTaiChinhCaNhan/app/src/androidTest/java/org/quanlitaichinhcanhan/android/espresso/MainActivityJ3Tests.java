package org.quanlitaichinhcanhan.android.espresso;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Espresso tests for the Main Activity, using JUnit 3 and instrumentation test case.
 * This in NOT NEEDED as JUnit 4 tests can run in the emulator equally well.
 * Displayed here only as a sample.
 */
public class MainActivityJ3Tests
        extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity testObject;
//    private UiTestHelpersRobotium helper;

    public MainActivityJ3Tests() {
        super(MainActivity.class);

    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

//        solo = new Solo(getInstrumentation(), getActivity());
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        this.testObject = getActivity();
//        this.helper = new UiTestHelpersRobotium(solo);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        this.testObject = null;
    }

    public void testCreation() {
        assertNotNull(testObject);

        // todo check this
//        onView(withId(R.id.linearLayoutWelcome))
//            .check(matches(isDisplayed()));
    }

    public void testWelcomeViewDisplayed() {
        onView(withText("Welcome to MoneyManagerEx!"))
            .check(matches(isDisplayed()));
    }

    public void testAccountsListDisplayed() {
        onView(withText("Bank Accounts"))
                .check(matches(isDisplayed()));
    }

}
