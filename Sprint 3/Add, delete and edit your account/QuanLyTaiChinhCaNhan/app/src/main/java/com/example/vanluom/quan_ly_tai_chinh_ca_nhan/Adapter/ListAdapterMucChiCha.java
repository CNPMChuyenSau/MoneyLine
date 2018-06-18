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
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.MucChiCha;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterMucChiCha extends ArrayAdapter<MucChiCha> {

    private List<MucChiCha> listMucChiCha;

    public ListAdapterMucChiCha(Context context, int resource, List<MucChiCha> objects) {
        super(context, resource, objects);
        listMucChiCha = objects;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_category_sub, null);
        }
        MucChiCha mucChiCha = listMucChiCha.get(position);
        TextView textViewTen, textViewGhi;
        textViewTen = (TextView) view.findViewById(R.id.textViewTen);
        textViewGhi = (TextView) view.findViewById(R.id.txtGhi);
        textViewTen.setText(mucChiCha.getTenMucChi());
        textViewGhi.setText(mucChiCha.getGhiChu());
        return view;

    }

    public void ReloadList(ArrayList<MucChiCha> a) {
        this.listMucChiCha = a;
        notifyDataSetChanged();
    }
}
