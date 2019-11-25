package com.pm.amass.utils;

import android.text.TextUtils;

import androidx.annotation.NonNull;


/**
 * @author pm
 * @date 2019/1/29
 * @email puming@zdsoft.cn
 */
public class StringUtil {
    @NonNull
    public static String generateThumbId(String obsIds) {
        String[] ids = generateSubString(obsIds);
        int length = ids.length;
        return length > 0 ? ids[length - 1] : "";
    }

    @NonNull
    public static String[] generateSubString(String res) {
        String[] ids = res.split(";");
        return ids.length > 0 ? ids : new String[]{};
    }

    @NonNull
    public static StringBuilder getUrlStringBuilder(String imageId) {
        StringBuilder builder = new StringBuilder();
        return builder;
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isEmpty(String string) {
        if (isNull(string)) {
            return true;
        }
        return string.isEmpty();
    }

    public static int valueOf(String value) {
        int valueOf = 0;
        if (!TextUtils.isEmpty(value)) {
            try {
                valueOf = Integer.valueOf(value);
            } catch (Exception e) {

            }
        }
        return valueOf;
    }

    public static String valueOf(int value) {
        String valueOf = "";
        try {
            valueOf = String.valueOf(value);
        } catch (Exception e) {

        }
        return valueOf;
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            //去掉多余的0
//            s = s.replaceAll("0+?$", "");
            //如最后一位是.则去掉
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }
}
