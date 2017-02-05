package com.goldducks.a8crack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ViewManager(getApplicationContext());

        //  PermissionManager.checkForOverlayPermission(this);
        //  startService(new Intent(this, CrackServidce.class));

        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);

        startService(new Intent(this, RunningAppDetectorService.class));

        finish();
    }


}
