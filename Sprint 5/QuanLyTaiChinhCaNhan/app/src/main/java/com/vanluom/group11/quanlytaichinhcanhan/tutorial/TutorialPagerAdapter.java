
package com.vanluom.group11.quanlytaichinhcanhan.tutorial;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Adapter that returns the tutorial pages.
 */
public class TutorialPagerAdapter
    extends FragmentStatePagerAdapter {

    public TutorialPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        Fragment pageFragment;

        switch(i){
            case 0:
                pageFragment = new TutorialAccountsFragment();
                break;
            case 1:
                pageFragment = TutorialTransactionsFragment.newInstance();
                break;
            case 2:
                pageFragment = TutorialGlobalFragment.newInstance();
                break;
            case 3:
                pageFragment = TutorialFinancialOverviewFragment.newInstance();
                break;
            case 4:
                pageFragment = TutorialSyncFragment.newInstance();
                break;
            default:
                pageFragment = new TutorialAccountsFragment();
        }

        return pageFragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
