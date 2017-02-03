package com.goldducks.a8crack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity implements RotationGestureDetector.OnRotationGestureListener {
    private RotationGestureDetector mRotationDetector;
    private View activity_main;
    View guidlineView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guidlineView = findViewById(R.id.guidlineView);

        mRotationDetector = new RotationGestureDetector(this);
        activity_main = findViewById(R.id.activity_main);
        activity_main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mRotationDetector.onTouchEvent(motionEvent);
                return false;
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mRotationDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void OnRotation(RotationGestureDetector rotationDetector) {
        float angle = -rotationDetector.getAngle();
        guidlineView.setRotation(angle);
        Log.d("RotationGestureDetector", "Rotation: " + Float.toString(angle));
    }

}

//        View rotateClockwiseView, rotateAntiClockwiseView;

//        rotateClockwiseView = findViewById(R.id.rotateClockWise);
//        rotateAntiClockwiseView = findViewById(R.id.rotateAntiClockWise);
//
//        rotateClockwiseView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return false;
//            }
//        });
//
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        int height = displaymetrics.heightPixels;
//
//        guidlineView = findViewById(R.id.guidlineView);
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) guidlineView.getLayoutParams();
//        params.width = height * 2;
//        params.height = 10;
//        guidlineView.setLayoutParams(params);
//
