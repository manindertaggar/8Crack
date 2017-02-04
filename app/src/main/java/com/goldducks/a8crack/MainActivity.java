package com.goldducks.a8crack;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionManager.checkForOverlayPermission(this);

        FrameLayout linearLayout = (FrameLayout) findViewById(R.id.ll);
        linearLayout.addView(new GuidlineView(this).getView());
    }

}
