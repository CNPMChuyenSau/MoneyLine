package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.TaiKhoan;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter.ListAccountSavingAdapter;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.SoTietKiem;

import java.util.ArrayList;


public class Fragment_Account_Tab2 extends Fragment {
    static ListView lv;
    ArrayList<SoTietKiem> ar;
    View view;
    static SQLite database;
    static ListAccountSavingAdapter accountSavingAdapter;

    public Fragment_Account_Tab2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_tab2, container, false);
        Init();
        try {
            database = new SQLite(getContext());

            ar = new ArrayList<>();

            ar = database.GetAllSoTietKiem();
            accountSavingAdapter = new ListAccountSavingAdapter(getContext(), R.layout.item_account_saving, ar);
            lv.setAdapter(accountSavingAdapter);
        } catch (Exception e) {
            ThongBao(e.getMessage());

        }

        return view;
    }

    private void Init() {
        lv = (ListView) view.findViewById(R.id.listViewAccountSaving);
    }

    private void ThongBao(String nd) {
        Toast.makeText(getContext(), nd, Toast.LENGTH_LONG).show();
    }

    public static void re(Context ct) {
        accountSavingAdapter = new ListAccountSavingAdapter(ct, R.layout.item_account_saving, database.GetAllSoTietKiem());
        lv.setAdapter(accountSavingAdapter);
    }
}
