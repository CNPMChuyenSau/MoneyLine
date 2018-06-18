
package com.vanluom.group11.quanlytaichinhcanhan.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;


public class DrawableUtils {

    public static Drawable scaleImage(Context context, Drawable image, int dstWidht, int dstHeight) {

        if ((image == null) || !(image instanceof BitmapDrawable)) {
            return image;
        }

        Bitmap b = ((BitmapDrawable) image).getBitmap();

        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, dstWidht, dstHeight, false);

        image = new BitmapDrawable(context.getResources(), bitmapResized);

        return image;

    }

    public static Drawable scaleImage(Context context, Drawable image, float scaleFactor) {

        if ((image == null) || !(image instanceof BitmapDrawable)) {
            return image;
        }

        Bitmap b = ((BitmapDrawable) image).getBitmap();

        int sizeX = Math.round(image.getIntrinsicWidth() * scaleFactor);
        int sizeY = Math.round(image.getIntrinsicHeight() * scaleFactor);

        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, sizeX, sizeY, false);

        image = new BitmapDrawable(context.getResources(), bitmapResized);

        return image;

    }
}
