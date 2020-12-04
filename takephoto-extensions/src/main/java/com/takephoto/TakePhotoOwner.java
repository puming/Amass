package com.takephoto;

import android.app.Activity;
import android.content.Intent;

import com.pm.takephoto.PictureFileProvider;
import com.pm.takephoto.TakePhotoActivity;

public class TakePhotoOwner {
    public void start(Activity activity,int requestCode){
        Intent intent = new Intent(activity, TakePhotoActivity.class);
        intent.putExtra(TakePhotoActivity.KEY_CONTENT_TYPE, TakePhotoActivity.CONTENT_TYPE_ID_CARD_FRONT);
        intent.putExtra(TakePhotoActivity.KEY_OUTPUT_FILE_PATH, PictureFileProvider.getAbsoluteImagePath());
        activity.startActivityForResult(intent, requestCode);
    }
}
