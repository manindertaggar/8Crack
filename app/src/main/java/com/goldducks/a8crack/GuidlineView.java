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
import android.widget.RelativeLayout;

/**
 * Created by Maninder Taggar on 4/2/17.
 */

public class GuidlineView implements RotationGestureDetector.OnRotationGestureListener {
    private static final String TAG = GuidlineView.class.getCanonicalName();
    private Context context;
    private View contentView;
    private View guidlineView;
    private RelativeLayout rlTouchListener;
    private RotationGestureDetector rotationGestureDetector;
    private ViewManager viewManager;
    private WindowManager.LayoutParams windowParams;

    public GuidlineView(Context context) {
        this.context = context;
        rotationGestureDetector = new RotationGestureDetector(this);
        viewManager = new ViewManager(context);

        intializeViews();
        intializeWindowParams();
        setListeners();
        fixGuidlineViewWidth();
    }

    private void intializeWindowParams() {
        windowParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT);
        windowParams.gravity = Gravity.TOP | Gravity.LEFT;
    }

    private void intializeViews() {
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_guidline, null);
        guidlineView = contentView.findViewById(R.id.guidlineView);
        rlTouchListener = (RelativeLayout) contentView.findViewById(R.id.rlTouchListener);
    }

    private void fixGuidlineViewWidth() {
        ViewGroup.LayoutParams params = guidlineView.getLayoutParams();
        params.width = ViewManager.getRunningInstance().getScreenHeight() * 2;
        guidlineView.setLayoutParams(params);
        Log.d(TAG, "fixGuidlineViewWidth: " + params.width);
    }

    private void setListeners() {
        rlTouchListener.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rotationGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    @Override
    public void OnRotation(RotationGestureDetector rotationDetector) {
        float angle = -rotationDetector.getAngle();
        guidlineView.setRotation(angle);
    }


    public View getView() {
        return contentView;
    }

    public void onConfigrationChanged() {

    }

    public WindowManager.LayoutParams getWindowParams() {
        return windowParams;
    }
}
