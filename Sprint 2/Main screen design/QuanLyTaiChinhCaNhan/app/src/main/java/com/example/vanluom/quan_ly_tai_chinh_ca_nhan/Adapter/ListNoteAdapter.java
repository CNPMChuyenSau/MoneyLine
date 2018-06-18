package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.DateTime;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.model.Chi;

import java.util.ArrayList;

public class ListNoteAdapter extends ArrayAdapter<Chi> {
    private Context c;
    private int rs;
    private ArrayList<Chi> Mylist;

    public ListNoteAdapter(Context context, int resource, ArrayList<Chi> objects) {
        super(context, resource, objects);
        Mylist = objects;
        rs = resource;
        c = context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(c);
            view = inflater.inflate(rs, null);
        }
        TextView txtType, txtGhiChu, textViewMoney, txtAccountName, txtDate1, textViewHomnay, txtweekdays1;
        txtType = (TextView) view.findViewById(R.id.txtType);
        txtGhiChu = (TextView) view.findViewById(R.id.txtGhiChu);
        textViewMoney = (TextView) view.findViewById(R.id.textViewMoney);
        txtAccountName = (TextView) view.findViewById(R.id.txtAccountName);
        txtDate1 = (TextView) view.findViewById(R.id.txtDate1);
        textViewHomnay = (TextView) view.findViewById(R.id.textViewHomnay);
        txtweekdays1 = (TextView) view.findViewById(R.id.txtweekdays1);
        Chi chi = Mylist.get(position);
        DateTime dateTime = new DateTime(chi.getStringNgayChi());

        if (chi.getMaLoaiMuc() == 1) {
            txtAccountName.setText("Từ " + chi.getTuTaiKhoan());
            txtType.setText("Chi Cho - " + chi.getTenMuc());
        } else {
            txtAccountName.setText("Vào " + chi.getTuTaiKhoan());
            txtType.setText("Thu Từ - " + chi.getTenMuc());
        }

        textViewMoney.setText(chi.getSoTien() + "Đ");

        txtGhiChu.setText(chi.getGhiChu());
        txtDate1.setText(String.valueOf(dateTime.getMonthOfYear() + 1) + " - " + String.valueOf(dateTime.getYear()));
        textViewHomnay.setText(String.valueOf(dateTime.getDayOfMonth()));
        txtweekdays1.setText(dateTime.getThu());
        return view;
    }

    public void load(ArrayList<Chi> list) {
        this.Mylist = list;
        notifyDataSetChanged();

    }
}
