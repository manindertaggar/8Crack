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
import android.widget.TextView;

/**
 * Created by Maninder Taggar on 4/2/17.
 */

public class GuidelineView implements RotationGestureDetector.OnRotationGestureListener {
    private static final String TAG = GuidelineView.class.getCanonicalName();
    private Context context;
    private View contentView;
    private View guidelineView;
    private RelativeLayout rlTouchListener;
    private RotationGestureDetector rotationGestureDetector;
    private WindowManager.LayoutParams windowParams;
    private Boolean isShown = false;
    private Boolean isLongPressed = false;
    private int length;
    private TextView tvIncrement, tvDecrement;

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
        guidelineView = contentView.findViewById(R.id.guidlineView);
        rlTouchListener = (RelativeLayout) contentView.findViewById(R.id.rlTouchListener);
        tvIncrement = (TextView) contentView.findViewById(R.id.tvIncrement);
        tvDecrement = (TextView) contentView.findViewById(R.id.tvDecrement);
    }

    private void fixGuidelineViewWidth() {
        ViewGroup.LayoutParams params = guidelineView.getLayoutParams();
        length = ViewManager.getRunningInstance().getScreenWidth() * 2;
        params.width = length;
        guidelineView.setLayoutParams(params);
        Log.d(TAG, "fixGuidelineViewWidth: " + params.width);
    }

    private void setListeners() {
        rlTouchListener.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rotationGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });


        tvIncrement.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                guidelineView.animate().setDuration(0).rotationBy(1);
                return false;
            }
        });
        tvDecrement.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                guidelineView.animate().setDuration(0).rotationBy(-1);
                return false;
            }
        });

        guidelineView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        guidelineView.setX(motionEvent.getRawX() - 100 - length / 4);
                        guidelineView.setY(motionEvent.getRawY() - 100);
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
