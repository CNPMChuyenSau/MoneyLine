package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;


public class Fragment_InsertSubCategory extends Fragment implements View.OnClickListener {
    FrameLayout fragmentControlThuChiRight;
    FragmentManager fragmentManager;
    View view;
    Animation animationExit, animation;
    TextView btnCancel, btnSave;

    public Fragment_InsertSubCategory(FragmentManager frm, FrameLayout fl) {
        fragmentControlThuChiRight = fl;
        this.fragmentManager = frm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_category_sub, container, false);
        AnhXa();
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == btnSave) {
            ThongBao("Bạn đã tạo mới một mục");
            fragmentControlThuChiRight.startAnimation(animationExit);
            fragmentControlThuChiRight.setVisibility(View.GONE);
        } else if (view == btnCancel) {
            fragmentControlThuChiRight.startAnimation(animationExit);
            fragmentControlThuChiRight.setVisibility(View.GONE);
        }
    }

    private void AnhXa() {
        btnCancel = (TextView) view.findViewById(R.id.btnCancel);
        btnSave = (TextView) view.findViewById(R.id.button_save);
        animationExit = AnimationUtils.loadAnimation(getContext(), R.anim.right_out);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
    }

    private void ThongBao(String thongBao)
    {
        Toast.makeText(getContext(), thongBao, Toast.LENGTH_SHORT).show();
    }
}