package org.quanlitaichinhcanhan.android.robotium;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.assetallocation.editor.AssetAllocationEditorActivity;
import com.vanluom.group11.quanlytaichinhcanhan.assetallocation.AssetClassEditActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UiTestHelpersRobotium;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Asset Allocation UI tests.
 */
@RunWith(AndroidJUnit4.class)
public class AssetAllocationTests
        extends ActivityInstrumentationTestCase2<AssetAllocationEditorActivity> {

    private Solo solo;

    public AssetAllocationTests() {
        super(AssetAllocationEditorActivity.class);
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
    public void clickingBackArrowExits() {
        solo.clickOnActionBarHomeButton();
//        assertThat(solo.waitForLogMessage("Finishing Asset Allocation")).isTrue();
        assertThat(getActivity().isFinishing()).isTrue();
    }

    @Test
    public void initialView() {
        // layoutIsLandscape ?
        // title
        assertThat(solo.searchText("Asset Allocation")).isTrue();
        // no records
        assertThat(solo.searchText("No asset classes have been defined.")).isTrue();
        // addNew button
        assertThat(solo.waitForView(R.id.fab)).isTrue();
    }

    @Test
    public void openNewAssetClassForm() {
        View fab = getActivity().findViewById(R.id.fab);
        solo.clickOnView(fab);

        assertThat(solo.waitForActivity(AssetClassEditActivity.class)).isTrue();
    }

    @Test
    public void createAndDeleteAssetClass() {
        // Given

        String assetClassName = "cash";
        UiTestHelpersRobotium robot = new UiTestHelpersRobotium(solo);

        robot.clickOnFloatingButton();
        solo.waitForActivity(AssetClassEditActivity.class);

        EditText editText = (EditText) solo.getView(R.id.nameEdit);
        solo.enterText(editText, assetClassName);

        solo.clickOnView(solo.getView(R.id.allocationEdit));
        robot.enterInNumericInput("2.54");
        solo.clickOnText("OK");

        // update
        robot.clickDone();

        // confirm that the new item is listed
        assertThat(solo.searchText(assetClassName)).isTrue();

//        solo.clickLongOnText(assetClassName);
        solo.clickOnText(assetClassName);
        solo.clickOnText("Delete");
        solo.clickOnText("OK");

        // Then

        assertThat(solo.searchText(assetClassName)).isFalse();
    }

    /*
      todo: Tasks
      - show pie chart for allocation.
     */

    /*
    todo: Tests
    - we see a grid of asset allocations
    - can add a new allocation
      + create/edit form opens
      - can select a security from all the available ones
    + can delete an allocation
    - when adding a stock, the allocation value updates
    - updating a stock price updates the allocation value (update manually for test value)

    - test that fab opens the new asset class form
    - test that the asset classes are sorted.
    - ability to move classes up or down
     */
}
