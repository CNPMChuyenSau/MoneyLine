
package com.vanluom.group11.quanlytaichinhcanhan.utils;

import android.app.ProgressDialog;

import timber.log.Timber;

/**
 * Common binaryDialog utility functions.
 */
public class DialogUtils {
    public static void closeProgressDialog(ProgressDialog progressDialog) {
        try {
            progressDialog.hide();
            progressDialog.dismiss();
        } catch (Exception ex) {
            Timber.e("error closing a binaryDialog");
        }
    }

}
