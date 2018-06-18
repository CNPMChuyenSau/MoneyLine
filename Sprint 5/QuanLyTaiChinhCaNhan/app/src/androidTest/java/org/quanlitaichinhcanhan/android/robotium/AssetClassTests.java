package org.quanlitaichinhcanhan.android.robotium;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.assetallocation.AssetClassEditActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UiTestHelpersRobotium;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Asset Class edit form.
 */
@RunWith(AndroidJUnit4.class)
public class AssetClassTests
        extends ActivityInstrumentationTestCase2<AssetClassEditActivity> {

    private Solo solo;

    public AssetClassTests() {
        super(AssetClassEditActivity.class);
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
    public void visualAppearance() {
        assertThat(solo.searchText("Cancel")).isTrue();
    }

    @Test
    public void enterAllocation() {
        View allocationView = solo.getView(R.id.allocationEdit);
        solo.clickOnView(allocationView);

        solo.waitForDialogToOpen();
        solo.clickOnView(solo.getView(R.id.buttonKeyNum2));
        solo.clickOnView(solo.getView(R.id.buttonKeyNumDecimal));
        solo.clickOnView(solo.getView(R.id.buttonKeyNum5));
        solo.clickOnView(solo.getView(R.id.buttonKeyNum6));
        solo.clickOnText("OK");
        solo.waitForDialogToClose();

        assertThat(solo.waitForText("2.56")).isTrue();
    }

}
