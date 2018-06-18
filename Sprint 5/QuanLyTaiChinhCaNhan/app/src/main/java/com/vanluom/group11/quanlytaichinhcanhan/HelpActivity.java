
package com.vanluom.group11.quanlytaichinhcanhan;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxFileUtils;

import timber.log.Timber;

public class HelpActivity
    extends MmxBaseFragmentActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.webview_activity);

        // adjust actionbar
        setDisplayHomeAsUpEnabled(true);

        mWebView = (WebView) findViewById(R.id.webViewContent);
        // enable javascript
        mWebView.getSettings().setJavaScriptEnabled(true);

        if (getIntent() == null) return;

        try {
            if ("android.resource".equals(getIntent().getData().getScheme())) {
                int rawId = Integer.parseInt(getIntent().getData().getPathSegments()
                        .get(getIntent().getData().getPathSegments().size() - 1));

                WebSettings settings = mWebView.getSettings();
                settings.setDefaultTextEncodingName("utf-8");
                mWebView.loadData(MmxFileUtils.getRawAsString(getApplicationContext(), rawId),
                        "text/html; charset=utf-8", null);
            } else {
                mWebView.loadUrl(getIntent().getData().toString());
            }
        } catch (Exception e) {
            Timber.e(e, "setting content of web view");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
