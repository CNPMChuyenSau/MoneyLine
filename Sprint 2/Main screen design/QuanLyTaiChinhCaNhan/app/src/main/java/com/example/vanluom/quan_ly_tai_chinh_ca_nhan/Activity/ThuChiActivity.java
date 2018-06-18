package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity;

import android.app.Activity;
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
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter.PageThuChiAdapter;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;


@SuppressWarnings({"deprecation", "StaticFieldLeak", "ResourceAsColor"})
public class ThuChiActivity extends AppCompatActivity {
    LinearLayout linearLayoutMainThuChi, linearLayoutExpenseFor;
    Animation animExit, anim;
    ImageView imgListTransaction;
    private static TextSwitcher textSwitcher;
    static Activity activity;
    FrameLayout fragmentControlThuChi;
    FrameLayout fragmentControlThuChiRight;
    FrameLayout fragmentControlThuChiInRight;
    ViewPager viewPagerThuChi;
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentManager fragmentManager;
    Animation animLeft, animRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_thu_chi);

        // Khởi tạo các đối tượng view trước khi sử dụng
        Initialization();
        AddPager();
        LoadAnimations();
        SetFactory();
        // Set text title
        setTitle("Lịch sử giao dịch");

        fragmentManager = getSupportFragmentManager();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    setTitle("Lịch sử giao dịch");
                } else {
                    setTitle("Thêm thu chi");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    // Khởi tạo các đối tượng view
    private void Initialization() {
        textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        linearLayoutExpenseFor = (LinearLayout) findViewById(R.id.lnExpenseFor);
        linearLayoutMainThuChi = (LinearLayout) findViewById(R.id.MainThuChi);
        fragmentControlThuChi = (FrameLayout) findViewById(R.id.fragmentControlThuChi);
        fragmentControlThuChiRight = (FrameLayout) findViewById(R.id.fragmentControlThuChiRight);
        fragmentControlThuChiInRight = (FrameLayout) findViewById(R.id.fragmentControlThuChiInRight);
        animExit = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top_out);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        fragmentControlThuChi.setVisibility(View.GONE);
        fragmentControlThuChiRight.setVisibility(View.GONE);
        fragmentControlThuChiInRight.setVisibility(View.GONE);
        imgListTransaction = (ImageView) findViewById(R.id.imgListTransaction);
        activity = getParent();
//        viewPager = (ViewPager) findViewById(R.id.viewPager);
        animLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in);
        animRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_out);
    }

    private void AddPager() {
        try {
            viewPagerThuChi = (ViewPager) findViewById(R.id.viewPagerThuChi);
            tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            FragmentManager manager = getSupportFragmentManager();
            PageThuChiAdapter adapter = new PageThuChiAdapter(manager, getParent(), fragmentControlThuChi,
                    fragmentControlThuChiRight, fragmentControlThuChiInRight);
            viewPagerThuChi.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPagerThuChi);
            viewPagerThuChi.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setTabsFromPagerAdapter(adapter);
        } catch (Exception e) {
            Notification("Lỗi" + e.getMessage());
        }
    }

    // Vì dùng fragment trong activity nên cần sử lý hàm này nếu không có thì bất kỳ màn hình nào cũng sẽ thoát app
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (fragmentControlThuChiInRight.getVisibility() == View.VISIBLE) {

                fragmentControlThuChiInRight.startAnimation(animExit);
                fragmentControlThuChiInRight.setVisibility(View.GONE);
            } else if (fragmentControlThuChiRight.getVisibility() == View.VISIBLE) {

                fragmentControlThuChiRight.startAnimation(animExit);
                fragmentControlThuChiRight.setVisibility(View.GONE);
            } else if (fragmentControlThuChi.getVisibility() == View.VISIBLE) {

                fragmentControlThuChi.startAnimation(animExit);
                fragmentControlThuChi.setVisibility(View.GONE);
            }
//            } else {
//                // khi main ở trạng thái ban đầu thì nhấn nút back sẽ finish app
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//                // Tạo sự kiện kết thúc app
//                Intent startMain = new Intent(Intent.ACTION_MAIN);
//                startMain.addCategory(Intent.CATEGORY_HOME);
//                startActivity(startMain);
//                finish();
//            }
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_MENU)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Hiệu ứng chữ chuyển động trên actionbar thu--->chi
    void LoadAnimations() {
        Animation in = AnimationUtils.loadAnimation(getApplicationContext(),
                android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getApplicationContext(),
                android.R.anim.slide_out_right);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);
    }

    // Hiệu ứng chữ chuyển động trên actionbar thu--->chi
    void SetFactory() {
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView myText = new TextView(getApplicationContext());
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(20);
                myText.setTextColor(R.color.background_color);
                return myText;
            }
        });
    }

    private void Notification(String notify)
    {
        Toast.makeText(ThuChiActivity.this, notify, Toast.LENGTH_SHORT).show();
    }

    public static void setTitle(String title)
    {
        textSwitcher.setText(title);
    }
}