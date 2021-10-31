package com.pm.login.utils;

import android.text.TextUtils;

/**
 * @author pm
 */
public class LoginUtil {
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "^((1[3,5,7,8][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    public static boolean isAuthCodeNo(String code) {
        String regex = "^\\d{6}$";
        if (TextUtils.isEmpty(code)) {
            return false;
        } else {
            return code.matches(regex);
        }
    }
}
