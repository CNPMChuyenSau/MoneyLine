package org.quanlitaichinhcanhan.android.tests;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A model for test class.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class BaseTests {

    // private ActivityController<T> controller;
    // private T activity;

    @Before
    public void setup() {
//        Context context = UnitTestHelper.getContext();

        // initialize support for activities (UI)
//        this.controller = UnitTestHelper.getController(MainActivity.class);
//        this.activity = UnitTestHelper.getActivity(this.controller);

        // initialize database
        // UnitTestHelper.setupContentProvider();
    }

    @After
    public void tearDown() {
        // Reset database instance between tests.
        // UnitTestHelper.teardownDatabase();

        // Destroy the activity controller.
//        this.controller.destroy();
    }

    @Test
    public void testTemplate() {
        assertThat(true).isTrue();
    }

    /**
     * Exception test example.
     */
    @Test(expected=RuntimeException.class)
    public void throwsException() {
        throw new RuntimeException("bang!");
    }
}
