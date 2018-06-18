
package com.vanluom.group11.quanlytaichinhcanhan.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.Core;

public class AboutFragment extends Fragment {
    private static Fragment mInstance;

    public static Fragment newInstance(int page) {
        if (mInstance == null) {
            mInstance = new AboutFragment();
        }
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String version;
        View view = inflater.inflate(R.layout.about_fragment, container, false);

        MmxBaseFragmentActivity activity = (MmxBaseFragmentActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Version application
        TextView txtVersion = (TextView) view.findViewById(R.id.textViewVersion);
        Core core = new Core(getActivity());
        version = core.getAppVersionName();

        txtVersion.setText(getString(R.string.version) + " " + version);

        OnClickListenerUrl clickListenerGithub = new OnClickListenerUrl();
        clickListenerGithub.setUrl("https://github.com/CNPMChuyenSau/QuanLyTaiChinhCaNhan");
        ImageView imageViewGithub = (ImageView) view.findViewById(R.id.imageViewGithub);
        imageViewGithub.setOnClickListener(clickListenerGithub);

        return view;
    }

    // implement a class to manage the opening of several url
    private class OnClickListenerUrl implements OnClickListener {
        private String mUrl;

        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(getUrl()))
                return;
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getUrl()));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        String getUrl() {
            return mUrl;
        }

        void setUrl(String mUrl) {
            this.mUrl = mUrl;
        }

    }

}
