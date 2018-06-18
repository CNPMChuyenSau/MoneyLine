package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.TaiKhoan.Fragment_Account_Tab1;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.TaiKhoan.Fragment_Account_Tab2;


public class PageTaiKhoanAdapter extends FragmentStatePagerAdapter {
    public PageTaiKhoanAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new Fragment_Account_Tab1();
                break;
            case 1:
                frag = new Fragment_Account_Tab2();
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
                title = "Tài Khoản";
                break;
            case 1:
                title = "Sổ Tiết Kiệm";
                break;
        }
        return title;
    }
}