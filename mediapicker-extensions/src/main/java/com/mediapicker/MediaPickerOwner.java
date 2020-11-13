package com.mediapicker;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.pm.mediapicker.MediaPicker;

import java.util.ArrayList;

/**
 * @author pm
 * @date 2019/6/11
 * @email puming@zdsoft.cn
 */
public class MediaPickerOwner {
    private static final String TAG = "MediaPickerOwner";
    private static final int REQUEST_CODE_MEDIA = 1000;
    private static final String[] REQUIRED_PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_CODE_CHOOSE_SINGLE = 1001;
    private static final int REQUEST_CODE_CHOOSE_MULTIPLE = 1002;

    private Activity activity;
    private boolean mSingle;

    public MediaPickerOwner(Activity activity) {
        this(activity, true);
    }

    public MediaPickerOwner(Activity activity, boolean single) {
        this.activity = activity;
        mSingle = single;
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_CHOOSE_SINGLE) {
            handleChooseMediaResult(resultCode, intent);
        } else if (requestCode == REQUEST_CODE_CHOOSE_MULTIPLE) {
            handleChooseMediaResult(resultCode, intent);
        }
        return true;
    }

    private void handleChooseMediaResult(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<String> paths = data.getStringArrayListExtra(MediaPicker.EXTRA_SELECT_IMAGES);
            if (paths != null && !paths.isEmpty()) {
                if (mSingle && paths.size() == 1) {
                    // TODO: 2020/11/13  
                } else {
                    // TODO: 2020/11/13
                }
            } else {
                Log.e(TAG, "handleChooseMediaResult: ", new Exception("choose media error"));
            }
        } else {
            Log.e(TAG, "handleChooseMediaResult: ", new Exception("choose media error"));
        }
    }

    public boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        if (requestCode == REQUEST_CODE_MEDIA) {
            if (permissionGranted) {
                launchPickerActivity();
                return true;
            }
        }
        return false;
    }

    private boolean checkPermission() {
        return ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public void launchPickerActivity() {
        if (!checkPermission()) {
            ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, REQUEST_CODE_MEDIA);
            return;
        }
        Intent intent = new Intent(activity.getPackageName() + ".action.media");
        activity.startActivityForResult(intent, mSingle ? REQUEST_CODE_CHOOSE_SINGLE : REQUEST_CODE_CHOOSE_MULTIPLE);
//        MediaPicker.getInstance().start(activity, mSingle ? REQUEST_CODE_CHOOSE_SINGLE : REQUEST_CODE_CHOOSE_MULTIPLE);
    }
}

