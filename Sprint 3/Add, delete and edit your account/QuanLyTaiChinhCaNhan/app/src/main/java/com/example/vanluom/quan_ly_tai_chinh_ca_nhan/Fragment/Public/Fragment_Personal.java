package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter.ListPersonalAdapter;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.Personal;

import java.util.ArrayList;

public class Fragment_Personal extends Fragment implements View.OnClickListener {

    ImageView imageViewBack, imageViewAdd;

    Animation animExit, animation;

    FrameLayout fragmentControlThuChi, fragmentControlThuChiRight;

    FragmentManager fragmentManager;

    View view;

    SQLite database;

    ListView lv;

    ArrayList<Personal> listPerson;

    ListPersonalAdapter adapter;

    TextView text;

    public Fragment_Personal(FragmentManager fragmentManager, FrameLayout fl, FrameLayout flRight, TextView t) {
        fragmentControlThuChi = fl;
        this.fragmentManager = fragmentManager;
        fragmentControlThuChiRight = flRight;
        this.text = t;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit_personal, container, false);
        database = new SQLite(getContext());
        Init();
        try {
            listPerson = new ArrayList<>();
            listPerson = database.GetAllPersonal();
            adapter = new ListPersonalAdapter(getContext(), R.layout.item_personal, listPerson, text.getText().toString());
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    text.setText(listPerson.get(i).getTen());
                    fragmentControlThuChi.startAnimation(animExit);
                    fragmentControlThuChi.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        Events();
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.image_view_back:
            {
                fragmentControlThuChi.startAnimation(animExit);
                fragmentControlThuChi.setVisibility(View.GONE);
                break;
            }
            case R.id.image_view_add:
                //database.InsertPersonal();
                break;
        }
    }

    private void Init() {
        lv = (ListView) view.findViewById(R.id.list_view_personal);
        imageViewAdd = (ImageView) this.view.findViewById(R.id.image_view_add);
        imageViewBack = (ImageView) this.view.findViewById(R.id.image_view_back);
        animExit = AnimationUtils.loadAnimation(getContext(), R.anim.right_out);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
    }

    private void Events()
    {
        imageViewBack.setOnClickListener(this);
        imageViewAdd.setOnClickListener(this);
    }
}