
package com.vanluom.group11.quanlytaichinhcanhan.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.log.ErrorRaisedEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Base activity for the settings activities.
 */
public class BaseSettingsFragmentActivity
    extends MmxBaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.settings_activity);
        setDisplayHomeAsUpEnabled(true);

        //
    }

    @Subscribe
    public void onEvent(ErrorRaisedEvent event) {
        new UIHelper(this).showToast(event.message);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically e clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        if (id == android.R.id.home) {
//            Log.d("test", "action bar clicked");
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    protected void setSettingFragment(PreferenceFragmentCompat fragment) {
        // use the class name as the fragment tag.
        String tag = fragment.getClass().getSimpleName();

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction tx = manager.beginTransaction();
        tx.replace(R.id.content, fragment, tag);

        // Add to backstack only if this is not the first fragment, and the fragment is not already added.
        List<Fragment> fragments = manager.getFragments();
        boolean isFirstFragment = fragments == null || fragments.size() == 0;

        Fragment existing = manager.findFragmentByTag(tag);
        boolean isAdded = existing != null;

        if (!isFirstFragment && !isAdded) {
            tx.addToBackStack(null);
        }

        tx.commit();
    }
}
