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

    private static final String TAG = PermissionManager.class.getCanonicalName()
            ;

    public static void checkForOverlay(Activity activity) {
        Log.d(TAG, "checkForOverlay: ");
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(activity)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.getPackageName()));
                activity.startActivityForResult(intent, 1);
            }
        }
    }

    public static void checkForUsageAccess(Activity activity) {
        Log.d(TAG, "checkForUsageAccess: ");
        AppOpsManager appOps = (AppOpsManager) activity.getSystemService(activity.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), activity.getPackageName());
        if (mode != AppOpsManager.MODE_ALLOWED) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            activity.startActivity(intent);
        }
    }
}
