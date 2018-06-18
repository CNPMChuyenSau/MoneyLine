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
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.DiaLogTypeAccount;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_EditText;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_Edit_Money;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.TK;


public class Fragment_Add_Account extends Fragment implements View.OnClickListener {
    ImageView back_button;

    Animation animExit, animation;

    FrameLayout fragmentControlThuChi, fragmentControlThuChiRight;

    FragmentManager fragmentManager;

    LinearLayout linearLayoutAddNew, linearLayoutSelectCalculator, lineaLayoutType, linearLayoutSelectAccountName, linearLayoutDelete;

    View view;

    TextView textViewEditMoney, textViewTypeAccount, textViewName;

    EditText EditTextDescription;

    SQLite database;

    TK editTaiKhoan;

    public Fragment_Add_Account(FragmentManager fragmentManager, FrameLayout fl, FrameLayout flRight) {
        fragmentControlThuChi = fl;
        this.fragmentManager = fragmentManager;
        fragmentControlThuChiRight = flRight;
    }

    public Fragment_Add_Account(FragmentManager fragmentManager, FrameLayout frameLayout, FrameLayout frameLayoutRight, TK tk) {
        fragmentControlThuChi = frameLayout;
        this.fragmentManager = fragmentManager;
        fragmentControlThuChiRight = frameLayoutRight;
        editTaiKhoan = tk;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_account, container, false);
        database = new SQLite(getContext());
        Init();
        textViewTypeAccount.setText(getString(R.string.balance));
        Events();

        if (editTaiKhoan != null) {
            textViewEditMoney.setText(editTaiKhoan.getSoTien());
            textViewName.setText(editTaiKhoan.getTenTK());
            textViewTypeAccount.setText(editTaiKhoan.getLoaiTK());
            EditTextDescription.setText(editTaiKhoan.getGhiChu());
            TextView edit = (TextView) view.findViewById(R.id.button_edit);
            edit.setText(getString(R.string.Edit));
            linearLayoutDelete.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button: {
                fragmentControlThuChi.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.right_out));
                fragmentControlThuChi.setVisibility(View.GONE);
                break;
            }

            case R.id.linear_layout_select_calculator: {
                showEditMoney();
                break;
            }

            case R.id.lnTpye: {
                DiaLogTypeAccount diaLogTypeAccount = new DiaLogTypeAccount(getContext(), textViewTypeAccount);
                diaLogTypeAccount.show();
                break;
            }

            case R.id.lnSelectAccountName: {
                fragmentControlThuChiRight.setVisibility(View.VISIBLE);
                fragmentManager
                        .beginTransaction()
                        .replace(fragmentControlThuChiRight.getId(),
                                new Fragment_EditText(fragmentControlThuChiRight, textViewName)).commit();
                fragmentControlThuChiRight.startAnimation(animation);
                fragmentControlThuChi.startAnimation(animExit);
                break;
            }

            case R.id.lnAddAndNew: {
                String name, type, money, des;
                name = textViewName.getText().toString();
                type = textViewTypeAccount.getText().toString();
                money = textViewEditMoney.getText().toString();
                des = EditTextDescription.getText().toString();
                if (name.equals("")) {
                    new CustomToast().ShowToast(getActivity(), view,
                            getString(R.string.account_empty));
                } else if (money.equals("")) {
                    new CustomToast().ShowToast(getActivity(), view,
                            getString(R.string.balance_empty));
                } else {
                    try {
                        if (editTaiKhoan == null) {
                            database.InsertTaiKhoan(new TK(name, type, money, des));
                            Notification("Thêm tài khoản thành công");
                        } else {
                            database.UpdateTK(new TK(editTaiKhoan.getID(), name, type, money, des));
                            Notification("Cập nhật thành công");
                        }
                        Fragment_Account_Tab1.re(getContext());
                    } catch (Exception e) {
                        Notification("Lỗi nhập dữ liệu");
                    }
                }

                fragmentControlThuChi.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.right_out));
                fragmentControlThuChi.setVisibility(View.GONE);
                break;
            }

            case R.id.lnDelete: {
                database.DeleteTK(editTaiKhoan);
                Notification("Xóa tài khoản thành công");
                Fragment_Account_Tab1.re(getContext());
                fragmentControlThuChi.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.right_out));
                fragmentControlThuChi.setVisibility(View.GONE);
                break;
            }
        }
    }

    private void showEditMoney() {
        fragmentControlThuChiRight.setVisibility(View.VISIBLE);
        fragmentManager
                .beginTransaction()
                .replace(fragmentControlThuChiRight.getId(),
                        new Fragment_Edit_Money(fragmentControlThuChiRight, textViewEditMoney)).commit();
        fragmentControlThuChiRight.startAnimation(animation);
        fragmentControlThuChi.startAnimation(animExit);
    }

    private void Init() {
        linearLayoutDelete = (LinearLayout) this.view.findViewById(R.id.lnDelete);
        linearLayoutSelectAccountName = (LinearLayout) this.view.findViewById(R.id.lnSelectAccountName);
        EditTextDescription = (EditText) this.view.findViewById(R.id.EditTextDescription);
        textViewName = (TextView) this.view.findViewById(R.id.txtName);
        textViewTypeAccount = (TextView) this.view.findViewById(R.id.txtTypeAccount);
        lineaLayoutType = (LinearLayout) this.view.findViewById(R.id.lnTpye);
        textViewEditMoney = (TextView) this.view.findViewById(R.id.text_view_edit_money);
        linearLayoutSelectCalculator = (LinearLayout) this.view.findViewById(R.id.linear_layout_select_calculator);
        linearLayoutAddNew = (LinearLayout) this.view.findViewById(R.id.lnAddAndNew);
        back_button = (ImageView) this.view.findViewById(R.id.back_button);
        animExit = AnimationUtils.loadAnimation(getContext(), R.anim.left_out);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
    }

    private void Events()
    {
        back_button.setOnClickListener(this);
        linearLayoutAddNew.setOnClickListener(this);
        linearLayoutSelectCalculator.setOnClickListener(this);
        lineaLayoutType.setOnClickListener(this);
        linearLayoutSelectAccountName.setOnClickListener(this);
        linearLayoutDelete.setOnClickListener(this);
    }
    private void Notification(String nd) {
        Toast.makeText(getContext(), nd, Toast.LENGTH_SHORT).show();
    }
}