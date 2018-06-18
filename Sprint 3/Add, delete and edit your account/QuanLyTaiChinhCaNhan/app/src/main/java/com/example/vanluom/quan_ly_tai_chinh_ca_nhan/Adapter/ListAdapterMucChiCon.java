package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.MucChiCon;

import java.util.ArrayList;
import java.util.List;


public class ListAdapterMucChiCon extends ArrayAdapter<MucChiCon> {
    private List<MucChiCon> listMucChiCon;

    public ListAdapterMucChiCon(Context context, int resource, List<MucChiCon> objects) {
        super(context, resource, objects);
        listMucChiCon = objects;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_category_child, null);
        }
        MucChiCon mucChiCon = listMucChiCon.get(position);
        TextView textViewTen, textViewGhi;
        textViewTen = (TextView) view.findViewById(R.id.textViewTen);
        textViewGhi = (TextView) view.findViewById(R.id.txtGhi);
        textViewTen.setText(mucChiCon.getTenMucChi());
        textViewGhi.setText(mucChiCon.getGhiChu());
        return view;

    }

    public void ReloadList(ArrayList<MucChiCon> a) {
        this.listMucChiCon = a;
        notifyDataSetChanged();
    }
}
