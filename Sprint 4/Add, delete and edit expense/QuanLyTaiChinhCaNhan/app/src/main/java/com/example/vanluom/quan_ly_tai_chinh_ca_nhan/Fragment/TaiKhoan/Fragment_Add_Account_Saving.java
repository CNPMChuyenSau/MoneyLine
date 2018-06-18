package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.TaiKhoan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.CustomToast;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.DateTime;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.DiaLogPicker.DateTimePicker;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.DiaLogPicker.SimpleDateTimePicker;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.DiaLogTypeAccount;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_EditText;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_Edit_Money;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_Tick_Period;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.SoTietKiem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Fragment_Add_Account_Saving extends Fragment implements View.OnClickListener, DateTimePicker.OnDateTimeSetListener {

    ImageView back_button;
    Animation animExit, animation;
    FrameLayout fragmentControlThuChi, fragmentControlThuChiRight;
    FragmentManager fragmentManager;
    LinearLayout lnAddAndNew, lnSelectCaculator, lnTpye, lnSelectAccountName,
            lnSelectDateSaving, lnperiod, lnSelectinterest, lnDelete;
    View view;
    TextView txteditMoney, txtTypeAccount, txtName, txtDateSaving, txtperiod;
    EditText EditTextDescription, EditTexttinterest;
    SQLite database;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    SoTietKiem editSoTietKiem;

    public Fragment_Add_Account_Saving(FragmentManager fragmentManager, FrameLayout fl, FrameLayout flRight) {
        fragmentControlThuChi = fl;
        this.fragmentManager = fragmentManager;
        fragmentControlThuChiRight = flRight;
    }

    public Fragment_Add_Account_Saving(FragmentManager fragmentManager, FrameLayout fl, FrameLayout flRight, SoTietKiem soTietKiem) {
        fragmentControlThuChi = fl;
        this.fragmentManager = fragmentManager;
        fragmentControlThuChiRight = flRight;
        editSoTietKiem = soTietKiem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_account_saving, container, false);
        database = new SQLite(getContext());

        Init();
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            txtDateSaving.setText(sdf.format(cal.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtTypeAccount.setText(getString(R.string.AccountTypeTicker4));
        txtperiod.setText(getString(R.string.SavingTermTypeTwelveMonth));
        Events();
        if (editSoTietKiem != null) {
            SetUpdate();
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button: {
                fragmentControlThuChi.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.right_out));
                fragmentControlThuChi.setVisibility(View.GONE);
            }
            break;
            case R.id.lnAddAndNew: {
                String name, type, money, des, date = "", tinterest, period;
                String ngayhan = "";
                Date dateEnd;
                int sothang = 0, nam, thang, ngay;
                name = txtName.getText().toString();
                type = txtTypeAccount.getText().toString();

                money = txteditMoney.getText().toString();
                des = EditTextDescription.getText().toString();
                date = txtDateSaving.getText().toString();
                tinterest = EditTexttinterest.getText().toString();
                try {
                    period = txtperiod.getText().toString().substring(0, 2);
                    if (period.substring(1, 2).equals(" ")) {
                        sothang = Integer.valueOf(period.substring(0, 1));
                    } else
                        sothang = Integer.valueOf(period);
                } catch (Exception e) {
                    ThongBao("Lỗi!");
                }
                try {
                    DateTime dateTime = new DateTime(date);

                    nam = dateTime.getYear();
                    thang = dateTime.getMonthOfYear() + 1;
                    if (thang + sothang > 12) {
                        nam += (thang + sothang) / 12;
                        thang = (thang + sothang) % 12;
                    } else
                        thang = thang + sothang;
                    ngayhan = nam + "-" + thang + "-" + dateTime.getDayOfMonth() + " "
                            + dateTime.getHourOfDay() + ":" + dateTime.getMinuteOfHour() + ":00";
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (name.equals("")) {
                    new CustomToast().ShowToast(getActivity(), view,
                            "Bạn chưa nhập tên tài khoản.");
                } else if (money.equals("")) {
                    new CustomToast().ShowToast(getActivity(), view,
                            "Bạn chưa nhập số tiền ban đầu");
                } else {
                    try {
                        SoTietKiem soTietKiem = new SoTietKiem();
                        soTietKiem.setTenSoTK(name);
                        soTietKiem.setGhiChu(des);
                        soTietKiem.setStringNgayGui(date);
                        soTietKiem.setSoTienBanDau(money);
                        soTietKiem.setStringNgayHetHan(ngayhan);
                        soTietKiem.setLaiXuat(Double.valueOf(tinterest));
                        soTietKiem.setStringNgayTatToan(ngayhan);
                        if (editSoTietKiem == null) {
                            database.InsertSoTietKiem(soTietKiem);
                            ThongBao("Đã thêm 1 tài khoản");
                        } else {
                            soTietKiem.setID(editSoTietKiem.getID());
                            database.UpdateSoTietKiem(soTietKiem);
                            ThongBao("Đã cập nhật 1 tài khoản");
                        }
                        Fragment_Account_Tab2.re(getContext());
                    } catch (Exception e) {
                        ThongBao("Lỗi nhập dữ liệu");
                    }
                    fragmentControlThuChi.startAnimation(AnimationUtils.loadAnimation(
                            getContext(), R.anim.right_out));
                    fragmentControlThuChi.setVisibility(View.GONE);
                }
            }
            break;
            case R.id.linear_layout_select_calculator: {
                ShowEditMoney();
            }
            break;
            case R.id.lnTpye: {
                DiaLogTypeAccount diaLogTypeAccount = new DiaLogTypeAccount(getContext(), txtTypeAccount);
                diaLogTypeAccount.show();
            }
            break;
            case R.id.lnSelectAccountName: {
                fragmentControlThuChiRight.setVisibility(View.VISIBLE);
                fragmentManager
                        .beginTransaction()
                        .replace(fragmentControlThuChiRight.getId(),
                                new Fragment_EditText(fragmentControlThuChiRight, txtName)).commit();
                fragmentControlThuChiRight.startAnimation(animation);
                fragmentControlThuChi.startAnimation(animExit);
            }
            break;
            case R.id.lnSelectDateSaving: {
                try {
                    SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                            "Set Date & Time",
                            new Date(),
                            this,
                            fragmentManager
                    );
                    simpleDateTimePicker.Show();
                } catch (Exception e) {
                    ThongBao("Lỗi!" + e.getMessage());
                }
            }
            break;
            case R.id.lnperiod: {
                fragmentControlThuChiRight.setVisibility(View.VISIBLE);
                fragmentManager
                        .beginTransaction()
                        .replace(fragmentControlThuChiRight.getId(),
                                new Fragment_Tick_Period(fragmentControlThuChiRight, txtperiod)).commit();
                fragmentControlThuChiRight.startAnimation(animation);
                fragmentControlThuChi.startAnimation(animExit);
            }
            break;
            case R.id.lnDelete: {
                database.DeleteSoTietKiem(editSoTietKiem);
                ThongBao("Đã xóa 1 sổ tiết kiệm");
                Fragment_Account_Tab2.re(getContext());
                fragmentControlThuChi.startAnimation(AnimationUtils.loadAnimation(
                        getContext(), R.anim.right_out));
                fragmentControlThuChi.setVisibility(View.GONE);
            }
        }
    }

    private void ShowEditMoney() {
        fragmentControlThuChiRight.setVisibility(View.VISIBLE);
        fragmentManager
                .beginTransaction()
                .replace(fragmentControlThuChiRight.getId(),
                        new Fragment_Edit_Money(fragmentControlThuChiRight, txteditMoney)).commit();
        fragmentControlThuChiRight.startAnimation(animation);
        fragmentControlThuChi.startAnimation(animExit);
    }

    private void Init() {
        lnDelete = (LinearLayout) this.view.findViewById(R.id.lnDelete);
        lnSelectinterest = (LinearLayout) this.view.findViewById(R.id.lnSelectinterest);
        lnperiod = (LinearLayout) this.view.findViewById(R.id.lnperiod);
        lnSelectDateSaving = (LinearLayout) this.view.findViewById(R.id.lnSelectDateSaving);
        EditTexttinterest = (EditText) this.view.findViewById(R.id.EditTexttinterest);
        txtperiod = (TextView) this.view.findViewById(R.id.txtperiod);
        txtDateSaving = (TextView) this.view.findViewById(R.id.txtDateSaving);
        lnSelectAccountName = (LinearLayout) this.view.findViewById(R.id.lnSelectAccountName);
        EditTextDescription = (EditText) this.view.findViewById(R.id.EditTextDescription);
        txtName = (TextView) this.view.findViewById(R.id.txtName);
        txtTypeAccount = (TextView) this.view.findViewById(R.id.txtTypeAccount);
        lnTpye = (LinearLayout) this.view.findViewById(R.id.lnTpye);
        txteditMoney = (TextView) this.view.findViewById(R.id.text_view_edit_money);
        lnSelectCaculator = (LinearLayout) this.view.findViewById(R.id.linear_layout_select_calculator);
        lnAddAndNew = (LinearLayout) this.view.findViewById(R.id.lnAddAndNew);
        back_button = (ImageView) this.view.findViewById(R.id.back_button);
        animExit = AnimationUtils.loadAnimation(getContext(), R.anim.left_out);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
    }

    private void Events()
    {
        back_button.setOnClickListener(this);
        lnAddAndNew.setOnClickListener(this);
        lnSelectCaculator.setOnClickListener(this);
        lnTpye.setOnClickListener(this);
        lnSelectAccountName.setOnClickListener(this);
        lnSelectDateSaving.setOnClickListener(this);
        lnperiod.setOnClickListener(this);
        lnSelectinterest.setOnClickListener(this);
        lnDelete.setOnClickListener(this);
    }

    private void ThongBao(String nd) {
        Toast.makeText(getContext(), nd, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DateTimeSet(Date date) {
        DateTime mDateTime = new DateTime(date);
        txtDateSaving.setText(mDateTime.getDateString());
    }

    private void SetUpdate() {
        txteditMoney.setText(editSoTietKiem.getSoTienBanDau());
        txtName.setText(editSoTietKiem.getTenSoTK());
        EditTextDescription.setText(editSoTietKiem.getGhiChu());
        EditTexttinterest.setText(String.valueOf(editSoTietKiem.getLaiXuat()));
        txtDateSaving.setText(editSoTietKiem.getStringNgayGui());
        int soThang = 0;
        try {
            DateTime ngaygui = new DateTime(editSoTietKiem.getStringNgayGui());
            DateTime ngayhan = new DateTime(editSoTietKiem.getStringNgayHetHan());
            int namGui, thangGui, namHet, thangHet;
            namGui = ngaygui.getYear();
            thangGui = ngaygui.getMonthOfYear();
            namHet = ngayhan.getYear();
            thangHet = ngayhan.getMonthOfYear();
            if (namHet - namGui > 0) {
                soThang += (namHet - namGui) * 12;
            }
            if (thangHet > thangGui) {
                soThang = soThang + (thangHet - thangGui);
            } else
                soThang = soThang - (thangGui - thangHet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtperiod.setText(String.valueOf(soThang + getString(R.string.Months)));
        TextView edit = (TextView) view.findViewById(R.id.button_edit);
        edit.setText(getString(R.string.Edit));
        lnDelete.setVisibility(View.VISIBLE);
    }

}