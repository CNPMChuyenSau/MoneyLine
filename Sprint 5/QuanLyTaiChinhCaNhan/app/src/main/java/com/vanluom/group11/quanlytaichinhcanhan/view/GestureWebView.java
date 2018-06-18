
package com.vanluom.group11.quanlytaichinhcanhan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * WebView with gesture listener. Allows handling custom gestures like long-press, double-tap, etc.
 */
public class GestureWebView
    extends WebView {

    public GestureWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

//        this.context = context;
        this.gestureDetector = initializeGestureDetector(context);
    }

    //private Context context;
    private GestureDetector gestureDetector;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private GestureDetector initializeGestureDetector(Context context) {
        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
            public boolean onDown(MotionEvent event) {
                return true;
            }

            public void onLongPress(MotionEvent event) {
                // todo Trigger context menu
                Log.d("test", "test");
            }
        };

        GestureDetector detector = new GestureDetector(context, listener);
        return detector;
    }
}
