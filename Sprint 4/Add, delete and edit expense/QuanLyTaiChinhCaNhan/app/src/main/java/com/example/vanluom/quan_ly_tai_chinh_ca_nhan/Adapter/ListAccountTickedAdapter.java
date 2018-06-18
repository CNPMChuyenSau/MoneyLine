package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.TK;

import java.util.List;


public class ListAccountTickedAdapter extends ArrayAdapter<TK> {
    private List<TK> listAccount;
    private int r;
    private String po;

    public ListAccountTickedAdapter(Context context, int resource, List<TK> objects, String po) {
        super(context, resource, objects);
        listAccount = objects;
        this.r = resource;
        this.po = po;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(r, null);
        }

        TK tk = listAccount.get(position);

        TextView txtAmount, txtExchangeAmount;

        final ImageView imgTickedAccount, imgAccount;

        txtAmount = (TextView) view.findViewById(R.id.txtAmount);

        txtExchangeAmount = (TextView) view.findViewById(R.id.text_view_change_amount);

        imgTickedAccount = (ImageView) view.findViewById(R.id.imgTickedAccount);

        imgAccount = (ImageView) view.findViewById(R.id.image_view_wallet_account);

        txtAmount.setText(tk.getTenTK());

        txtExchangeAmount.setText(tk.getSoTien());

        if (tk.getTenTK().equals(this.po)) {
            imgTickedAccount.setVisibility(View.VISIBLE);
        } else
            imgTickedAccount.setVisibility(View.GONE);

        return view;
    }
}
