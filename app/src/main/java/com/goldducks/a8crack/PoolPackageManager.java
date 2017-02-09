package com.goldducks.a8crack;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by Maninder on 09-02-2017.
 */

public class PoolPackageManager {

    public static boolean isInstalled(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getPackageInfo(Constants.POOL_PACKAGE_NAME, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean openApplication(Context context) {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(Constants.POOL_PACKAGE_NAME);
        if (launchIntent != null) {
            context.startActivity(launchIntent);
            return true;
        }
        return false;
    }

    public static void openInPlayerStore(Context context) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Constants.POOL_PACKAGE_NAME)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + Constants.POOL_PACKAGE_NAME)));
        }
    }

}

