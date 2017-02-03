package com.goldducks.a8crack;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by root on 18/1/17.
 */

public class OnTouchListener implements View.OnTouchListener {
    View guidlineView;

    public OnTouchListener(View guildlineView) {
        this.guidlineView = guildlineView;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_MOVE:

        }
        return false;
    }
}
