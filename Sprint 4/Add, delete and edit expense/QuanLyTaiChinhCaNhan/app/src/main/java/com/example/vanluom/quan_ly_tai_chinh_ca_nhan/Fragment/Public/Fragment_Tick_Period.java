package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.Public;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;

public class Fragment_Tick_Period extends Fragment implements View.OnClickListener {
    ImageView btnBack;
    Animation animationExit, animation;
    FrameLayout fragmentControlThuChi;
    LinearLayout ln24Month, lnOneMonth, lnThreeMonth, lnTwelveMonth, lnSixMonth, lnOther, lnOtherValue;
    ImageView imgOneMonth, imgThreeMonth, imgSixMonth, img24Month, imgOther, imgTwelveMonth;
    View view;
    TextView text;
    EditText txtOtherValue;

    public Fragment_Tick_Period(FrameLayout fl, TextView t) {
        fragmentControlThuChi = fl;
        this.text = t;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.select_term_type, container, false);
        Init();
        setChecked(text.getText().toString());
        Events();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back: {
                fragmentControlThuChi.startAnimation(animationExit);
                fragmentControlThuChi.setVisibility(View.GONE);
            }
            break;
            case R.id.lnOneMonth: {
                text.setText(getString(R.string.SavingTermTypeOneMonth));
                fragmentControlThuChi.startAnimation(animationExit);
                fragmentControlThuChi.setVisibility(View.GONE);
            }
            break;
            case R.id.lnThreeMonth: {
                text.setText(getString(R.string.SavingTermTypeThreeMonth));
                fragmentControlThuChi.startAnimation(animationExit);
                fragmentControlThuChi.setVisibility(View.GONE);
            }
            break;
            case R.id.lnSixMonth: {
                text.setText(getString(R.string.SavingTermTypeSixMonth));
                fragmentControlThuChi.startAnimation(animationExit);
                fragmentControlThuChi.setVisibility(View.GONE);
            }
            break;
            case R.id.lnTwelveMonth: {
                text.setText(getString(R.string.SavingTermTypeTwelveMonth));
                fragmentControlThuChi.startAnimation(animationExit);
                fragmentControlThuChi.setVisibility(View.GONE);
            }
            break;
            case R.id.ln24Month: {
                text.setText(getString(R.string.ln24Month));
                fragmentControlThuChi.startAnimation(animationExit);
                fragmentControlThuChi.setVisibility(View.GONE);
            }
            break;
            case R.id.lnOther: {
                setUnchecked(text.getText().toString());
                imgOther.setVisibility(View.VISIBLE);
                if (lnOtherValue.getVisibility() == View.GONE) {
                    lnOtherValue.setVisibility(View.VISIBLE);
                    lnOtherValue.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.sidedown));
                } else {
                    text.setText(String.valueOf(txtOtherValue.getText().toString() + " " + getString(R.string.Months)));
                    fragmentControlThuChi.startAnimation(animationExit);
                    fragmentControlThuChi.setVisibility(View.GONE);
                }


            }
            break;
            case R.id.lnOtherValue: {
                text.setText(String.valueOf(txtOtherValue.getText().toString() + " " + getString(R.string.Months)));
                fragmentControlThuChi.startAnimation(animationExit);
                fragmentControlThuChi.setVisibility(View.GONE);

            }
            break;
        }
    }

    public void setChecked(String t) {
        if (t.equals(getString(R.string.SavingTermTypeOneMonth))) {
            imgOneMonth.setVisibility(View.VISIBLE);
        } else if (t.equals(getString(R.string.SavingTermTypeThreeMonth))) {
            imgThreeMonth.setVisibility(View.VISIBLE);
        } else if (t.equals(getString(R.string.SavingTermTypeSixMonth))) {
            imgSixMonth.setVisibility(View.VISIBLE);
        } else if (t.equals(getString(R.string.SavingTermTypeTwelveMonth))) {
            imgTwelveMonth.setVisibility(View.VISIBLE);
        } else if (t.equals(getString(R.string.ln24Month))) {
            img24Month.setVisibility(View.VISIBLE);
        } else if (!t.equals("")) {
            imgOther.setVisibility(View.VISIBLE);
        }
    }

    public void setUnchecked(String t) {
        if (t.equals(getString(R.string.SavingTermTypeOneMonth))) {
            imgOneMonth.setVisibility(View.GONE);
        } else if (t.equals(getString(R.string.SavingTermTypeThreeMonth))) {
            imgThreeMonth.setVisibility(View.GONE);
        } else if (t.equals(getString(R.string.SavingTermTypeSixMonth))) {
            imgSixMonth.setVisibility(View.GONE);
        } else if (t.equals(getString(R.string.SavingTermTypeTwelveMonth))) {
            imgTwelveMonth.setVisibility(View.GONE);
        } else if (t.equals(getString(R.string.ln24Month))) {
            img24Month.setVisibility(View.GONE);
        } else if (t.equals(getString(R.string.SavingTermTypeOther))) {
            imgOther.setVisibility(View.GONE);
        }
    }

    private void Init() {
        txtOtherValue = (EditText) view.findViewById(R.id.txtOtherValue);
        lnOtherValue = (LinearLayout) view.findViewById(R.id.lnOtherValue);
        lnOneMonth = (LinearLayout) view.findViewById(R.id.lnOneMonth);
        lnThreeMonth = (LinearLayout) view.findViewById(R.id.lnThreeMonth);
        lnSixMonth = (LinearLayout) view.findViewById(R.id.lnSixMonth);
        lnTwelveMonth = (LinearLayout) view.findViewById(R.id.lnTwelveMonth);
        ln24Month = (LinearLayout) view.findViewById(R.id.ln24Month);
        lnOther = (LinearLayout) view.findViewById(R.id.lnOther);
        imgOneMonth = (ImageView) view.findViewById(R.id.imgOneMonth);
        imgThreeMonth = (ImageView) view.findViewById(R.id.imgThreeMonth);
        imgSixMonth = (ImageView) view.findViewById(R.id.imgSixMonth);
        imgTwelveMonth = (ImageView) view.findViewById(R.id.imgTwelveMonth);
        img24Month = (ImageView) view.findViewById(R.id.img24Month);
        imgOther = (ImageView) view.findViewById(R.id.imgOther);
        btnBack = (ImageView) this.view.findViewById(R.id.button_back);
        animationExit = AnimationUtils.loadAnimation(getContext(), R.anim.right_out);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
    }

    private void Events()
    {
        btnBack.setOnClickListener(this);
        lnOneMonth.setOnClickListener(this);
        lnThreeMonth.setOnClickListener(this);
        lnSixMonth.setOnClickListener(this);
        lnTwelveMonth.setOnClickListener(this);
        ln24Month.setOnClickListener(this);
        lnOther.setOnClickListener(this);
        lnOtherValue.setOnClickListener(this);
    }
}