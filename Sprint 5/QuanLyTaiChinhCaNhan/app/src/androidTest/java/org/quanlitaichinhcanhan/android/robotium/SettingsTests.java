package org.quanlitaichinhcanhan.android.robotium;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.vanluom.group11.quanlytaichinhcanhan.settings.SettingsActivity;
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
public class SettingsTests
        extends ActivityInstrumentationTestCase2<SettingsActivity> {

    private Solo solo;

    public SettingsTests() {
        super(SettingsActivity.class);
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
//    public void canReturnHomeViaToolbar() {
//        solo.clickOnActionBarHomeButton();
//
//
//    }
}
