package com.goldducks.a8crack;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;

public class CrackService extends Service {
    private static final String TAG = CrackService.class.getCanonicalName();
    ViewManager viewManager;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        viewManager = new ViewManager(getApplicationContext());
        viewManager.addViews();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        viewManager.onConfigrationChanged();
    }
}
