package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter;

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
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.TK;

import java.util.ArrayList;
import java.util.List;

public class ListAccountAdapter extends ArrayAdapter<TK> {
    private List<TK> myListAccount;

    public ListAccountAdapter(Context context, int resource, List<TK> objects) {
        super(context, resource, objects);
        myListAccount = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_account_money, null);
        }

        final TK tk = myListAccount.get(position);

        TextView txtAmount, txtExchangeAmount;
        ImageView imgEditAccount, imgAccount;

        txtAmount = (TextView) view.findViewById(R.id.txtAmount);
        txtExchangeAmount = (TextView) view.findViewById(R.id.text_view_change_amount);

        imgEditAccount = (ImageView) view.findViewById(R.id.image_view_edit_account);
        imgAccount = (ImageView) view.findViewById(R.id.image_view_wallet_account);

        txtAmount.setText(tk.getTenTK());
        txtExchangeAmount.setText(tk.getSoTien());

        imgEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaiKhoanActivity.edit(getContext(), tk);
            }
        });

        return view;
    }

    public void Reload(ArrayList<TK> t) {
        this.myListAccount = t;
        notifyDataSetChanged();
    }
}
