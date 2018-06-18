
package org.quanlitaichinhcanhan.android.tests;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;
import com.vanluom.group11.quanlytaichinhcanhan.home.DatabaseMetadata;
import com.vanluom.group11.quanlytaichinhcanhan.home.DatabaseMetadataFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test the factory
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DatabaseMetadataFactoryTests {

    private DatabaseMetadataFactory _testObject;

    @Before
    public void setUp() throws Exception {
        _testObject = new DatabaseMetadataFactory(RuntimeEnvironment.application);
    }

    @After
    public void tearDown() throws Exception {
        _testObject = null;
    }

    @Test
    public void createDefaultItem() {
        DatabaseMetadata empty = _testObject.createDefaultEntry();

        assertThat(empty).isNotNull();
    }

}
