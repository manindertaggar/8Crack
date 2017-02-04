package com.goldducks.a8crack;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
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
    private GuidelineView guidelineView;
    private HandleView handleView;

    public ViewManager(Context context) {
        if (runningInstance != null)
            return;

        runningInstance = this;
        this.context = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        intializeViews();
        calculateScreenDimentions();

    }

    private void intializeViews() {
        handleView = new HandleView(context);
        guidelineView = new GuidelineView(context);
    }

    public void addViews() {
        addGuidelineView();
        addHandleView();
    }

    private void calculateScreenDimentions() {
        display = windowManager.getDefaultDisplay();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
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

    private void addHandleView() {
        windowManager.addView(handleView.getView(), handleView.getWindowParams());
    }

    private void addGuidelineView() {
        windowManager.addView(guidelineView.getView(), guidelineView.getWindowParams());
        guidelineView.hide();
    }


    public void onConfigrationChanged() {
        calculateScreenDimentions();

        if (guidelineView != null) {
            guidelineView.onConfigrationChanged();
            updateViewLayout(guidelineView.getView(), guidelineView.getWindowParams());
        }

        if (handleView != null) {
            handleView.onConfigrationChanged();
            updateViewLayout(handleView.getView(), handleView.getWindowParams());
        }

    }

    public void showGuidelines() {
        if (guidelineView == null) {
            Log.w(TAG, "showGuidelines: guidlinesView is null ");
            return;
        }
        guidelineView.show();
    }

    public void hideGuidelines() {
        if (guidelineView == null) {
            Log.w(TAG, "hideGuidelines: guidlinesView is null ");
            return;
        }
        guidelineView.hide();
    }
}
