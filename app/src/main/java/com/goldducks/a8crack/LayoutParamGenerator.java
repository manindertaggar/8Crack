package com.goldducks.a8crack;

import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created by root on 3/2/17.
 */

public class LayoutParamGenerator {
    public static WindowManager.LayoutParams getNewLayoutParams() {
        WindowManager.LayoutParams windowManagerLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT);
        windowManagerLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        return windowManagerLayoutParams;
    }
}
