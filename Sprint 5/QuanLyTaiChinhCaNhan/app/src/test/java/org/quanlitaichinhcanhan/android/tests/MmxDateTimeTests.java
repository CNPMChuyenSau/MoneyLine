
package org.quanlitaichinhcanhan.android.tests;

import android.util.Log;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Tests for conversion from JodaTime back to the standard Java date types.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class MmxDateTimeTests {

    private MmxDate _util;

    @Before
    public void setup() {
        _util = new MmxDate();
    }

    @Test
    public void basicTests() {
        String current = _util.getCalendar().toString();
        Log.d("test", current);
    }
}
