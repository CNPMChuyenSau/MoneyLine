package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.andexert.library.RippleView;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.DBService;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@SuppressLint({"ValidFragment", "SimpleDateFormat"})
public class Fragment_ThuChi extends Fragment {
    FragmentManager fragmentManager;
    FrameLayout fragmentControlThuChi;
    FrameLayout fragmentControlThuChiRight;
    FrameLayout fragmentControlThuChiInRight;

    LinearLayout lnSelectDate;
    LinearLayout lnSelectCategory;
    LinearLayout huy12;
    LinearLayout lnSelectAccount;
    LinearLayout lnDescription;
    LinearLayout lnExpenseFor;
    LinearLayout MainThuChi;

    Animation animExit;
    Animation anim;
    Animation animRight;
    Animation animLeft;

    TextView editTextMoney;
    TextView txtExpenseType;
    TextView txtDescription;
    TextView txtAccountName;
    TextView txtDate;
    TextView txtExpenseFor;
    TextView txtLenderTitle;
    TextView AccountFrom;
    TextView textViewExpenseFor;

    ImageView imageViewGhiChu, Thoat, imghint;
    View view;
    RippleView lnAddAndNew, lnDelete, lnUpdate;
    Context ct;
    SQLite database;
    RadioButton radioButtonThu, radioButtonChi;
    Spinner spinnerMuc;

    ArrayList<String> arsp;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public Fragment_ThuChi() { }

    public Fragment_ThuChi(Context a, FragmentManager fm, FrameLayout fl, FrameLayout flRight, FrameLayout flInRight) {
        fragmentManager = fm;
        this.fragmentControlThuChi = fl;
        this.fragmentControlThuChiRight = flInRight;
        ct = a;
        this.fragmentControlThuChiInRight = flRight;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thu_chi, container, false);
        Init();

        DBService.ThemDuLieu(getContext());
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            txtDate.setText(sdf.format(cal.getTime()));
        } catch (Exception e) {

        }
        database = new SQLite(getContext());
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, arsp);

        spinnerMuc.setAdapter(adapter);
        spinnerMuc.setVisibility(View.GONE);
        radioButtonChi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    txtExpenseType.setVisibility(View.VISIBLE);
                    imghint.setVisibility(View.VISIBLE);
                    spinnerMuc.setVisibility(View.GONE);
                    ThuChiActivity.setTitle("Chi");
                    textViewExpenseFor.setText(R.string.ExpenseFor);
                    AccountFrom.setText(R.string.AccountFrom);
                    view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
                    lnExpenseFor.setVisibility(View.VISIBLE);
                    lnExpenseFor.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.sidedown));
                } else {
                    txtExpenseType.setVisibility(View.GONE);
                    imghint.setVisibility(View.GONE);
                    spinnerMuc.setVisibility(View.VISIBLE);

                    ThuChiActivity.setTitle(getString(R.string.TextThu));
                    textViewExpenseFor.setText(R.string.ThuTu);
                    AccountFrom.setText(R.string.ToAccount);
                    view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
                    lnExpenseFor.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.sideup));
                    lnExpenseFor.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }

    private void Init() {
        Thoat = (ImageView) view.findViewById(R.id.Thoat);
        huy12 = (LinearLayout) view.findViewById(R.id.huy12);
        imghint = (ImageView) view.findViewById(R.id.imghint);
        spinnerMuc = (Spinner) view.findViewById(R.id.spinnerMuc);
        textViewExpenseFor = (TextView) view.findViewById(R.id.textViewExpenseFor);
        AccountFrom = (TextView) view.findViewById(R.id.AccountFrom);
        radioButtonChi = (RadioButton) view.findViewById(R.id.radioButtonChi);
        radioButtonThu = (RadioButton) view.findViewById(R.id.radioButtonThu);
        lnAddAndNew = (RippleView) view.findViewById(R.id.lnAddAndNew);
        txtExpenseType = (TextView) view.findViewById(R.id.txtExpenseType);
        txtDescription = (TextView) view.findViewById(R.id.txtDescription);
        txtAccountName = (TextView) view.findViewById(R.id.txtAccountName);
        txtDate = (TextView) view.findViewById(R.id.txtDate);
        txtExpenseFor = (TextView) view.findViewById(R.id.txtExpenseFor);
        txtLenderTitle = (TextView) view.findViewById(R.id.txtLenderTitle);
        lnExpenseFor = (LinearLayout) view.findViewById(R.id.lnExpenseFor);
        lnDescription = (LinearLayout) view.findViewById(R.id.lnDescription);
        lnSelectAccount = (LinearLayout) view.findViewById(R.id.lnSelectAccount);
        lnSelectCategory = (LinearLayout) view.findViewById(R.id.lnSelectCategory);
        lnSelectDate = (LinearLayout) view.findViewById(R.id.lnSelectDate);
        editTextMoney = (TextView) view.findViewById(R.id.tvEditMoney);
        imageViewGhiChu = (ImageView) view.findViewById(R.id.imageViewGhiChu);
        animExit = AnimationUtils.loadAnimation(getContext(), R.anim.left_out);
        animLeft = AnimationUtils.loadAnimation(getContext(), R.anim.left_in);
        anim = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
        animRight = AnimationUtils.loadAnimation(getContext(), R.anim.right_out);
        MainThuChi = (LinearLayout) getActivity().findViewById(R.id.MainThuChi);
        lnDelete = (RippleView) view.findViewById(R.id.lnDelete);
        lnUpdate = (RippleView) view.findViewById(R.id.lnUpdate);
        arsp = new ArrayList<>();
        arsp.add("Lương");
        arsp.add("Tiền thưởng");
        arsp.add("Trúng xổ số");
        arsp.add("Khác");
    }
}
