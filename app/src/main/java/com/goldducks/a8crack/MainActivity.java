package com.goldducks.a8crack;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

public class MainActivity extends Activity {
    ViewManager viewManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        viewManager = new ViewManager(this);
//
//        PermissionManager.checkForOverlayPermission(this);
//
        startService(new Intent(this, CrackService.class));
////        addGuildlineView();
   //     addHandle();
//        finish();
    }


}
