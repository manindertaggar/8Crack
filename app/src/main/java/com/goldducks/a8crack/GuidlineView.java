package com.goldducks.a8crack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by root on 4/2/17.
 */

public class GuidlineView implements RotationGestureDetector.OnRotationGestureListener {
    private Context context;
    private View contentView;
    private View guidlineView;
    private RelativeLayout rlTouchListener;
    private RotationGestureDetector rotationGestureDetector;

    public GuidlineView(Context context) {
        this.context = context;

        rotationGestureDetector = new RotationGestureDetector(this);

        intializeViews();
        setListeners();
    }

    private void intializeViews() {
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_guidline, null);
        guidlineView = contentView.findViewById(R.id.guidlineView);
        rlTouchListener= (RelativeLayout) contentView.findViewById(R.id.rlTouchListener);
    }

    private void setListeners() {
        rlTouchListener.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rotationGestureDetector.onTouchEvent(motionEvent);
                return false;
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
}
