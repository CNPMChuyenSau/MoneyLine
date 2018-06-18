package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter.ListAccountTickedAdapter;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.TK;

import java.util.ArrayList;

public class Fragment_Tick_Account extends Fragment {
    ListView listViewAccount;
    View view;
    ArrayList<TK> ar;
    ListAccountTickedAdapter adapterAccountMoney;
    FrameLayout fragmentControlThuChi;
    ImageView btnBack;
    String po;
    TextView txtAccountName;

    public Fragment_Tick_Account(FrameLayout fl, TextView txt, String po) {
        this.fragmentControlThuChi = fl;
        this.txtAccountName = txt;
        this.po = po;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tick_account, container, false);
        listViewAccount = (ListView) view.findViewById(R.id.listViewTickAccount);
        ar = new ArrayList<>();
        try {

            SQLite database = new SQLite(getContext());

            ar = database.GetAllTK();
            SetList();
            btnBack = (ImageView) view.findViewById(R.id.button_back);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentControlThuChi.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.right_out));
                    fragmentControlThuChi.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            ThongBao("Lỗi!" + e.getMessage());
        }

        return view;
    }


    private void SetList() {
        adapterAccountMoney = new ListAccountTickedAdapter(getContext(), R.layout.item_account_money_ticked, ar, po);
        listViewAccount.setAdapter(adapterAccountMoney);
        listViewAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                txtAccountName.setText(ar.get(i).getTenTK());
                view.findViewById(R.id.imgTickedAccount).setVisibility(View.VISIBLE);
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.phongto));
                fragmentControlThuChi.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.right_out));
                fragmentControlThuChi.setVisibility(View.GONE);

            }
        });
    }

    private void ThongBao(String nd) {
        Toast.makeText(getContext(), nd, Toast.LENGTH_LONG).show();
    }
}
