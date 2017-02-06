package com.goldducks.a8crack;

import android.app.ActivityManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import java.util.List;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

/**
 * Created by root on 4/2/17.
 */

public class RunningAppDetectorService extends Service {
    private static final String TAG = RunningAppDetectorService.class.getCanonicalName();
    private ActivityManager am;
    private String lastDetectedRunningApp = "";

    @Override
    public void onCreate() {
        super.onCreate();

        am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        String appOnTop = getAppRunningOnTop();
                        if (appOnTop.equals("com.miniclip.eightballpool")) {
                            if (!lastDetectedRunningApp.equals(appOnTop)) {
                                ControlBoard.start(getBaseContext());
                            }
                        } else {
                            if (!lastDetectedRunningApp.equals(appOnTop)) {
                                Log.d(TAG, "run: stopping crackService");
                                ControlBoard.stop(getBaseContext());
                            }
                        }
                        lastDetectedRunningApp = appOnTop;
                    }
                }, 20000, 1000);
        return START_STICKY;
    }


    private String getAppRunningOnTop() {
        if (Build.VERSION.SDK_INT >= 21) {
            String currentApp = null;
            UsageStatsManager usm = (UsageStatsManager) this.getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            List<UsageStats> applist = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 1000, time);
            if (applist != null && applist.size() > 0) {
                SortedMap<Long, UsageStats> mySortedMap = new TreeMap<>();
                for (UsageStats usageStats : applist) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    currentApp = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                }
            }
            return currentApp;
        } else {
            ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            String mm = (manager.getRunningTasks(1).get(0)).topActivity.getPackageName();
            return mm;
        }
    }
}
