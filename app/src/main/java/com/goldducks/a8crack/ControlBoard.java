package com.goldducks.a8crack;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by root on 5/2/17.
 */

public class ControlBoard {
    private static final String TAG = ControlBoard.class.getCanonicalName();

    public static void start(Context context) {
        Log.d(TAG, "run: starting crackService");
        context.startService(new Intent(context, CrackService.class));
    }

    public static void stop(Context context) {
        Log.d(TAG, "stop: ");
        context.stopService(new Intent(context, CrackService.class));
    }

    public static boolean isServiceRunning() {
        Boolean isServiceRunning = CrackService.isServiceRunning();
        Log.d(TAG, "isServiceRunning: " + isServiceRunning);
        return isServiceRunning;
    }
}
