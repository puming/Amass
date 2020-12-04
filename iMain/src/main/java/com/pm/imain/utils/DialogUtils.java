package com.pm.imain.utils;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

/**
 * @author pm
 * @date 2019/3/25
 * @email puming@zdsoft.cn
 */
public class DialogUtils {

    public static void showDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("请打开位置服务")
                .setPositiveButton("确定", (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    activity.startActivity(intent);
                    dialog.dismiss();
                })
/*                .setNegativeButton("取消", (dialog, which) -> {
                    dialog.dismiss();
                })*/
                .setCancelable(false)
                .create()
                .show();
    }

}
