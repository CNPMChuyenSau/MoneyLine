package com.vanluom.group11.quanlytaichinhcanhan.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Network utility functions
 */
public class NetworkUtils {

    public static boolean isOnline(Context context) {
        return new NetworkUtils(context).isOnline();
    }

    public NetworkUtils(Context context) {
        mContext = context;
    }

    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    /**
     * Check if device has connection
     *
     * @return true if is online otherwise false
     */
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnected();
        // isConnectedOrConnecting
    }

    public boolean isOnWiFi() {
        ConnectivityManager connManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        // check connManager.getAllNetworks()

        // deprecated as of API 23.
//        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null) return false;  // no network
        if (!networkInfo.isConnected()) return false;

        return networkInfo.getType() == ConnectivityManager.TYPE_WIFI;

    }
}
