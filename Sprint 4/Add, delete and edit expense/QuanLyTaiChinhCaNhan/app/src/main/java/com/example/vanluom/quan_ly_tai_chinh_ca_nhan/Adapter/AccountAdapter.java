package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.TK;

import java.util.List;

public class AccountAdapter extends ArrayAdapter<TK> {
    private List<TK> mylist;

    public AccountAdapter(Context context, int resource, List<TK> objects) {
        super(context, resource, objects);
        mylist = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_account, null);
        }

        TK tk = mylist.get(position);

        return view;
    }
}
