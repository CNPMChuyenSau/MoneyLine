
package com.vanluom.group11.quanlytaichinhcanhan.database;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionStatuses;

/**
 * Helper for transaction status - related issues, like colors, etc.
 * Created by Alessandro Lazzari on 08/09/2014.
 */
public class TransactionStatus {
    /**
     * @param status char of status
     * @return decode status char
     */
    public static String getStatusAsString(Context ctx, String status) {
//        if (TextUtils.isEmpty(status)) {
//            return ctx.getResources().getString(R.string.status_none);
//        } else if (Constants.TRANSACTION_STATUS_RECONCILED.equalsIgnoreCase(status)) {
//            return ctx.getResources().getString(R.string.status_reconciled);
//        } else if (Constants.TRANSACTION_STATUS_VOID.equalsIgnoreCase(status)) {
//            return ctx.getResources().getString(R.string.status_void);
//        } else if (Constants.TRANSACTION_STATUS_FOLLOWUP.equalsIgnoreCase(status)) {
//            return ctx.getResources().getString(R.string.status_follow_up);
//        } else if (Constants.TRANSACTION_STATUS_DUPLICATE.equalsIgnoreCase(status)) {
//            return ctx.getResources().getString(R.string.status_duplicate);
//        }
//        return "";

        String result;
        TransactionStatuses transactionStatus = TransactionStatuses.get(status);
        if (transactionStatus == null) return null;

        switch (transactionStatus) {
            case NONE:
                result = ctx.getResources().getString(R.string.status_none);
                break;
            case RECONCILED:
                result = ctx.getResources().getString(R.string.status_reconciled);
                break;
            case VOID:
                result = ctx.getResources().getString(R.string.status_void);
                break;
            case FOLLOWUP:
                result = ctx.getResources().getString(R.string.status_follow_up);
                break;
            case DUPLICATE:
                result = ctx.getResources().getString(R.string.status_duplicate);
                break;
            default:
                result = "";
                break;
        }
        return result;
    }

    public static int getBackgroundColorFromStatus(Context ctx, String status) {
//        if (Constants.TRANSACTION_STATUS_RECONCILED.equalsIgnoreCase(status)) {
//            return ctx.getResources().getColor(R.color.material_green_500);
//        } else if (Constants.TRANSACTION_STATUS_VOID.equalsIgnoreCase(status)) {
//            return ctx.getResources().getColor(R.color.material_red_500);
//        } else if (Constants.TRANSACTION_STATUS_FOLLOWUP.equalsIgnoreCase(status)) {
//            return ctx.getResources().getColor(R.color.material_orange_500);
//        } else if (Constants.TRANSACTION_STATUS_DUPLICATE.equalsIgnoreCase(status)) {
//            return ctx.getResources().getColor(R.color.material_indigo_500);
//        } else {
//            return ctx.getResources().getColor(R.color.material_grey_500);
//        }

        int result;
        TransactionStatuses txStatus = TransactionStatuses.get(status);
        if (txStatus == null) {
            txStatus = TransactionStatuses.NONE;
        }

        switch (txStatus) {
//            case NONE:
//                result = ctx.getResources().getString(R.string.status_none);
//                break;
            case RECONCILED:
                result = ctx.getResources().getColor(R.color.material_green_500);
                break;
            case VOID:
                result = ctx.getResources().getColor(R.color.material_red_500);
                break;
            case FOLLOWUP:
                result = ctx.getResources().getColor(R.color.material_orange_500);
                break;
            case DUPLICATE:
                result = ctx.getResources().getColor(R.color.material_indigo_500);
                break;
            default:
                result = ctx.getResources().getColor(R.color.material_grey_500);
                break;
        }
        return result;
    }
}


