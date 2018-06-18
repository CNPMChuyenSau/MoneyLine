package com.vanluom.group11.quanlytaichinhcanhan.about;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;

import timber.log.Timber;

/**
 * About the app
 */
public class AboutActivity
    extends MmxBaseFragmentActivity {

    private static final String BUNDLE_KEY_TABINDEX = "AboutActivity:tabindex";

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
       mViewPager.setAdapter(new AboutTabPagerAdapter(getSupportFragmentManager(), new String[]{
               getString(R.string.about),
               getString(R.string.credits)
       }));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(mViewPager);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(BUNDLE_KEY_TABINDEX, mViewPager.getCurrentItem());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mViewPager.setCurrentItem(savedInstanceState.getInt(BUNDLE_KEY_TABINDEX));
    }


    @Override
    protected void setTheme() {
        try {
            this.setTheme(R.style.Theme_Money_Manager_Light);
        } catch (Exception e) {
            Timber.e(e, "setting theme");
        }
    }
}
