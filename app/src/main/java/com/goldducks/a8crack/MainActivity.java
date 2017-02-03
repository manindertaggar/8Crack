package com.goldducks.a8crack;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

//        View guidlineView;
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
