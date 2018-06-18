package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity;


import android.annotation.TargetApi;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

// Hàm Main extends tabActivity :biến các Activity con thành phần tab trong Activity Main
// bao gồm ControlThuChiActivity -TaiKhoanActivity -ReportActivity -SettingActivity

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity implements View.OnClickListener {
    ImageButton ImageButtonTab1, ImageButtonTab2, ImageButtonTab4, ImageButtonTab3;
    Animation animation;
    TabHost tabHost;
    TabHost.TabSpec tabSpecThongKe;
    LinearLayout LinearLayoutTabControl, LinearLayoutTabCustomControl;
    int taBon = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar();
        setTitle("MoneyLine");
        SetTabHost();
        Init();
        Events();
        ImageButtonTab1.setImageResource(R.drawable.note_selected2);

        MyShowcaseConfig();
    }

    private void Init() {
        LinearLayoutTabCustomControl = (LinearLayout) findViewById(R.id.LinearLayoutTabCustomControl);
        LinearLayoutTabControl = (LinearLayout) findViewById(R.id.LinearLayoutTabControl);
        ImageButtonTab1 = (ImageButton) findViewById(R.id.ImageButtonTab1);
        ImageButtonTab2 = (ImageButton) findViewById(R.id.ImageButtonTab2);
        ImageButtonTab3 = (ImageButton) findViewById(R.id.ImageButtonTab3);
        ImageButtonTab4 = (ImageButton) findViewById(R.id.ImageButtonTab4);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in);
    }

    private void Events()
    {
        ImageButtonTab1.setOnClickListener(this);
        ImageButtonTab2.setOnClickListener(this);
        ImageButtonTab3.setOnClickListener(this);
        ImageButtonTab4.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        SetTabOff(taBon);
        switch (view.getId()) {
            case R.id.ImageButtonTab1: {

                ImageButtonTab1.setImageResource(R.drawable.note_selected2);
                tabHost.setCurrentTab(0);
                break;
            }
            case R.id.ImageButtonTab2: {
                ImageButtonTab2.setImageResource(R.drawable.wallet_selected2);
                tabHost.setCurrentTab(1);
                break;
            }
            case R.id.ImageButtonTab3: {
                ImageButtonTab3.setImageResource(R.drawable.pie_chart_selected2);
                tabHost.setCurrentTab(2);
                break;
            }
            case R.id.ImageButtonTab4: {
                ImageButtonTab4.setImageResource(R.drawable.setting_more_new2);
                tabHost.setCurrentTab(3);
                break;
            }
        }
        taBon = tabHost.getCurrentTab();
    }

    private void SetTabOff(int tab) {
        switch (tab) {
            case 0: {
                ImageButtonTab1.setImageResource(R.drawable.note2);
                break;
            }
            case 1: {
                ImageButtonTab2.setImageResource(R.drawable.wallet2);
                break;
            }
            case 2: {
                ImageButtonTab3.setImageResource(R.drawable.pie_chart2);
                break;
            }
            case 3: {
                ImageButtonTab4.setImageResource(R.drawable.setting_more2);
                break;
            }
        }
    }

    private void SetTabHost() {
        tabHost = getTabHost();
        TabHost.TabSpec tabSpecThuChi = tabHost.newTabSpec("thu chi");
        tabSpecThuChi.setIndicator("Thu chi");
        Intent intentThuChi = new Intent(this, ThuChiActivity.class);
        tabSpecThuChi.setContent(intentThuChi);

        TabHost.TabSpec tabSpecTaiKhoan = tabHost.newTabSpec("tai khoan");
        tabSpecTaiKhoan.setIndicator("Tài khoản");
//        Intent intentTaiKhoan = new Intent(this, TaiKhoanActivity.class);
//        tabSpecTaiKhoan.setContent(intentTaiKhoan);

        tabSpecThongKe = tabHost.newTabSpec("thong ke");
        tabSpecThongKe.setIndicator("Thống kê");
        //Intent intentThongKe = new Intent(this, ReportActivity.class);
        //tabSpecThongKe.setContent(intentThongKe);

        TabHost.TabSpec tabSpecCaiDat = tabHost.newTabSpec("cai dat");
        tabSpecCaiDat.setIndicator("cài đặt");
        //Intent intentCaiDat = new Intent(this, SettingActivity.class);
        //tabSpecCaiDat.setContent(intentCaiDat);

        tabHost.addTab(tabSpecThuChi);
        tabHost.addTab(tabSpecTaiKhoan);
        tabHost.addTab(tabSpecThongKe);
        tabHost.addTab(tabSpecCaiDat);

    }

    // hàm hướng dẫn sử dụng khi người dùng cài đặt lần đầu tiên
    private void MyShowcaseConfig() {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(300); // half second between each showcase view
        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "SHOW_ONE");
        sequence.setConfig(config);
        sequence.addSequenceItem(ImageButtonTab1, "MoneyLine",
                "Đây là Tab Thêm chi tiêu \n bạn sẽ thêm sửa xóa thu chi ở tab này", "Tiếp tục");
        sequence.addSequenceItem(ImageButtonTab2, "MoneyLine",
                "Đây là Tab Tài Khoản \n bạn có thể quản lý Tài khoản tiền của mình tại Tab này", "Tiếp tục");
        sequence.addSequenceItem(ImageButtonTab3, "MoneyLine",
                "Đây là Tab Thống kê \n bạn có thể xem thống kê theo ngày, tuần, tháng, năm.....", "Tiếp tục");
        sequence.addSequenceItem(ImageButtonTab4, "MoneyLine",
                "Đây là Tab Cài đặt \n bạn có thể Cài đặt các tính năng về : \n Tài khoản đăng nhập, đồng bộ dữ liệu, mật khẩu, nhắc nhở và thiết đặt hệ thống", "Tiếp tục");
        sequence.addSequenceItem(ThuChiActivity.imgListTransaction, "MoneyLine",
                "Buton để xem lịch sử giao dịch của bạn \n hãy nhớ xem nó mỗi cuối tháng của bạn!!!!!", "Tiếp tục");
        sequence.start();
    }
}