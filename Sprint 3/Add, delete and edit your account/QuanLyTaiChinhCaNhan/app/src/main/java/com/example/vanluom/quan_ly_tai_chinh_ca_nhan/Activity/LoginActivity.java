package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control.Utils;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Fragment.SignUp.Login_Fragment;
import com.example.vanluom.quan_ly_tai_chinh_ca_nhan.R;

//activity chứa 3 fragment login_Fragment SignUp_Fragment ForgotPassword_Fragment
public class LoginActivity extends AppCompatActivity {
    private static FragmentManager fragmentManager;
    ImageView imgViewClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_2);
        // Auth của Firebase

        fragmentManager = getSupportFragmentManager();
        imgViewClose = (ImageView) findViewById(R.id.close_activity);

        // Nếu Bundle trả về bằng null sẽ vào login
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new Login_Fragment(),
                            Utils.Login_Fragment).commit();
        }

        // On close icon click finish activity
        imgViewClose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        finish();
                    }
                });
    }

    // Replace Login Fragment with animation
    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new Login_Fragment(),
                        Utils.Login_Fragment).commit();

    }

    @Override
    public void onBackPressed() {
        Fragment SignUp_Fragment = fragmentManager
                .findFragmentByTag(Utils.SignUp_Fragment);
        Fragment ForgotPassword_Fragment = fragmentManager
                .findFragmentByTag(Utils.ForgotPassword_Fragment);

        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }
}