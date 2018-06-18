
package com.vanluom.group11.quanlytaichinhcanhan.investment;

import android.app.ProgressDialog;
import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.R;

import java.util.List;

import timber.log.Timber;

/**
 * Base class for price updaters. Contains some common and useful code.
 */
public class PriceUpdaterBase
    implements ISecurityPriceUpdater {

    public PriceUpdaterBase(Context context) {
        mContext = context;
    }

    private Context mContext;
    private ProgressDialog mDialog = null;

    @Override
    public void downloadPrices(List<String> symbols) {

    }

    public Context getContext() {
        return mContext;
    }

    protected void showProgressDialog(Integer max) {
//        Context context = getContext();
        mDialog = new ProgressDialog(getContext());

        mDialog.setMessage(getContext().getString(R.string.starting_price_update));
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        if (max != null) {
            mDialog.setMax(max);
        }
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    protected void setProgress(int progress) {
        mDialog.setProgress(progress);
    }

    protected void closeProgressDialog() {
        try {
            if (mDialog != null) {
                mDialog.dismiss();
//                DialogUtils.closeProgressDialog(mDialog);
            }
        } catch (Exception e) {
            Timber.e(e, "closing binaryDialog");
        }
    }

}
