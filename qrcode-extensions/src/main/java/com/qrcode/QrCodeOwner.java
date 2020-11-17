package com.qrcode;

import android.app.Activity;
import android.content.Context;

public class QrCodeOwner {
    public void start(Context context){
        LaunchDelegate.toActivity((Activity) context);
    }
}
