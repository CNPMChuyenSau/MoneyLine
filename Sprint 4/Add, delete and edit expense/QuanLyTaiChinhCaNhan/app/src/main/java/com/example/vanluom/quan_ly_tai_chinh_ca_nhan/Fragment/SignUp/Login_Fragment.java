package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.SignUp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity.MainActivity;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.NetworkOnline;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.Utils;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Custom.CustomToast;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Database.SQLite;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Model.TKDN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ResourceType")
public class Login_Fragment extends Fragment implements View.OnClickListener {

    private static View view;

    private static EditText emailInvalid, password;
    private static com.andexert.library.RippleView loginButton, buttonAnonymous;
    private static TextView forgotPassword, signUp;
    private static CheckBox checkBox_ShowHidePassword;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    SQLite database;
    LinearLayout keyCode, linearLayoutLogin;
    ImageView imgPasscode1, imgPasscode2, imgPasscode3, imgPasscode4;
    com.andexert.library.RippleView btnKey1, oke, btnKey2, btnKey3, btnKey4, btnKey5, btnKey6, btnKey7, btnKey9,
            btnKey8, btnKey0;
    com.andexert.library.RippleView rippleView_Button_KeyBack;
    String keyCancel = "";
    TextView textView_ForgotPassword;
    boolean isCheckLogin = true;


