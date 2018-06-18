
package org.quanlitaichinhcanhan.android.tests;

import android.os.Environment;
import android.util.Log;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;
import com.vanluom.group11.quanlytaichinhcanhan.database.MmxOpenHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UnitTestHelper;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Tests related to database open helper.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MmexHelperTests {

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
    public void backup_file_generation() {
        String path = Environment.getExternalStorageDirectory().getPath() + "/somefile.mmb";

        MmxOpenHelper testObject = new MmxOpenHelper(UnitTestHelper.getContext(), path);

        try {
            testObject.createDatabaseBackupOnUpgrade(path, 3);
        } catch (Exception ex) {
            Log.d("error", ex.getMessage());
        }
    }
}
