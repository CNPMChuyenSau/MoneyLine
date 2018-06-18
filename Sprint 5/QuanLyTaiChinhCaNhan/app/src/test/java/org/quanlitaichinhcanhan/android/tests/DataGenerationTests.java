package org.quanlitaichinhcanhan.android.tests;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.DataHelpers;
import org.quanlitaichinhcanhan.android.testhelpers.UnitTestHelper;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Test creation of records in the database.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DataGenerationTests {
    @Before
    public void setUp() {
        UnitTestHelper.setupContentProvider();
        UnitTestHelper.setupLog();
    }

    @After
    public void tearDown() {
        UnitTestHelper.teardownDatabase();
    }

    @Test
    public void testGeneration() {
        // test generation and insertion of main records.

        DataHelpers.insertData();

        // asserts are inside the helper method itself.
    }
}
