package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity.TaiKhoanActivity;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.SoTietKiem;

import java.util.List;


public class ListAccountSavingAdapter extends ArrayAdapter<SoTietKiem> {
    private List<SoTietKiem> myListSoTietKiem;

    public ListAccountSavingAdapter(Context context, int resource, List<SoTietKiem> objects) {
        super(context, resource, objects);
        myListSoTietKiem = objects;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_account_saving, null);
        }

        final SoTietKiem soTietKiem = myListSoTietKiem.get(position);

        TextView txtAccountName, txtAmount, txtT, txtExchangeAmount;

        ImageView imgEditAccount;

        txtAccountName = (TextView) view.findViewById(R.id.text_view_account_name);
        txtT = (TextView) view.findViewById(R.id.txtT);
        txtAmount = (TextView) view.findViewById(R.id.txtAmount);
        txtExchangeAmount = (TextView) view.findViewById(R.id.text_view_change_amount);

        imgEditAccount = (ImageView) view.findViewById(R.id.image_view_edit_account);

        txtAccountName.setText(soTietKiem.getTenSoTK());
        txtAmount.setText(soTietKiem.getSoTienBanDau());
        txtExchangeAmount.setText(soTietKiem.getStringNgayGui());
        txtT.setText(String.valueOf(soTietKiem.getLaiXuat() + "%"));

        imgEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaiKhoanActivity.EditAccountSaving(getContext(), soTietKiem);
            }
        });

        return view;
    }


}
