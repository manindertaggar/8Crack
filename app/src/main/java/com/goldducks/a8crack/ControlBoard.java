package com.goldducks.a8crack;

import android.app.ActivityManager;
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
        context.stopService(new Intent(context, CrackService.class));
    }

    public boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
