package com.goldducks.a8crack;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by Maninder Taggar on 3/2/17.
 */

public class PermissionManager {

    private static final String TAG = PermissionManager.class.getCanonicalName();


    public static void askForOverlay(Activity activity) {
        if (hasOverlay(activity)) {
            return;
        }
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, 1);
    }

    public static boolean hasOverlay(Activity activity) {
        Log.d(TAG, "checkForOverlay: ");
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(activity);
        }
        return true;
    }


    public static void askUsageAccess(Activity activity) {
        if (hasUsageAccess(activity))
            return;

        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        activity.startActivity(intent);
    }


    public static boolean hasUsageAccess(Activity activity) {
        Log.d(TAG, "checkForUsageAccess: ");
        AppOpsManager appOps = (AppOpsManager) activity.getSystemService(activity.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), activity.getPackageName());
        if (mode != AppOpsManager.MODE_ALLOWED) {
            return false;
        }
        return true;
    }
}
