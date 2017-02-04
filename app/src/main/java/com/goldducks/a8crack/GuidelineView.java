package com.goldducks.a8crack;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by Maninder Taggar on 4/2/17.
 */

public class GuidelineView implements RotationGestureDetector.OnRotationGestureListener {
    private static final String TAG = GuidelineView.class.getCanonicalName();
    private Context context;
    private View contentView;
    private View guidelineView;
    private RotationGestureDetector rotationGestureDetector;
    private WindowManager.LayoutParams windowParams;
    private Boolean isShown = false;
    private int length;
    private ViewManager viewManager = ViewManager.getRunningInstance();
    private int curruntTouchX, curruntTouchY, previousTouchX, previousTouchY;

    public GuidelineView(Context context) {
        this.context = context;
        rotationGestureDetector = new RotationGestureDetector(this);

        intializeViews();
        intializeWindowParams();
        setListeners();
        fixGuidelineViewWidth();
    }

    private void intializeWindowParams() {
        windowParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT);
        windowParams.gravity = Gravity.CENTER;
    }

    private void intializeViews() {
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_guidline, null);
        guidelineView = contentView.findViewById(R.id.guidlineView);
    }

    private void fixGuidelineViewWidth() {
        ViewGroup.LayoutParams params = guidelineView.getLayoutParams();
        length = ViewManager.getRunningInstance().getScreenWidth() * 2;
        params.width = length;
        guidelineView.setLayoutParams(params);
        Log.d(TAG, "fixGuidelineViewWidth: " + params.width);
    }

    private void setListeners() {
//        rlTouchListener.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                rotationGestureDetector.onTouchEvent(motionEvent);
//                return true;
//            }
//        });


        guidelineView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        curruntTouchX = previousTouchX = (int) motionEvent.getRawX();
                        curruntTouchY = previousTouchY = (int) motionEvent.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        curruntTouchX = (int) motionEvent.getRawX();
                        curruntTouchY = (int) motionEvent.getRawY();

                        int diffX = (curruntTouchX - previousTouchX);
                        int diffY = (curruntTouchY - previousTouchY);
                        getWindowParams().x += diffX;
                        getWindowParams().y += diffY;

//                        Log.d(TAG, "onTouch: x: " + getWindowParams().x + " y: " + getWindowParams().y);
                        Log.d(TAG, "onTouch: x: " + diffX + " y: " + diffY);
                        viewManager.updateViewLayout(getView(), getWindowParams());

                        previousTouchX = curruntTouchX;
                        previousTouchY = curruntTouchY;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void OnRotation(RotationGestureDetector rotationDetector) {
        float angle = -rotationDetector.getAngle();
        guidelineView.setRotation(angle);
    }


    public View getView() {
        return contentView;
    }

    public void onConfigrationChanged() {
        fixGuidelineViewWidth();
    }

    public WindowManager.LayoutParams getWindowParams() {
        return windowParams;
    }

    public void show() {
        contentView.setVisibility(View.VISIBLE);
    }

    public void hide() {
        contentView.setVisibility(View.GONE);
    }

    public Boolean isShown() {
        return isShown;
    }
}
