package com.vanluom.group11.quanlytaichinhcanhan.common;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.HttpMethods;
import com.vanluom.group11.quanlytaichinhcanhan.core.MyWebChromeClient;

import java.util.HashMap;

/**
 * Used for PayPal online donations handling.
 */
public class WebViewActivity
    extends MmxBaseFragmentActivity {

    public static final String URL = "URL";
    /**
     * GET, POST
     */
    public static final String METHOD = "METHOD";
    public static final String POST_VALUES = "POST_VALUES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_web_view);
//        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        // show progress bar

        getProgressBar().setProgress(0);

        handleIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically e clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateProgress(int value) {
        getProgressBar().setProgress(value);
    }

    private ProgressBar getProgressBar() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        return progressBar;
    }

    private WebView getWebView() {
        final WebView webView = (WebView) findViewById(R.id.webView);
        if (webView == null) return null;

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);

        // appearance
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_INSET);

        // prevent opening the browser app.
        webView.setWebViewClient(new WebViewClient(){
            @Override
            //show the web page in webview but not in web browser
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl (url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                ViewGroup progressDisplay = (ViewGroup) findViewById(R.id.progressDisplay);
                progressDisplay.setVisibility(View.GONE);

//        findViewById(R.id.progressBar1).setVisibility(View.GONE);
//        findViewById(R.id.activity_main_webview).setVisibility(View.VISIBLE);

                webView.setVisibility(View.VISIBLE);
            }
        });

        MyWebChromeClient chromeWebClient = new MyWebChromeClient(this);
        webView.setWebChromeClient(chromeWebClient);

        return webView;
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent == null) return;

        HttpMethods method = (HttpMethods) intent.getSerializableExtra(METHOD);
        if (method == null) return;

        if (method.equals(HttpMethods.POST)) {
            post(intent);
        }
    }

    private void post(Intent intent) {
        if (intent == null) return;

        WebView webView = getWebView();
        if (webView == null) return;

        String url = intent.getStringExtra(URL);
        HashMap<String, String> postParams = (HashMap<String, String>) intent.getSerializableExtra(POST_VALUES);
        if (postParams != null) {
            String postDataString = "";
            for (String key : postParams.keySet()) {
                if (!TextUtils.isEmpty(postDataString)) {
                    postDataString += "&";
                }
                postDataString += key + "=" + postParams.get(key);
            }

            // send a POST request
            byte[] postData;
            try {
                postData = postDataString.getBytes("BASE64");
            } catch (Exception e) {
                postData = postDataString.getBytes();
            }

            webView.postUrl(url, postData);
        }

    }

}
