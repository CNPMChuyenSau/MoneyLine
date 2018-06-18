package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.FrameLayout;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity.Fragment_Select_Note;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity.Fragment_ThuChi;


// Adapter cho tablayout của Activity ThuChi
public class PageThuChiAdapter extends FragmentStatePagerAdapter {
    private Context a;
    private FragmentManager fm;
    private android.widget.FrameLayout fl;
    private FrameLayout flRight;
    private FrameLayout flInRight;

    public PageThuChiAdapter(FragmentManager fm, Context a, FrameLayout fl, FrameLayout flRight, FrameLayout flInRight) {
        super(fm);
        this.a = a;
        this.fm = fm;
        this.fl = fl;
        this.flRight = flRight;
        this.flInRight = flInRight;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new Fragment_Select_Note(fm, fl, flRight);
                break;
            case 1:
                frag = new Fragment_ThuChi(a, fm, fl, flRight, flInRight);
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Lịch sử giao dịch";
                break;
            case 1:
                title = "Thêm thu chi";
                break;
        }
        return title;
    }
}