package com.goldducks.a8crack;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by Maninder on 09-02-2017.
 */

public class PoolPackageManager {

    public boolean isPackageInstalled(Context context) {
        try {

            PackageManager packageManager = context.getPackageManager();
            packageManager.getPackageInfo(Constants.POOL_PACKAGE_NAME, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
