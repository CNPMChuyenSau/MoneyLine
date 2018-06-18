
package com.vanluom.group11.quanlytaichinhcanhan.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Replacing material dialogs AlertDialogWrapper so that the changes to the dialog code is minimal
 */

public class AlertDialogWrapper {
    public AlertDialogWrapper(Context context) {
        builder = new MaterialDialog.Builder(context);
    }

    private MaterialDialog.Builder builder;

    public AlertDialogWrapper setCancelable(boolean cancelable) {
        builder.cancelable(cancelable);
        return this;
    }

    public AlertDialogWrapper setTitle(int resId) {
        builder.title(resId);
        return this;
    }

    public AlertDialogWrapper setTitle(CharSequence title) {
        builder.title(title);
        return this;
    }

    public AlertDialogWrapper setIcon(Drawable icon) {
        builder.icon(icon);
        return this;
    }

    public AlertDialogWrapper setMessage(int resId) {
        builder.content(resId);
        return this;
    }

    public AlertDialogWrapper setNegativeButton(int captionResId, MaterialDialog.SingleButtonCallback callback) {
        builder.negativeText(captionResId);
        builder.onNegative(callback);
        return this;
    }

    public AlertDialogWrapper setNegativeButton(int captionResId, final DialogInterface.OnClickListener callback) {
        builder.negativeText(captionResId);
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                callback.onClick(dialog, which.ordinal());
            }
        });
        return this;
    }

    public AlertDialogWrapper setNeutralButton(int captionResId, final DialogInterface.OnClickListener callback) {
        builder.neutralText(captionResId);
        builder.onNeutral(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                callback.onClick(dialog, which.ordinal());
            }
        });
        return this;
    }

    public AlertDialogWrapper setPositiveButton(int captionResId) {
        builder.positiveText(captionResId);
        return this;
    }

    public AlertDialogWrapper setPositiveButton(int captionResId, MaterialDialog.SingleButtonCallback callback) {
        builder.positiveText(captionResId);
        builder.onPositive(callback);
        return this;
    }

    public AlertDialogWrapper setPositiveButton(int captionResId, final DialogInterface.OnClickListener callback) {
        builder.positiveText(captionResId);
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                callback.onClick(dialog, which.ordinal());
            }
        });
        return this;
    }

    public AlertDialogWrapper onPositive(MaterialDialog.SingleButtonCallback callback) {
        builder.onPositive(callback);
        return this;
    }

    public MaterialDialog create() {
        return builder.build();
    }

    public AlertDialogWrapper setView(View view) {
        builder.customView(view, true);
        return this;
    }
}
