package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Adapter.ExpandableListAdapter;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.MucChiCha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment_Category extends Fragment implements View.OnClickListener {
    ImageView editChuThich;
    ImageView btnBack, imageViewAdd;
    Animation animationExit, animation;
    FrameLayout fragmentControlThuChi, fragmentControlThuChiRight;
    FragmentManager fragmentManager;
    View view;

    private  ExpandableListView expandableListView;
    private  ExpandableListAdapter adapter;
    SQLite database;
    ArrayList<ArrayList<String>> MapList;
    ArrayList<Integer> idGroup;
    ArrayList<String> listChild, listGroup;
    ArrayList<MucChiCha> listMucCha;
    TextView text;

    public Fragment_Category(FragmentManager fragmentManager, FrameLayout fl, FrameLayout flRight, TextView t) {
        fragmentControlThuChi = fl;
        this.fragmentManager = fragmentManager;
        fragmentControlThuChiRight = flRight;
        this.text = t;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.simple_expandable_listview);
        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);
        database = new SQLite(getContext());
        idGroup = new ArrayList<>();
        listChild = new ArrayList<>();
        listGroup = new ArrayList<>();
        MapList = new ArrayList<>();

        listMucCha = database.GetAllMucChiCha();
        listGroup = database.GetAllTenMucChiCha();
        idGroup = database.GetAllIDMucChiCha();
        try {
            listChild = database.GetAllTenMucChiConInCha(database.GetMucChiCha(1).getID());
            ThongBao(listGroup.get(1) + " huy" + listChild.get(1));
        } catch (Exception e) {
            ThongBao(e.getMessage());

        }

        setItems();
        setListener();
        AnhXa();
        btnBack.setOnClickListener(this);
        imageViewAdd.setOnClickListener(this);
        return view;
    }

    void setItems() {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (int i = 0; i < listGroup.size(); i++) {
            hashMap.put(listGroup.get(i), database.GetAllTenMucChiConInCha(idGroup.get(i)));
        }
        adapter = new ExpandableListAdapter(getContext(), listGroup, hashMap);
        expandableListView.setAdapter(adapter);
    }

    void setListener() {
        // This listener will Show toast on group click
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {
                return false;
            }
        });

        // This listener will expand one group at one time
        // You can remove this listener for expanding all groups
        expandableListView
                .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                    // Default position
                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup)
                            expandableListView.collapseGroup(previousGroup);
                        previousGroup = groupPosition;
                    }
                });

        // This listener will Show toast on child click
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
                text.setText(adapter.getChild(groupPos, childPos).toString());
                ImageView imgCheckedIncomeCategory = (ImageView) view.findViewById(R.id.imgCheckedIncomeCatagory);
                imgCheckedIncomeCategory.setVisibility(View.VISIBLE);
                fragmentControlThuChi.startAnimation(animationExit);
                fragmentControlThuChi.setVisibility(View.GONE);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnBack) {
            fragmentControlThuChi.startAnimation(animationExit);
            fragmentControlThuChi.setVisibility(View.GONE);

        } else if (view == imageViewAdd) {
            fragmentControlThuChiRight.setVisibility(View.VISIBLE);
            fragmentManager.beginTransaction().replace(fragmentControlThuChiRight.getId(),
                    new Fragment_InsertSubCategory(fragmentManager, fragmentControlThuChiRight)).commit();
            fragmentControlThuChiRight.startAnimation(animation);

        }
    }

    private void AnhXa() {
        imageViewAdd = (ImageView) this.view.findViewById(R.id.image_view_add);
        btnBack = (ImageView) this.view.findViewById(R.id.button_back);
        animationExit = AnimationUtils.loadAnimation(getContext(), R.anim.right_out);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
    }

    private void ThongBao(String nd) {
        Toast.makeText(getContext(), nd, Toast.LENGTH_LONG).show();
    }
}