package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

@SuppressWarnings({"WrongConstant", "deprecation"})
public class NetworkOnline {
    private Context context;

    // Class kiểm tra tình trạng mạng
    public NetworkOnline(Context paramContext) {
        this.context = paramContext;
    }

    public boolean IsCheckedNetworkOnline() {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (connectivity != null) {
            netInfo = connectivity.getActiveNetworkInfo();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
