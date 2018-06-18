
package com.vanluom.group11.quanlytaichinhcanhan.about;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxFileUtils;

public class AboutCreditsFragment extends Fragment {
    private static Fragment mInstance;

    public static Fragment newInstance(int page) {
        if (mInstance == null) {
            mInstance = new AboutCreditsFragment();
        }
        return mInstance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup group,
                             Bundle saved) {
        return inflater.inflate(R.layout.about_content, group, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WebView webView = (WebView) getActivity().findViewById(R.id.about_thirdsparty_credits);

//        webView.loadData(MmxFileUtils.getRawAsString(getActivity(), R.raw.credits_thirdparty), "text/html", "UTF-8");

        // Display Unicode characters.
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");

        webView.loadData(MmxFileUtils.getRawAsString(getActivity().getApplicationContext(), R.raw.credits_thirdparty),
                "text/html; charset=utf-8", null);
    }
}
