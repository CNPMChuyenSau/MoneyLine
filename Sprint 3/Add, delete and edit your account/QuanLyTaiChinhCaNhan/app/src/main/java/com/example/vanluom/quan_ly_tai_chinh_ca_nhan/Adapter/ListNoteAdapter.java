package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.DateTime;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.Chi;

import java.util.ArrayList;

public class ListNoteAdapter extends ArrayAdapter<Chi> {
    private Context c;
    private int rs;
    private ArrayList<Chi> myList;

    public ListNoteAdapter(Context context, int resource, ArrayList<Chi> objects) {
        super(context, resource, objects);
        myList = objects;
        rs = resource;
        c = context;
    }

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
        txtAccountName = (TextView) view.findViewById(R.id.text_view_account_name);
        txtDate1 = (TextView) view.findViewById(R.id.txtDate1);
        textViewHomnay = (TextView) view.findViewById(R.id.text_view_today);
        txtweekdays1 = (TextView) view.findViewById(R.id.text_view_weekdays_2);
        Chi chi = myList.get(position);
        DateTime dateTime = new DateTime(chi.getStringNgayChi());

        if (chi.getMaLoaiMuc() == 1) {
            txtAccountName.setText(String.valueOf("Từ " + chi.getTuTaiKhoan()));
            txtType.setText(String.valueOf("Chi Cho - " + chi.getTenMuc()));
        } else {
            txtAccountName.setText(String.valueOf("Vào " + chi.getTuTaiKhoan()));
            txtType.setText(String.valueOf("Thu Từ - " + chi.getTenMuc()));
        }

        textViewMoney.setText(String.valueOf(chi.getSoTien() + "Đ"));

        txtGhiChu.setText(chi.getGhiChu());
        txtDate1.setText(String.valueOf(dateTime.getMonthOfYear() + 1 + " - " + dateTime.getYear()));
        textViewHomnay.setText(dateTime.getDayOfMonth());
        txtweekdays1.setText(dateTime.getThu());
        return view;
    }

    public void Load(ArrayList<Chi> list) {
        this.myList = list;
        notifyDataSetChanged();

    }
}
