package com.vanluom.group11.quanlytaichinhcanhan.core;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.vanluom.group11.quanlytaichinhcanhan.common.WebViewActivity;

/**
 * Chrome web client customization.
 */
public class MyWebChromeClient extends WebChromeClient {

    public MyWebChromeClient(WebViewActivity parent) {
        this.MyActivity = parent;
    }

    private final WebViewActivity MyActivity;

    public void onProgressChanged(WebView view, int progress)
    {
        //Make the bar disappear after URL is loaded, and changes string to Loading...
//        MyActivity.setTitle("Loading...");
//        MyActivity.setProgress(progress * 100); //Make the bar disappear after URL is loaded
        MyActivity.updateProgress(progress);

//        // Return the app name after finish loading
//        if(progress == 100)
//            MyActivity.setTitle(R.string.loading);
    }

}
