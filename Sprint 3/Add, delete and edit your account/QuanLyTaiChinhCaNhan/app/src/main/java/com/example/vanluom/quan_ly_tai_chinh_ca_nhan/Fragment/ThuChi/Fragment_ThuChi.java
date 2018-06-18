package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.ThuChi;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity.ThuChiActivity;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.DateTime;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.CustomToast;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.DiaLogPicker.DateTimePicker;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.DiaLogPicker.SimpleDateTimePicker;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.DBService;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_Category;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_EditText;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_Edit_Money;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_Personal;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_Tick_Account;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.Chi;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Fragment_ThuChi extends Fragment implements View.OnClickListener, DateTimePicker.OnDateTimeSetListener {

    private static FragmentManager fragmentManager;
    FrameLayout fragmentControlThuChi, fragmentControlThuChiRight, fragmenttControlThuChiInRight;
    LinearLayout lnSelectDate, lnSelectCategory, huy12, lnSelectAccount, lnDescription, lnExpenseFor;
    LinearLayout MainThuChi;
    Animation animationthoat, animation, animationright_out, animationleft_in;
    TextView txteditMoney, txtExpenseType, txtDescription, txtAccountName, txtDate, txtExpenseFor, txtLenderTitle, txtLender, AccountFrom, textViewExpenseFor;
    ImageView imageViewGhiChu, Thoat, imghint;
    View view;
    RippleView lnAddAndNew, lnDelete, lnUpdate;
    Context ct;
    SQLite database;
    RadioButton radioButtonThu, radioButtonChi;
    Spinner spinnerMuc;
    Chi editchi;
    ArrayList<String> arsp;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public Fragment_ThuChi() { }

    public Fragment_ThuChi(Context a, FragmentManager fm, FrameLayout fl, FrameLayout flRight, FrameLayout flInRight) {
        fragmentManager = fm;
        this.fragmentControlThuChi = fl;
        this.fragmentControlThuChiRight = flInRight;
        ct = a;
        this.fragmenttControlThuChiInRight = flRight;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thu_chi, container, false);
        Init();

        DBService.ThemDuLieu(getContext());
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//            Calendar cal = Calendar.getInstance();
//            String currentDateTime = sdf.format(cal.getTime());
//            txtDate.setText(currentDateTime);
//        } catch (Exception e) { }
//
        try
        {
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            txtDate.setText(currentDateTimeString);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        database = new SQLite(getContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, arsp);
        spinnerMuc.setAdapter(adapter);
        spinnerMuc.setVisibility(View.GONE);
        radioButtonChi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    txtExpenseType.setVisibility(View.VISIBLE);
                    imghint.setVisibility(View.VISIBLE);
                    spinnerMuc.setVisibility(View.GONE);
                    ThuChiActivity.SetTitle("Chi");
                    textViewExpenseFor.setText(R.string.ExpenseFor);
                    AccountFrom.setText(R.string.AccountFrom);
                    view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
                    lnExpenseFor.setVisibility(View.VISIBLE);
                    lnExpenseFor.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.sidedown));
                } else {
                    txtExpenseType.setVisibility(View.GONE);
                    imghint.setVisibility(View.GONE);
                    spinnerMuc.setVisibility(View.VISIBLE);
                    ThuChiActivity.SetTitle("Thu");
                    textViewExpenseFor.setText(R.string.ThuTu);
                    AccountFrom.setText(R.string.ToAccount);
                    view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
                    lnExpenseFor.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.sideup));
                    lnExpenseFor.setVisibility(View.GONE);
                }
            }
        });
        txteditMoney.setOnClickListener(this);
        lnSelectDate.setOnClickListener(this);
        lnSelectCategory.setOnClickListener(this);
        lnSelectAccount.setOnClickListener(this);
        lnDescription.setOnClickListener(this);
        lnExpenseFor.setOnClickListener(this);
        lnAddAndNew.setOnClickListener(this);
        lnUpdate.setOnClickListener(this);
        lnDelete.setOnClickListener(this);
        Thoat.setOnClickListener(this);
        return view;
    }

    private void Init() {
        Thoat = (ImageView) view.findViewById(R.id.Thoat);
        huy12 = (LinearLayout) view.findViewById(R.id.huy12);
        imghint = (ImageView) view.findViewById(R.id.image_hint);
        spinnerMuc = (Spinner) view.findViewById(R.id.spinnerMuc);
        textViewExpenseFor = (TextView) view.findViewById(R.id.textViewExpenseFor);
        AccountFrom = (TextView) view.findViewById(R.id.AccountFrom);
        radioButtonChi = (RadioButton) view.findViewById(R.id.radioButtonChi);
        radioButtonThu = (RadioButton) view.findViewById(R.id.radioButtonThu);
        lnAddAndNew = (RippleView) view.findViewById(R.id.lnAddAndNew);
        txtExpenseType = (TextView) view.findViewById(R.id.txtExpenseType);
        txtDescription = (TextView) view.findViewById(R.id.txtDescription);
        txtAccountName = (TextView) view.findViewById(R.id.text_view_account_name);
        txtDate = (TextView) view.findViewById(R.id.text_view_date);
        txtExpenseFor = (TextView) view.findViewById(R.id.txtExpenseFor);
        txtLenderTitle = (TextView) view.findViewById(R.id.txtLenderTitle);
        lnExpenseFor = (LinearLayout) view.findViewById(R.id.lnExpenseFor);
        lnDescription = (LinearLayout) view.findViewById(R.id.lnDescription);
        lnSelectAccount = (LinearLayout) view.findViewById(R.id.lnSelectAccount);
        lnSelectCategory = (LinearLayout) view.findViewById(R.id.lnSelectCategory);
        lnSelectDate = (LinearLayout) view.findViewById(R.id.lnSelectDate);
        txteditMoney = (TextView) view.findViewById(R.id.text_view_edit_money);
        imageViewGhiChu = (ImageView) view.findViewById(R.id.imageViewGhiChu);
        animationthoat = AnimationUtils.loadAnimation(getContext(), R.anim.left_out);
        animationleft_in = AnimationUtils.loadAnimation(getContext(), R.anim.left_in);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
        animationright_out = AnimationUtils.loadAnimation(getContext(), R.anim.right_out);
        MainThuChi = (LinearLayout) getActivity().findViewById(R.id.MainThuChi);
        lnDelete = (RippleView) view.findViewById(R.id.lnDelete);
        lnUpdate = (RippleView) view.findViewById(R.id.lnUpdate);
        arsp = new ArrayList<>();
        arsp.add("Lương");
        arsp.add("Tiền thưởng");
        arsp.add("Trúng xổ số");
        arsp.add("Khác");
    }

    private void ThongBao(String nd) {
        Toast.makeText(getContext(), nd, Toast.LENGTH_LONG).show();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lnDescription: {
                {
                    fragmentControlThuChi.setVisibility(View.VISIBLE);
                    fragmentManager
                            .beginTransaction()
                            .replace(fragmentControlThuChi.getId(),
                                    new Fragment_EditText(fragmentControlThuChi, txtDescription)).commit();
                    fragmentControlThuChi.startAnimation(animation);
                    MainThuChi.startAnimation(animationthoat);
                }
            }
            break;
            case R.id.text_view_edit_money: {
                {
                    fragmentControlThuChi.setVisibility(View.VISIBLE);
                    fragmentManager
                            .beginTransaction()
                            .replace(fragmentControlThuChi.getId(),
                                    new Fragment_Edit_Money(fragmentControlThuChi, MainThuChi, txteditMoney)).commit();
                    fragmentControlThuChi.startAnimation(animation);
                    MainThuChi.startAnimation(animationthoat);
                }
            }
            break;
            case R.id.lnSelectDate: {
                try {
                    SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                            "Set Date/Time",
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
            case R.id.lnSelectCategory: {
                if (radioButtonChi.isChecked()) {
                    fragmentControlThuChi.setVisibility(View.VISIBLE);
                    fragmentManager
                            .beginTransaction()
                            .replace(fragmentControlThuChi.getId(),
                                    new Fragment_Category(fragmentManager, fragmentControlThuChi,
                                            fragmentControlThuChiRight, txtExpenseType)).commit();
                    fragmentControlThuChi.startAnimation(animation);
                    MainThuChi.startAnimation(animationthoat);
                }

            }
            break;
            case R.id.lnSelectAccount: {
                {
                    fragmentControlThuChi.setVisibility(View.VISIBLE);
                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_in, R.anim.left_out)
                            .replace(fragmentControlThuChi.getId(),
                                    new Fragment_Tick_Account(fragmentControlThuChi, txtAccountName, txtAccountName.getText().toString())).commit();
                    fragmentControlThuChi.startAnimation(animation);
                    MainThuChi.startAnimation(animationthoat);
                }
            }
            break;
            case R.id.lnExpenseFor: {

                {
                    fragmentControlThuChi.setVisibility(View.VISIBLE);
                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_in, R.anim.left_out)
                            .replace(fragmentControlThuChi.getId(),
                                    new Fragment_Personal(fragmentManager, fragmentControlThuChi,
                                            fragmentControlThuChiRight, txtExpenseFor)).commit();
                    fragmentControlThuChi.startAnimation(animation);
                    MainThuChi.startAnimation(animationthoat);
                }
            }
            break;
            case R.id.Thoat: {
                huy12.startAnimation(animationthoat);
                lnAddAndNew.setVisibility(View.VISIBLE);

                huy12.setVisibility(View.GONE);
            }
            break;
            case R.id.lnUpdate: {

                {
                    String mucchi, taikhoan, personal, money, chuthich, ngay;
                    int loailuc = 1;
                    if (radioButtonChi.isChecked()) {
                        mucchi = txtExpenseType.getText().toString();
                        loailuc = 1;
                    } else {
                        mucchi = spinnerMuc.getSelectedItem().toString();
                        loailuc = 2;
                    }
                    taikhoan = txtAccountName.getText().toString();
                    ngay = txtDate.getText().toString();
                    personal = txtExpenseFor.getText().toString();
                    money = txteditMoney.getText().toString();
                    chuthich = txtDescription.getText().toString();
                    Chi chihientai = database.GetAllChiHienTai();
                    Chi c = new Chi(chihientai.getID(), mucchi, loailuc, taikhoan, ngay, personal, money, chuthich);
                    database.UpdateChi(c);
                    ThongBao("Đã cập nhật thành công");
                }
            }
            break;
            case R.id.lnDelete: {
                Chi chi = database.GetAllChiHienTai();
                database.DeleteChi(chi);
                ThongBao("Đã xóa thành công");
            }
            break;
            case R.id.lnAddAndNew: {
                {
                    String mucchi, taikhoan, personal, money, chuthich, ngay;
                    int loailuc = 1;
                    if (radioButtonChi.isChecked()) {
                        mucchi = txtExpenseType.getText().toString();
                        loailuc = 1;
                    } else {
                        mucchi = spinnerMuc.getSelectedItem().toString();
                        loailuc = 2;
                    }
                    taikhoan = txtAccountName.getText().toString();
                    ngay = txtDate.getText().toString();
                    personal = txtExpenseFor.getText().toString();
                    money = txteditMoney.getText().toString();
                    chuthich = txtDescription.getText().toString();
                    try {
                        if (mucchi.equals("")) {
                            new CustomToast().ShowToast(getActivity(), view,
                                    "Bạn chưa nhập mục chi.");
                        } else if (taikhoan.equals("")) {
                            new CustomToast().ShowToast(getActivity(), view,
                                    "Bạn chưa chọn tài khoản.");
                        }
                        if (money.equals("")) {
                            new CustomToast().ShowToast(getActivity(), view,
                                    "Bạn chưa nhập số tiền!");
                        } else {
                            Chi c = new Chi(mucchi, loailuc, taikhoan, ngay, personal, money, chuthich);
                            database.InsertBangChi(c);
                            ThongBao("Đã thêm một mục ghi");
                            huy12.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.left_in));
                            lnAddAndNew.setVisibility(View.GONE);
                            huy12.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    @Override
    public void DateTimeSet(Date date) {
        DateTime mDateTime = new DateTime(date);
        Log.v("TEST_TAG", "Date and Time selected: " + mDateTime.getDateString());
        txtDate.setText(mDateTime.getDateString());
    }
}
