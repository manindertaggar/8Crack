package com.goldducks.a8crack;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by root on 4/2/17.
 */

public class HandleView {
    private static final String TAG = HandleView.class.getCanonicalName();
    private Context context;
    private View contentView;
    private LinearLayout rlHandle;
    private Boolean isGuidelineShown = false;
    private ViewManager viewManager = ViewManager.getRunningInstance();
    private WindowManager.LayoutParams windowParams;
    private ImageView ivHandle;
    private int contentViewWidth, contentViewHeight;
    private ImageView ivClockwise, ivAntiClockwise;

    public HandleView(Context context) {
        Log.d(TAG, "HandleView: ");
        this.context = context;

        getViewDimentions();

        intializeViews();
        intializeWindowParams();
        setListeners();
        tingleAfterDelay();
    }

    private void tingleAfterDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                contentView.animate().setInterpolator(new CycleInterpolator(5)).setDuration(1000).rotation(8);
            }
        }, 5000);
    }

    private void intializeWindowParams() {
        Log.d(TAG, "intializeWindowParams: ");
        windowParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT);
        windowParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;

//        windowParams.x = ViewManager.getRunningInstance().getScreenWidth() - contentViewWidth;
//        windowParams.y = 50;
    }

    private void intializeViews() {
        Log.d(TAG, "intializeViews: ");
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_handle, null);
        rlHandle = (LinearLayout) contentView.findViewById(R.id.rlHandle);
        ivHandle = (ImageView) contentView.findViewById(R.id.ivHandle);
        ivClockwise = (ImageView) contentView.findViewById(R.id.ivClockwise);
        ivAntiClockwise = (ImageView) contentView.findViewById(R.id.ivAntiClockwise);
    }

    private void setListeners() {
        Log.d(TAG, "setListeners: ");
        ivHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGuidelineShown) {
                    hideGuidelines();
                } else {
                    showGuidelines();
                }
            }
        });

        ivClockwise.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                viewManager.rotateClockwiseBy(1);
                return false;
            }
        });

        ivAntiClockwise.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                viewManager.rotateAntiClockwiseBy(1);
                return false;
            }
        });
    }

    private void showGuidelines() {
        Log.d(TAG, "showGuidelines: ");

        ivClockwise.setVisibility(View.VISIBLE);
        ivAntiClockwise.setVisibility(View.VISIBLE);

//        contentView.animate().setInterpolator(new DecelerateInterpolator()).setDuration(250).rotation(-360);
        ivHandle.setImageResource(R.drawable.close);
        isGuidelineShown = true;
        viewManager.showGuidelines();
    }

    private void hideGuidelines() {
        Log.d(TAG, "hideGuidelines: ");

        ivClockwise.setVisibility(View.GONE);
        ivAntiClockwise.setVisibility(View.GONE);

//        contentView.animate().setInterpolator(new DecelerateInterpolator()).setDuration(250).rotation(360);
        ivHandle.setImageResource(R.drawable.bolt);
        isGuidelineShown = false;
        viewManager.hideGuidelines();
    }

    public View getView() {
        return contentView;
    }

    public WindowManager.LayoutParams getWindowParams() {
        return windowParams;
    }

    public void onConfigrationChanged() {
    }


    public void getViewDimentions() {
        contentViewWidth = (int) context.getResources().getDimension(R.dimen.handle_height);
        contentViewHeight = (int) context.getResources().getDimension(R.dimen.handle_height);
        Log.d(TAG, "getViewDimentions: " + contentViewWidth + " " + contentViewHeight);
    }

}
