package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter.PageTaiKhoanAdapter;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.TaiKhoan.Fragment_Add_Account;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.TaiKhoan.Fragment_Add_Account_Saving;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.SoTietKiem;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.TK;


@SuppressWarnings("deprecation")
public class TaiKhoanActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager pager;
    TabLayout tabLayout;
    ImageView imgAdd;
    ViewPager view_pager;
    static FragmentManager fragmentManager;
    static LinearLayout contentAccount;
    Animation animExit, animation, anim_left_in, anim_right_out;
    static FrameLayout frameLayoutContent, frame_layout_content_tick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tai_khoan);
        Init();
        AddPager();
        imgAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == imgAdd) {
            if (tabLayout.getSelectedTabPosition() == 0) {
                try {
                    frameLayoutContent.setVisibility(View.VISIBLE);
                    fragmentManager.beginTransaction().replace(R.id.FragmentContentAccountAdd,
                            new Fragment_Add_Account(fragmentManager, frameLayoutContent, frame_layout_content_tick)).commit();
                    contentAccount.startAnimation(animExit);
                    frameLayoutContent.startAnimation(animation);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Lỗi!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                try {
                    frameLayoutContent.setVisibility(View.VISIBLE);
                    fragmentManager.beginTransaction().replace(R.id.FragmentContentAccountAdd,
                            new Fragment_Add_Account_Saving(fragmentManager, frameLayoutContent, frame_layout_content_tick)).commit();
                    contentAccount.startAnimation(animExit);
                    frameLayoutContent.startAnimation(animation);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Lỗi!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private void Init() {
        contentAccount = (LinearLayout) findViewById(R.id.contentAccount);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        fragmentManager = getSupportFragmentManager();
        frameLayoutContent = (FrameLayout) findViewById(R.id.FragmentContentAccountAdd);
        frame_layout_content_tick = (FrameLayout) findViewById(R.id.FragmentContentAddTick);
        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        animExit = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_out);
        anim_left_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in);
        anim_right_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_out);
    }

    private void AddPager() {
        try {
            pager = (ViewPager) findViewById(R.id.view_pagerTaiKhoan);
            tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            FragmentManager manager = getSupportFragmentManager();
            PageTaiKhoanAdapter adapter = new PageTaiKhoanAdapter(manager);
            pager.setAdapter(adapter);
            tabLayout.setupWithViewPager(pager);
            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setTabsFromPagerAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getParent(), "lỗi" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Vì dùng fragment trong activity nên cần sử lý hàm này nếu không có thì bất kỳ màn hình nào cũng sẽ thoát app
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (frame_layout_content_tick.getVisibility() == View.VISIBLE) {

                frame_layout_content_tick.startAnimation(anim_right_out);
                frame_layout_content_tick.setVisibility(View.GONE);
            } else if (frameLayoutContent.getVisibility() == View.VISIBLE) {

                frameLayoutContent.startAnimation(anim_right_out);
                frameLayoutContent.setVisibility(View.GONE);
            }

            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_MENU)) {
            ThongBao("Bạn vừa bấm nút MENU!");
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
            ThongBao("Bạn vừa bấm nút VOLUME+");
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
            ThongBao("Bạn vừa bấm nút VOLUME-");
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void ThongBao(String nd) {
        Toast.makeText(getApplicationContext(), nd, Toast.LENGTH_LONG).show();
    }

    public static void edit(Context context, TK tk) {
        frameLayoutContent.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction().replace(R.id.FragmentContentAccountAdd,
                new Fragment_Add_Account(fragmentManager, frameLayoutContent, frame_layout_content_tick, tk)).commit();
        contentAccount.startAnimation(AnimationUtils.loadAnimation(context, R.anim.left_out));
        frameLayoutContent.startAnimation(AnimationUtils.loadAnimation(context, R.anim.right_in));
    }

    public static void EditAccountSaving(Context ct, SoTietKiem soTietKiem) {
        frameLayoutContent.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction().replace(R.id.FragmentContentAccountAdd,
                new Fragment_Add_Account_Saving(fragmentManager, frameLayoutContent, frame_layout_content_tick, soTietKiem)).commit();
        contentAccount.startAnimation(AnimationUtils.loadAnimation(ct, R.anim.left_out));
        frameLayoutContent.startAnimation(AnimationUtils.loadAnimation(ct, R.anim.right_in));
    }

}