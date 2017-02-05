package com.goldducks.a8crack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ViewManager(getApplicationContext());

        PermissionManager.checkForOverlay(this);
        PermissionManager.checkForUsageAccess(this);

        startService(new Intent(this, RunningAppDetectorService.class));

        finish();
    }


}
