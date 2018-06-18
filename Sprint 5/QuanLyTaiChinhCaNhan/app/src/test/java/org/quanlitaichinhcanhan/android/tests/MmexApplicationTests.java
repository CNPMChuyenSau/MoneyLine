package org.quanlitaichinhcanhan.android.tests;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;
import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDatabaseUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowEnvironment;

import static org.junit.Assert.assertTrue;

/**
 * Test the methods in MoneyManagerApplication.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MmexApplicationTests {

    private Context context;

    @Before
    public void setUp() throws Exception {
        this.context = RuntimeEnvironment.application;

        // External storage
//        File externalStorage = new File("/sdcard/MoneyManagerEx/");
//        ShadowEnvironment.setExternalStorageEmulated(externalStorage, true);
    }

    @After
    public void tearDown() throws Exception {
        this.context = null;

        ShadowEnvironment.reset();
    }

    /**
     * Ensure that the default file name can not be empty.
     */
    @Test
    public void defaultDatabaseNameContainsFileName() throws Exception {
        String expected = "data.mmb";

        String actual = MoneyManagerApplication.getDatabasePath(context);

//        assertEquals(expected, actual);
        assertTrue(actual.contains("/" + expected));
    }

    /**
     * The test fails.
     */
    @Test
    public void dbDirectoryHasAppName() {
        final String expected = "MoneyManagerEx";

        MmxDatabaseUtils dbUtils = new MmxDatabaseUtils(this.context);
        String actual = dbUtils.getDefaultDatabaseDirectory();

        assertTrue(actual.contains(expected));
    }

}
