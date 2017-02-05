package com.goldducks.a8crack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PermissionManager.checkForOverlay(this);
        PermissionManager.checkForUsageAccess(this);

        startService(new Intent(this, RunningAppDetectorService.class));
//        startService(new Intent(this, CrackService.class));

        finish();
    }


}