    public Login_Fragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.login_layout, container, false);
        database = new SQLite(getContext());
        keyCode = (LinearLayout) view.findViewById(R.id.keycode);
        linearLayoutLogin = (LinearLayout) view.findViewById(R.id.loginlayout);

        initViews();
        Init();
        Events();
        TKDN tk;


        try {
            tk = database.getTKDN(1);
            String luu = tk.getCode();
            if (luu.equals("")) {
                linearLayoutLogin.setVisibility(View.VISIBLE);
                keyCode.setVisibility(View.GONE);
                isCheckLogin = true;
            } else {
                isCheckLogin = false;
                linearLayoutLogin.setVisibility(View.GONE);
                keyCode.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        SetListeners();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    private void Init() {
        textView_ForgotPassword = (TextView) view.findViewById(R.id.forget_password);
        imgPasscode1 = (ImageView) view.findViewById(R.id.imgPassCode1);
        imgPasscode2 = (ImageView) view.findViewById(R.id.imgPassCode2);
        imgPasscode3 = (ImageView) view.findViewById(R.id.imgPassCode3);
        imgPasscode4 = (ImageView) view.findViewById(R.id.imgPassCode4);
        btnKey1 = (com.andexert.library.RippleView) view.findViewById(R.id.btnKey1);
        btnKey2 = (com.andexert.library.RippleView) view.findViewById(R.id.btnKey2);
        btnKey3 = (com.andexert.library.RippleView) view.findViewById(R.id.btnKey3);
        btnKey4 = (com.andexert.library.RippleView) view.findViewById(R.id.btnKey4);
        btnKey5 = (com.andexert.library.RippleView) view.findViewById(R.id.btnKey5);
        btnKey6 = (com.andexert.library.RippleView) view.findViewById(R.id.btnKey6);
        btnKey7 = (com.andexert.library.RippleView) view.findViewById(R.id.btnKey7);
        btnKey8 = (com.andexert.library.RippleView) view.findViewById(R.id.btnKey8);
        btnKey9 = (com.andexert.library.RippleView) view.findViewById(R.id.btnKey9);
        btnKey0 = (com.andexert.library.RippleView) view.findViewById(R.id.btnKey0);
        rippleView_Button_KeyBack = (com.andexert.library.RippleView) view.findViewById(R.id.btnKeyBack);
        oke = (com.andexert.library.RippleView) view.findViewById(R.id.oke);

    }

    private void Events()
    {
        btnKey0.setOnClickListener(this);
        btnKey1.setOnClickListener(this);
        btnKey2.setOnClickListener(this);
        btnKey3.setOnClickListener(this);
        btnKey4.setOnClickListener(this);
        btnKey5.setOnClickListener(this);
        btnKey6.setOnClickListener(this);
        btnKey7.setOnClickListener(this);
        btnKey9.setOnClickListener(this);
        btnKey8.setOnClickListener(this);
        rippleView_Button_KeyBack.setOnClickListener(this);
        textView_ForgotPassword.setOnClickListener(this);
        oke.setOnClickListener(this);
        buttonAnonymous.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_password: {

                {
                    linearLayoutLogin.setVisibility(View.VISIBLE);
                    keyCode.setVisibility(View.GONE);
                    try {

                        emailInvalid.setText("");
                        password.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

            case R.id.btnKey0: {
                CheckPass("0");
                break;
            }

            case R.id.btnKey1: {
                CheckPass("1");
                break;
            }

            case R.id.btnKey2: {
                CheckPass("2");
                break;
            }

            case R.id.btnKey3: {
                CheckPass("3");
                break;
            }

            case R.id.btnKey4: {
                CheckPass("4");
                break;
            }

            case R.id.btnKey5: {
                CheckPass("5");
                break;
            }

            case R.id.btnKey6: {
                CheckPass("6");
                break;
            }

            case R.id.btnKey7: {
                CheckPass("7");
                break;
            }

            case R.id.btnKey8: {
                CheckPass("8");
                break;
            }

            case R.id.btnKey9: {
                CheckPass("9");
                break;
            }

            case R.id.btnKeyBack: {
                KeyBack();
                break;
            }

            case R.id.oke: {
                if (keyCancel.length() == 4) {
                    Toast.makeText(getContext(), "Hoàn tất", Toast.LENGTH_SHORT).show();
                    SQLite sqLite = new SQLite(getContext());
                    try {
                        if (sqLite.GetTKDNCount() > 0) {
                            TKDN tkdn = sqLite.getTKDN(1);
                            if (tkdn.getCode().equals(keyCancel)) {
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();
                            }
                        } else
                            Toast.makeText(getContext(), "Tạm thời bạn không được vào", Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else
                    new CustomToast().ShowToast(getActivity(), view,
                            "Bạn chưa nhập đủ");
                break;
            }
            case R.id.loginBtn: {
                CheckedValidation();
                break;
            }
            case R.id.forgot_password: {
                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ForgotPassword_Fragment(),
                                Utils.ForgotPassword_Fragment).commit();
                break;
            }
            case R.id.createAccount: {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new SignUp_Fragment(getActivity()),
                                Utils.SignUp_Fragment).commit();
                break;
            }

            case R.id.btnLoginNotAccount: {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }


    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        buttonAnonymous = (com.andexert.library.RippleView) view.findViewById(R.id.btnLoginNotAccount);
        emailInvalid = (EditText) view.findViewById(R.id.login_emailid);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (com.andexert.library.RippleView) view.findViewById(R.id.loginBtn);
        forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        checkBox_ShowHidePassword = (CheckBox) view
                .findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            forgotPassword.setTextColor(csl);
            checkBox_ShowHidePassword.setTextColor(csl);
            signUp.setTextColor(csl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Set Listeners
    private void SetListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);
        checkBox_ShowHidePassword
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {
                        if (isChecked) {
                            checkBox_ShowHidePassword.setText(R.string.hide_pwd);
                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// Show password
                        } else {
                            checkBox_ShowHidePassword.setText(R.string.show_pwd);// change
                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }
                    }
                });
    }

    private void CheckedValidation() {
        String getEmailId = emailInvalid.getText().toString();
        String getPassword = password.getText().toString();
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().ShowToast(getActivity(), view,
                    "Enter both credentials.");

        } else if (!m.find())
            new CustomToast().ShowToast(getActivity(), view,
                    getString(R.string.email_invalid));
        else {
            NetworkOnline networkOnline = new NetworkOnline(getContext());
            if (!networkOnline.IsCheckedNetworkOnline()) {
                new CustomToast().ShowToast(getActivity(), view,
                        getString(R.string.internet));
            } else
                Login(getEmailId, getPassword);
        }
    }

    private void Login(final String email, final String password) {
        // đã xóa kết nối với firebase
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void CheckPass(String key) {
        if (keyCancel.length() == 4) {
            new CustomToast().ShowToast(getActivity(), view,
                    "Bạn đã nhập đủ! hoặc là sửa đổi hoặc là nhấn OK");
        } else {
            keyCancel += key;
            if (keyCancel.length() == 1) {
                imgPasscode1.setVisibility(View.VISIBLE);
            } else if (keyCancel.length() == 2) {
                imgPasscode2.setVisibility(View.VISIBLE);
            }
            if (keyCancel.length() == 3) {
                imgPasscode3.setVisibility(View.VISIBLE);
            }
            if (keyCancel.length() == 4) {
                imgPasscode4.setVisibility(View.VISIBLE);
            }
        }

    }

    private void KeyBack() {
        if (keyCancel.length() > 0) {
            keyCancel = keyCancel.substring(0, keyCancel.length() - 1);
            if (keyCancel.length() == 1) {
                imgPasscode2.setVisibility(View.GONE);
            } else if (keyCancel.length() == 2) {
                imgPasscode3.setVisibility(View.GONE);
            }
            if (keyCancel.length() == 3) {
                imgPasscode4.setVisibility(View.GONE);
            }
            if (keyCancel.length() == 0) {
                imgPasscode1.setVisibility(View.GONE);
            }
        }
    }
}
