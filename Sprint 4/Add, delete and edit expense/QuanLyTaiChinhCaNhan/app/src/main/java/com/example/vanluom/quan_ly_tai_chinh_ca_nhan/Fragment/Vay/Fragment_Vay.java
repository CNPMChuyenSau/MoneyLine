package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Vay;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.DateTime;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.DiaLogPicker.DateTimePicker;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.DiaLogPicker.SimpleDateTimePicker;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_Category;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_EditText;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_Edit_Money;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public.Fragment_Tick_Account;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;

import java.util.Date;


public class Fragment_Vay extends Fragment implements View.OnClickListener, DateTimePicker.OnDateTimeSetListener {

    FragmentManager fragmentManager;
    FrameLayout fragmentControlThuChi,fragmentControlThuChiRight,fragmenttControlThuChiInRight;
    LinearLayout lnSelectDate,lnSelectCategory,MainThuChi,lnSelectAccount,lnDescription;
    Animation animationthoat,animation ,animationright_out,animationleft_in;
    TextView txteditMoney,txtExpenseType, txtDescription, txtAccountName, txtDate ,txtExpenseFor ,txtLenderTitle, txtLender;
    ImageView imageViewGhiChu,imgAdd;
    View view;
    Context ct;

   public Fragment_Vay(){ }

    public Fragment_Vay(Context a, FragmentManager fm, FrameLayout fl, FrameLayout flRight, FrameLayout flInRight){
        this.fragmentManager=fm;
        this.fragmentControlThuChi=fl;
        this.fragmentControlThuChiRight=flInRight;
        ct=a;
        this.fragmenttControlThuChiInRight=flRight;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view= inflater.inflate(R.layout.fragment_vay, container, false);
        Init();


        txteditMoney.setOnClickListener(this);
        lnSelectDate.setOnClickListener(this);
        lnSelectCategory.setOnClickListener(this);
        lnSelectAccount.setOnClickListener(this);
        lnDescription.setOnClickListener(this);
        return view;
    }
    private void Init(){
      txtExpenseType=(TextView)view.findViewById(R.id.txtExpenseType);
        txtDescription=(TextView)view.findViewById(R.id.txtDescription);
        txtAccountName=(TextView)view.findViewById(R.id.text_view_account_name);
        txtDate=(TextView)view.findViewById(R.id.text_view_date);
        txtExpenseFor=(TextView)view.findViewById(R.id.txtExpenseFor);
        txtLenderTitle=(TextView)view.findViewById(R.id.txtLenderTitle);
        lnDescription=(LinearLayout)view.findViewById(R.id.lnDescription);

        lnSelectAccount=(LinearLayout)view.findViewById(R.id.lnSelectAccount) ;
        lnSelectCategory=(LinearLayout)view.findViewById(R.id.lnSelectCategory);
        lnSelectDate=(LinearLayout)view.findViewById(R.id.lnSelectDate);
        txteditMoney=(TextView)view.findViewById(R.id.text_view_edit_money);
        imageViewGhiChu=(ImageView)view.findViewById(R.id.imageViewGhiChu);
        animationthoat = AnimationUtils.loadAnimation(getContext(), R.anim.left_out);
        animationleft_in = AnimationUtils.loadAnimation(getContext(), R.anim.left_in);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
        animationright_out= AnimationUtils.loadAnimation(getContext(), R.anim.right_out);
        MainThuChi=(LinearLayout)getActivity().findViewById(R.id.MainThuChi);
    }
    private void ThongBao(String nd){
        Toast.makeText(getContext(),nd,Toast.LENGTH_LONG).show();
    }
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lnDescription: {
                {
                    fragmentControlThuChi.setVisibility(View.VISIBLE);
                    fragmentManager
                            .beginTransaction()
                            .replace(fragmentControlThuChi.getId(),
                      new Fragment_EditText(fragmentControlThuChi,txtDescription)).commit();
                    fragmentControlThuChi.startAnimation(animation);
                    MainThuChi.startAnimation(animationthoat);
                } }break;
            case R.id.text_view_edit_money: {
                {
                    fragmentControlThuChi.setVisibility(View.VISIBLE);
                    fragmentManager
                            .beginTransaction()
                            .replace(fragmentControlThuChi.getId(),
                            new Fragment_Edit_Money(fragmentControlThuChi,txteditMoney)).commit();
                    fragmentControlThuChi.startAnimation(animation);
                    MainThuChi.startAnimation(animationthoat);
                } }break;
            case R.id.lnSelectDate:{
                try{
                    SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                            "Set Date & Time",
                            new Date(),
                            this,
                            fragmentManager
                    );
                    simpleDateTimePicker.Show();
                }catch (Exception e){
                  ThongBao("Lỗi!"+ e.getMessage().toString());
                }

            }break;
            case R.id.lnSelectCategory:{
                {
                    fragmentControlThuChi.setVisibility(View.VISIBLE);
                    fragmentManager
                            .beginTransaction()
                            .replace(fragmentControlThuChi.getId(),
                            new Fragment_Category(fragmentManager,fragmentControlThuChi,
                                    fragmentControlThuChiRight,txtExpenseType)).commit();
                    fragmentControlThuChi.startAnimation(animation);
                    MainThuChi.startAnimation(animationthoat);
                }}break;
            case R.id.lnSelectAccount:{
                {
                    fragmentControlThuChi.setVisibility(View.VISIBLE);
                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_in, R.anim.left_out)
                            .replace(fragmentControlThuChi.getId(),
                            new Fragment_Tick_Account(fragmentControlThuChi,txtAccountName,txtAccountName.getText().toString())).commit();
                    fragmentControlThuChi.startAnimation(animation);
                    MainThuChi.startAnimation(animationthoat);
                }}break;
        }
    }

    @Override
    public void DateTimeSet(Date date) {
        DateTime mDateTime = new DateTime(date);
        Log.v("TEST_TAG","Date and Time selected: " + mDateTime.getDateString());
        txtDate.setText(mDateTime.getDateString());
    }
}
