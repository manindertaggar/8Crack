package com.goldducks.a8crack;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private TextView tvToggleService;
    private View layoutStart, layoutShowInPlaystore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intializeLayouts();
        setLayouts();
//     tvToggleService = (TextView) findViewById(R.id.tvToggleService);
//        if (ControlBoard.isServiceRunning()) {
//            tvToggleService.setText("Stop");
//        } else {
//            tvToggleService.setText("Start");
//        }
//
//        tvToggleService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle();
//            }
//
//
//        });

//        PermissionManager.checkForOverlay(this);
//        PermissionManager.checkForUsageAccess(this);

//        startService(new Intent(this, RunningAppDetectorService.class));

    }

    private void setLayouts() {
        if(PermissionManager.hasOverlay(this)){

        }else{

        }

        if(PermissionManager.hasUsageAccess(this)){

        }else{

        }

        if(PoolPackageManager.isInstalled(this)){

        }else{

        }

    }

    private void intializeLayouts() {
        layoutStart = findViewById(R.id.layout_start);
        layoutShowInPlaystore = findViewById(R.id.layout_show_in_playstore);
    }

    public void openInPlayStore(View v) {
        PoolPackageManager.openInPlayerStore(this);
    }

    private void toggle() {
        Log.d(TAG, "toggle: ");
        if (ControlBoard.isServiceRunning()) {
            ControlBoard.stop(this);
            tvToggleService.setText("Start");
        } else {
            ControlBoard.start(this);
            tvToggleService.setText("Stop");
        }

    }


}
