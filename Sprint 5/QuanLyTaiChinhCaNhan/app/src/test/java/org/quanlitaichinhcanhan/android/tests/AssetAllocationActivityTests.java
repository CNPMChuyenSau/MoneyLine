package org.quanlitaichinhcanhan.android.tests;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;
import com.vanluom.group11.quanlytaichinhcanhan.assetallocation.editor.AssetAllocationEditorActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.DataHelpers;
import org.quanlitaichinhcanhan.android.testhelpers.UnitTestHelper;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Asset Allocation UI
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class AssetAllocationActivityTests {

    private ActivityController<AssetAllocationEditorActivity> controller;
    private AssetAllocationEditorActivity activity;

    @Before
    public void setup() {
        // initialize database
        UnitTestHelper.setupContentProvider();

        // initialize support for activities (UI)
        this.controller = UnitTestHelper.getController(AssetAllocationEditorActivity.class);
        this.activity = UnitTestHelper.getActivity(this.controller);
    }

    @After
    public void tearDown() {
        // Destroy the activity controller.
        this.controller.destroy();
        // Reset database instance between tests.
        UnitTestHelper.teardownDatabase();
    }

    @Test
    public void instantiation() {
        assertThat(this.activity).isNotNull();
    }

    @Test
    public void loadAllocation() {
        // Given
        DataHelpers.createAllocation();

        // When

        // Then
        assertThat(this.activity).isNotNull();
    }

    /**
     * Exception test example.
     */
    @Test(expected=RuntimeException.class)
    public void throwsException() {
        throw new RuntimeException("bang!");
    }

}
