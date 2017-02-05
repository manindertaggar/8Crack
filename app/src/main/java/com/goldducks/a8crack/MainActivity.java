package com.goldducks.a8crack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    TextView tvToggleService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvToggleService = (TextView) findViewById(R.id.tvToggleService);
        if (ControlBoard.isServiceRunning()) {
            tvToggleService.setText("Stop");
        } else {
            tvToggleService.setText("Start");
        }

        tvToggleService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }


        });

        PermissionManager.checkForOverlay(this);
        PermissionManager.checkForUsageAccess(this);

//        startService(new Intent(this, RunningAppDetectorService.class));

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
