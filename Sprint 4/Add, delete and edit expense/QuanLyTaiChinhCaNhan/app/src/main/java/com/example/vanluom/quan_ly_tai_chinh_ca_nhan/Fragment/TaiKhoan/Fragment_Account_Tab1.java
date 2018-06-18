package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.TaiKhoan;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter.ListAccountAdapter;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.TK;

import java.util.ArrayList;


public class Fragment_Account_Tab1 extends Fragment {
    static ListView listViewAccount;
    View view;
    static ListAccountAdapter adapterAccountMoney;
    SearchView search;
    static SQLite database;

    public Fragment_Account_Tab1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_tab1, container, false);
        Init();
        ArrayList<TK> ar;
        try {
            database = new SQLite(getContext());

            ar = database.GetAllTK();
            adapterAccountMoney = new ListAccountAdapter(getContext(), R.layout.item_account_money, ar);
            listViewAccount.setAdapter(adapterAccountMoney);
        } catch (Exception e) {
            ThongBao("Lỗi!" + e.getMessage());
            System.out.println("=========================>" + e.getMessage());
        }
        return view;
    }

    private void ThongBao(String nd) {
        Toast.makeText(getContext(), nd, Toast.LENGTH_LONG).show();
    }

    private void Init() {
        listViewAccount = (ListView) view.findViewById(R.id.listViewAccount);
    }

    public static void re(Context ct) {
        adapterAccountMoney = new ListAccountAdapter(ct, R.layout.item_account_money, database.GetAllTK());
        listViewAccount.setAdapter(adapterAccountMoney);
    }
}
