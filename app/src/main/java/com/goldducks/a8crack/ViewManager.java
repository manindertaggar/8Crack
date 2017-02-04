package com.goldducks.a8crack;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Maninder Taggar on 3/2/17.
 */

public class ViewManager {
    private static final String TAG = ViewManager.class.getCanonicalName();
    private WindowManager windowManager;
    private Context context;
    private Display display;
    private Point size = new Point();
    private int screenHeight, screenWidth;
    private static ViewManager runningInstance;
    private GuidlineView guidlineView;
    private HandleView handleView;

    public ViewManager(Context context) {
        if (runningInstance != null)
            return;

        runningInstance = this;
        this.context = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        calculateScreenDimentions();
    }

    private void calculateScreenDimentions() {
        display = windowManager.getDefaultDisplay();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }


    public void addView(View view, WindowManager.LayoutParams layoutParams) {
        windowManager.addView(view, layoutParams);
    }

    public void removeView(View view) {
        windowManager.removeView(view);
    }

    public void updateViewLayout(View view, WindowManager.LayoutParams layoutParams) {
        windowManager.updateViewLayout(view, layoutParams);
    }

    public int getScreenHeight() {
        Log.d(TAG, "getScreenHeight: " + screenHeight);
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public static ViewManager getRunningInstance() {
        return runningInstance;
    }

    private void addGuildlineView() {
        WindowManager.LayoutParams windowManagerLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT);
        windowManagerLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;

        guidlineView = new GuidlineView(context);
        windowManager.addView(guidlineView.getView(), windowManagerLayoutParams);

    }


    private void addHandle() {
        WindowManager.LayoutParams windowManagerLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT);
        windowManagerLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;

        windowManagerLayoutParams.x = ViewManager.getRunningInstance().getScreenWidth() + 20;
        windowManagerLayoutParams.y = 50;
        handleView = new HandleView(context);
        windowManager.addView(handleView.getView(), windowManagerLayoutParams);

    }

    public void onConfigrationChanged() {
        calculateScreenDimentions();

        if (guidlineView != null) {
            guidlineView.onConfigrationChanged();
        }

        if (handleView != null) {
            handleView.onConfigrationChanged();
        }

    }


}
