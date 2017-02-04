package com.goldducks.a8crack;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by root on 4/2/17.
 */

public class HandleView {
    private Context context;
    private View contentView;
    private RelativeLayout rlHandle;
    private Boolean isGuidelineShown = false;
    private ViewManager viewManager=ViewManager.getRunningInstance();
    private WindowManager.LayoutParams windowParams;
    private ImageView ivHandle;
    private int contentViewWidth, contentViewHeight;

    public HandleView(Context context) {
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
        windowParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT);
        windowParams.gravity = Gravity.TOP | Gravity.LEFT;

        windowParams.x = ViewManager.getRunningInstance().getScreenWidth() - contentViewWidth;
        windowParams.y = 50;
    }

    private void intializeViews() {
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_handle, null);
        rlHandle = (RelativeLayout) contentView.findViewById(R.id.rlHandle);
        ivHandle = (ImageView) contentView.findViewById(R.id.ivHandle);
    }

    private void setListeners() {
        rlHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGuidelineShown) {
                    hideGuidelines();
                } else {
                    showGuidelines();
                }
            }
        });
    }

    private void showGuidelines() {
        contentView.animate().setInterpolator(new CycleInterpolator(1)).setDuration(250).rotationY(360);
        ivHandle.setImageResource(R.drawable.close);
        isGuidelineShown = true;
        viewManager.showGuidelines();
    }

    private void hideGuidelines() {
        contentView.animate().setInterpolator(new CycleInterpolator(1)).setDuration(250).rotationY(360);
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
        windowParams.x = ViewManager.getRunningInstance().getScreenWidth() - contentViewWidth;
        windowParams.y = 50;
    }

    public void getViewDimentions() {
        contentViewWidth = (int) context.getResources().getDimension(R.dimen.handle_width);
        contentViewHeight = (int) context.getResources().getDimension(R.dimen.handle_height);
    }

}
