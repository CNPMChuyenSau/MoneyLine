
package com.vanluom.group11.quanlytaichinhcanhan.servicelayer;

import android.content.Context;

/**
 * Base class for the services
 */
public abstract class ServiceBase {

    public ServiceBase(Context context) {
//        mContext = context.getApplicationContext();
        mContext = context;
    }

    private Context mContext;

    public Context getContext() {
        return mContext;
    }
}
