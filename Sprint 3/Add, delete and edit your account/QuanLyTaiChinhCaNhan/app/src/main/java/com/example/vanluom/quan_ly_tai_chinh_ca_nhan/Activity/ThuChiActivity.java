package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter.PageThuChiAdapter;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;

@SuppressWarnings("deprecation")
public class ThuChiActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout MainThuChi, lnExpenseFor;
    Animation animationExit, animation;
    static ImageView imgListTransaction;
    static TextSwitcher textSwitcher;
    Activity activity;
    FrameLayout fragmentControlThuChi, fragmentControlThuChiRight, fragmentControlThuChiInRight;
    ViewPager pager;
    TabLayout tabLayout;

    FragmentManager fragmentManager;

    Animation animationLeftIn, animationRightOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_thu_chi);

        Init();
        AddPager();
        LoadAnimations();
        SetFactory();
        SetTitle("Thêm thu chi");

        imgListTransaction.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    SetTitle("Lịch sử giao dịch");
                } else {
                    SetTitle("Thêm thu chi");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }

    // Ánh xạ các đối tượng view
    private void Init() {
        textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        lnExpenseFor = (LinearLayout) findViewById(R.id.lnExpenseFor);
        MainThuChi = (LinearLayout) findViewById(R.id.MainThuChi);
        fragmentControlThuChi = (FrameLayout) findViewById(R.id.fragment_control_income);
        fragmentControlThuChiRight = (FrameLayout) findViewById(R.id.fragment_control_income_in_right);
        fragmentControlThuChiInRight = (FrameLayout) findViewById(R.id.fragment_control_income_right);
        animationExit = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top_out);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        fragmentControlThuChi.setVisibility(View.GONE);
        fragmentControlThuChiRight.setVisibility(View.GONE);
        fragmentControlThuChiInRight.setVisibility(View.GONE);
        imgListTransaction = (ImageView) findViewById(R.id.image_view_history_transaction);
        activity = getParent();

        animationLeftIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in);
        animationRightOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_out);
    }

    private void AddPager() {
        try {
            pager = (ViewPager) findViewById(R.id.view_pager_income);
            tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            FragmentManager manager = getSupportFragmentManager();
            PageThuChiAdapter adapter = new PageThuChiAdapter(manager, getParent(), fragmentControlThuChi,
                    fragmentControlThuChiRight, fragmentControlThuChiInRight);
            pager.setAdapter(adapter);
            tabLayout.setupWithViewPager(pager);
            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setTabsFromPagerAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_view_history_transaction:
                break;
        }
    }

    // Vì dùng fragment trong activity nên cần sử lý hàm này nếu không có thì bất kỳ màn hình nào cũng sẽ thoát app
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (fragmentControlThuChiInRight.getVisibility() == View.VISIBLE) {
                fragmentControlThuChiInRight.startAnimation(animationExit);
                fragmentControlThuChiInRight.setVisibility(View.GONE);
            } else if (fragmentControlThuChiRight.getVisibility() == View.VISIBLE) {
                fragmentControlThuChiRight.startAnimation(animationExit);
                fragmentControlThuChiRight.setVisibility(View.GONE);
            } else if (fragmentControlThuChi.getVisibility() == View.VISIBLE) {
                fragmentControlThuChi.startAnimation(animationExit);
                fragmentControlThuChi.setVisibility(View.GONE);
            } else {
                // khi main ở trạng thái ban đầu thì nhấn nút back sẽ finish app
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                // Tạo sự kiện kết thúc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_MENU)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Hiệu ứng chữ chuyển động trên actionbar thu--->chi
    private void LoadAnimations() {
        Animation in = AnimationUtils.loadAnimation(getApplicationContext(),
                android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getApplicationContext(),
                android.R.anim.slide_out_right);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);
    }

    // Hiệu ứng chữ chuyển động trên actionbar thu--->chi
    private void SetFactory() {
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView myText = new TextView(getApplicationContext());
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(20);
                myText.getResources().getColor(R.color.background_color);
                return myText;
            }
        });

    }

    public static void SetTitle(String str)
    {
        textSwitcher.setText(str);
    }
}