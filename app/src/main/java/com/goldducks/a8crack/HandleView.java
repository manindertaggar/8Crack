package com.goldducks.a8crack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by root on 4/2/17.
 */

public class HandleView {
    private Context context;
    private View contentView;
    private RelativeLayout rlHandle;
    private Boolean isGuidlineShown = false;

    public HandleView(Context context) {
        this.context = context;
        intializeViews();
        setListeners();
    }

    private void intializeViews() {
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_handle, null);
        rlHandle = (RelativeLayout) contentView.findViewById(R.id.rlHandle);
    }

    private void setListeners() {
        rlHandle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isGuidlineShown) {
                    hideGuidelines();
                } else {
                    showGuidelines();
                }
                return false;
            }


        });
    }

    private void showGuidelines() {

    }

    private void hideGuidelines() {

    }

    public View getView() {
        return contentView;
    }

    public void onConfigrationChanged() {

    }
}
