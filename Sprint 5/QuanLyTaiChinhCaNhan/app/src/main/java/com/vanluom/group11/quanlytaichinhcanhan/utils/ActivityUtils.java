
package com.vanluom.group11.quanlytaichinhcanhan.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Surface;

public class ActivityUtils {

    /**
     * Returns current device orientation.
     * @param activity Activity from which to get the current orientation information.
     * @return Code indicating the current device orientation.
     */
    public static int forceCurrentOrientation(Activity activity) {
        int prevOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        if (activity != null) {
            prevOrientation = activity.getRequestedOrientation(); // update current position

            if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if (activity.getWindowManager().getDefaultDisplay().getRotation() == Surface.ROTATION_0 ||
                    activity.getWindowManager().getDefaultDisplay().getRotation() == Surface.ROTATION_90) {
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                }
            } else if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
            }
        }
        return prevOrientation;
    }

    /**
     * Sets the device orientation for the activity.
     * @param activity Activity to which to apply the orientation.
     * @param orientation Code for orientation.
     */
    public static void restoreOrientation(Activity activity, int orientation) {
        if (activity != null) {
            activity.setRequestedOrientation(orientation);
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }
    }
}
