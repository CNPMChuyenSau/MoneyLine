package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;


// Custom dialog lựa chọn loại tài khoản trong tab TaiKhoanActivity khi thêm mới 1 tài khoản
public class DiaLogTypeAccount extends Dialog implements View.OnClickListener {

    private ImageView imageView_WalletAccount;
    private ImageView imageView_ATMAccount;
    private ImageView imageView_BankAccount;
    private ImageView imageView_SoTietKiem;
    private ImageView imageView_OtherAccount;

    private LinearLayout linearLayout_WalletAccount;
    private LinearLayout linearLayout_ATMAccount;
    private LinearLayout linearLayout_BankAccount;
    private LinearLayout linearLayout_SoTietKiem;
    private LinearLayout linearLayout_OtherAccount;

    private TextView txt;
    private TextView textView_WalletAccount;
    private TextView textView_ATMAccount;
    private TextView textView_BankAccount;
    private TextView textView_SoTietKiem;
    private TextView textView_OtherAccount;

    public DiaLogTypeAccount(Context context, TextView txtType) {
        super(context);
        txt = txtType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_ticker_account);
        Init();
        SetChecked(txt.getText().toString());
        Events();
    }

    private void SetChecked(String text) {
        imageView_WalletAccount.setVisibility(View.GONE);
        imageView_ATMAccount.setVisibility(View.GONE);
        imageView_BankAccount.setVisibility(View.GONE);
        imageView_SoTietKiem.setVisibility(View.GONE);
        imageView_OtherAccount.setVisibility(View.GONE);
        if (text.equals(textView_WalletAccount.getText().toString())) {
            imageView_WalletAccount.setVisibility(View.VISIBLE);
        } else if (text.equals(textView_ATMAccount.getText().toString())) {
            imageView_ATMAccount.setVisibility(View.VISIBLE);
        } else if (text.equals(textView_BankAccount.getText().toString())) {
            imageView_BankAccount.setVisibility(View.VISIBLE);
        } else if (text.equals(textView_SoTietKiem.getText().toString())) {
            imageView_SoTietKiem.setVisibility(View.VISIBLE);
        } else if (text.equals(textView_OtherAccount.getText().toString())) {
            imageView_OtherAccount.setVisibility(View.VISIBLE);
        }

    }

    private void Init() {
        textView_WalletAccount = (TextView) findViewById(R.id.text_view_wallet_account);
        textView_ATMAccount = (TextView) findViewById(R.id.text_view_ticked_atm_account);
        textView_BankAccount = (TextView) findViewById(R.id.text_view_ticked_bank_account);
        textView_SoTietKiem = (TextView) findViewById(R.id.ticked_account_so_tiet_kiem);
        textView_OtherAccount = (TextView) findViewById(R.id.ticked_account_other);

        linearLayout_WalletAccount = (LinearLayout) findViewById(R.id.linear_layout_type_wallet_account);
        linearLayout_ATMAccount = (LinearLayout) findViewById(R.id.linear_layout_type_atm_account);
        linearLayout_BankAccount = (LinearLayout) findViewById(R.id.linear_layout_type_bank_account);
        linearLayout_SoTietKiem = (LinearLayout) findViewById(R.id.linear_layout_type_account_so_tiet_kiem);
        linearLayout_OtherAccount = (LinearLayout) findViewById(R.id.linear_layout_type_account_other);

        imageView_WalletAccount = (ImageView) findViewById(R.id.image_view_checked_wallet);
        imageView_ATMAccount = (ImageView) findViewById(R.id.image_view_checked_atm_account);
        imageView_BankAccount = (ImageView) findViewById(R.id.image_view_checked_bank_account);
        imageView_SoTietKiem = (ImageView) findViewById(R.id.image_view_checked_so_tiet_kiem);
        imageView_OtherAccount = (ImageView) findViewById(R.id.image_view_checked_account_other);
    }

    private void Events()
    {
        linearLayout_WalletAccount.setOnClickListener(this);
        linearLayout_ATMAccount.setOnClickListener(this);
        linearLayout_BankAccount.setOnClickListener(this);
        linearLayout_SoTietKiem.setOnClickListener(this);
        linearLayout_OtherAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_type_wallet_account: {
                txt.setText(textView_WalletAccount.getText().toString());
            }
            break;
            case R.id.linear_layout_type_atm_account: {
                txt.setText(textView_ATMAccount.getText().toString());
            }
            break;
            case R.id.linear_layout_type_bank_account: {
                txt.setText(textView_BankAccount.getText().toString());
            }
            break;
            case R.id.linear_layout_type_account_so_tiet_kiem: {
                txt.setText(textView_SoTietKiem.getText().toString());
            }
            break;
            case R.id.linear_layout_type_account_other: {
                txt.setText(textView_OtherAccount.getText().toString());
            }
            break;
        }
        dismiss();
    }
}
