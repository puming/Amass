package com.cameramodule;

import android.content.Context;

import com.pm.cameraui.CameraActivity;

public class CameraOwner {
    public void start(Context context){
        context.startActivity(CameraActivity.newIntent(context, CameraActivity.TYPE_PICTURE));
    }
}
