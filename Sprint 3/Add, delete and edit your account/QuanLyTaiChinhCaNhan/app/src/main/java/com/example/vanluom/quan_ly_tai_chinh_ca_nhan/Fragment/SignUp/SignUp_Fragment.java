package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.SignUp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity.LoginActivity;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity.MainActivity;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.Utils;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.CustomToast;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.TKDN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressLint({"ValidFragment", "ResourceType"})
public class SignUp_Fragment extends Fragment implements OnClickListener {
    private static View view;
    private static EditText fullName, emailId, mobileNumber, location,
            password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;
    private static CheckBox terms_conditions;
    SQLite database;
    Activity activity;

    public SignUp_Fragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signup_layout, container, false);
        initViews();
        setListeners();
        database = new SQLite(getContext());
        return view;
    }

    // Initialize all views
    private void initViews() {
        fullName = (EditText) view.findViewById(R.id.fullName);
        emailId = (EditText) view.findViewById(R.id.userEmailId);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        location = (EditText) view.findViewById(R.id.location);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton = (Button) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);
        terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            login.setTextColor(csl);
            terms_conditions.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpBtn:

                // Call checkValidation method
                checkValidation();
                break;

            case R.id.already_user:

                // Replace login fragment
                new LoginActivity().replaceLoginFragment();
                break;
        }

    }

    String getFullName, getMobileNumber;

    // Check Validation Method
    private void checkValidation() {

        // Get all edittext texts
        getFullName = fullName.getText().toString();
        String getEmailId = emailId.getText().toString();
        getMobileNumber = mobileNumber.getText().toString();
        String getLocation = location.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();

        // Pattern match for email id
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        // Check if all strings are null or not
        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getLocation.equals("") || getLocation.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0)

            new CustomToast().ShowToast(getActivity(), view,
                    "All fields are required.");

            // Check if email id valid or not
        else if (!m.find())
            new CustomToast().ShowToast(getActivity(), view,
                    "Your Email Id is Invalid.");

            // Check if both password should be equal
        else if (!getConfirmPassword.equals(getPassword))
            new CustomToast().ShowToast(getActivity(), view,
                    "Both password doesn't match.");

            // Make sure user should check Terms and Conditions checkbox
        else if (!terms_conditions.isChecked())
            new CustomToast().ShowToast(getActivity(), view,
                    "Please select Terms and Conditions.");

            // Else do signup or do your stuff
        else {
            DangKy(getEmailId, getPassword);
            Toast.makeText(getActivity(), "Do SignUp.", Toast.LENGTH_SHORT)
                    .show();

        }
    }

    private void DangKy(final String email, final String password) {

        try {

            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            DangNhap(email, password);
        } catch (Exception e) {

        }
        activity.finish();
    }

    private void DangNhap(final String email, final String password) {


        Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        if (database.GetTKCount() == 0)
            database.InsertTKDN(new TKDN(getFullName, email, password, getMobileNumber, ""));
        else {
            database.UpdateTKDN(new TKDN(1, getFullName, email, password, getMobileNumber, ""));
        }

    }
}
