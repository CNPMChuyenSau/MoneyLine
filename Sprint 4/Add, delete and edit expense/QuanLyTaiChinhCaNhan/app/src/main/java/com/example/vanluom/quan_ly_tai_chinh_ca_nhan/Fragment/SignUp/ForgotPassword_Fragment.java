package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.SignUp;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity.LoginActivity;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.CustomToast;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.Utils;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R.drawable.*;

@SuppressWarnings({"deprecation", "ResourceType"})
public class ForgotPassword_Fragment extends Fragment implements
        OnClickListener {
    private static View view;

    private static EditText emailId;
    private static TextView submit, back;

    public ForgotPassword_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.forgot_password_layout, container,
                false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        emailId = (EditText) view.findViewById(R.id.register_email_invalid);
        submit = (TextView) view.findViewById(R.id.forgot_button);
        back = (TextView) view.findViewById(R.id.backToLoginBtn);
        XmlResourceParser xrp = getResources().getXml(text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            back.setTextColor(csl);
            submit.setTextColor(csl);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setListeners() {
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToLoginBtn:
                new LoginActivity().replaceLoginFragment();
                break;
            case R.id.forgot_button:
                submitButtonTask();
                break;
        }
    }

    private void submitButtonTask() {
        String getEmailId = emailId.getText().toString();
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);
        if (getEmailId.equals("") || getEmailId.length() == 0)
            new CustomToast().ShowToast(getActivity(), view,
                    "Email không được bỏ trống");
        else if (!m.find())
            new CustomToast().ShowToast(getActivity(), view,
                    "Email không chính xác");
        else
            Toast.makeText(getActivity(), "Get forgot password",
                    Toast.LENGTH_SHORT).show();
    }
}