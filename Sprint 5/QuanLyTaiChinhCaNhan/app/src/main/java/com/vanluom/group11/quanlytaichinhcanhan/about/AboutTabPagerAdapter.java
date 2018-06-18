package com.vanluom.group11.quanlytaichinhcanhan.about;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class AboutTabPagerAdapter extends FragmentPagerAdapter {

    private static final int NUMBER_PAGER = 2;
    private String[] titles;

    @Override
    public int getCount() {
        return NUMBER_PAGER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AboutFragment.newInstance(position);
            case 1:
                return AboutCreditsFragment.newInstance(position);
        }
        return null;
    }

    AboutTabPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }
}
