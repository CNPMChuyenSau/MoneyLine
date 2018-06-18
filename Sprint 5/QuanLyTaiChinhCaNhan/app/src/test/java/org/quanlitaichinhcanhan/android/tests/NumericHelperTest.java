package org.quanlitaichinhcanhan.android.tests;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;
import com.vanluom.group11.quanlytaichinhcanhan.core.NumericHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Numeric helper tests.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class NumericHelperTest {

    private NumericHelper _numericHelper;

    @Before
    public void setUp() throws Exception {
        Context context = RuntimeEnvironment.application.getApplicationContext();
        _numericHelper = new NumericHelper(context);

    }

    @After
    public void tearDown() throws Exception {
        _numericHelper = null;
    }

    @Test
    public void testIsNumeric() throws Exception {
        boolean actual = NumericHelper.isNumeric("3");
        assertTrue(actual);
    }

    @Test
    public void testTryParse() throws Exception {
        int actual = _numericHelper.tryParse("64");
        assertEquals(64, actual);
    }

    public void testGetNumberFormatted() throws Exception {

    }

    public void testGetNumberDecimal() throws Exception {

    }
}