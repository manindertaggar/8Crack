package com.goldducks.a8crack;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewManager.init(this);

        PermissionManager.checkForOverlayPermission(this);

        GuidlineView guidlineView = new GuidlineView(this);

        ViewManager.getRunningInstance().addView(guidlineView.getView(), LayoutParamGenerator.getNewLayoutParams());
        finish();
    }

}
