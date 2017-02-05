package com.goldducks.a8crack;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
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
    private View guideStick;
    private int guidelineCurruntTouchX, guidelineCurruntTouchY, guidelinePreviousTouchX, guidelinePreviousTouchY;
    private int contentViewCurruntTouchY, contentViewPreviousTouchY;

    public GuidelineView(Context context) {
        Log.d(TAG, "GuidelineView: ");
        this.context = context;
        rotationGestureDetector = new RotationGestureDetector(this);

        intializeViews();
        intializeWindowParams();
        setListeners();
        fixGuidelineViewWidth();
    }

    private void intializeWindowParams() {
        Log.d(TAG, "intializeWindowParams: ");
        windowParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT);
        windowParams.gravity = Gravity.CENTER;
    }

    private void intializeViews() {
        Log.d(TAG, "intializeViews: ");
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_guideline, null);
        guidelineView = contentView.findViewById(R.id.guidlineView);
        guideStick = contentView.findViewById(R.id.guideStick);
    }

    private void fixGuidelineViewWidth() {
//
//        ViewGroup.LayoutParams params = guidelineView.getLayoutParams();
//        length = ViewManager.getRunningInstance().getScreenWidth() * 2;
//        params.width = length;
//        guidelineView.setLayoutParams(params);
//        Log.d(TAG, "fixGuidelineViewWidth: " + params.width);
    }

    private void setListeners() {
        Log.d(TAG, "setListeners: ");

        contentView.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {

                            case MotionEvent.ACTION_DOWN:
                                contentViewCurruntTouchY = contentViewPreviousTouchY = (int) motionEvent.getRawY();
                                guideStick.setBackgroundColor(Color.RED);
                                break;
                            case MotionEvent.ACTION_MOVE:
                                contentViewCurruntTouchY = (int) motionEvent.getRawY();

                                float diffY = (contentViewCurruntTouchY - contentViewPreviousTouchY) / 8f;
                                guidelineView.animate().setDuration(0).rotationBy(diffY);

                                contentViewPreviousTouchY = contentViewCurruntTouchY;
                                break;
                            case MotionEvent.ACTION_CANCEL:
                            case MotionEvent.ACTION_UP:
                                guideStick.setBackgroundResource(R.drawable.dotted_line);
                                break;
                        }
                        return false;
                    }
                }
        );

        guidelineView.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        //if (rotationGestureDetector.onTouchEvent(motionEvent)) {
                        switch (motionEvent.getAction()) {

                            case MotionEvent.ACTION_DOWN:
                                guidelineCurruntTouchX = guidelinePreviousTouchX = (int) motionEvent.getRawX();
                                guidelineCurruntTouchY = guidelinePreviousTouchY = (int) motionEvent.getRawY();
                                guideStick.setBackgroundColor(Color.GREEN);
                                break;
                            case MotionEvent.ACTION_MOVE:
                                guidelineCurruntTouchX = (int) motionEvent.getRawX();
                                guidelineCurruntTouchY = (int) motionEvent.getRawY();

                                int diffX = (guidelineCurruntTouchX - guidelinePreviousTouchX);
                                int diffY = (guidelineCurruntTouchY - guidelinePreviousTouchY);

                                guidelineView.setX(guidelineView.getX() + diffX);
                                guidelineView.setY(guidelineView.getY() + diffY);

                                guidelinePreviousTouchX = guidelineCurruntTouchX;
                                guidelinePreviousTouchY = guidelineCurruntTouchY;
                                break;
                            case MotionEvent.ACTION_CANCEL:
                            case MotionEvent.ACTION_UP:
                                guideStick.setBackgroundResource(R.drawable.dotted_line);
                                break;
                        }
                        return false;
                    }
                }

        );

    }

    @Override
    public void OnRotation(RotationGestureDetector rotationDetector) {
        Log.d(TAG, "OnRotation: ");
        float angle = -rotationDetector.getAngle();
        guidelineView.setRotation(angle);
    }

    public void rotateAnticlockwiseBy(float r) {
        Log.d(TAG, "rotateAnticlockwiseBy: " + r);
        guidelineView.animate().setDuration(0).rotationBy(-r);
    }

    public void rotateClockwiseBy(float r) {
        Log.d(TAG, "rotateClockwiseBy: " + r);
        guidelineView.animate().setDuration(0).rotationBy(r);
    }

    public View getView() {
        return contentView;
    }

    public void onConfigrationChanged() {
        Log.d(TAG, "onConfigrationChanged: ");
        fixGuidelineViewWidth();
    }

    public WindowManager.LayoutParams getWindowParams() {
        return windowParams;
    }

    public void show() {
        Log.d(TAG, "show: ");
        contentView.setVisibility(View.VISIBLE);
    }

    public void hide() {
        Log.d(TAG, "hide: ");
        contentView.setVisibility(View.GONE);
    }

    public Boolean isShown() {
        Log.d(TAG, "isShown: " + isShown);
        return isShown;
    }
}
