package com.xtm.qidashi.mingnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author TianMing
 * @version 1.0
 * @date 2019/5/14
 * @description:
 */
public class SpUtil {
    public static final String PREF_NAME = "news";
    public static SharedPreferences sp;

    private static void initSp(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
    }

    public static void putBoolean(Context context, String key, boolean value) {
        initSp(context);
        sp.edit().putBoolean(key, value).apply();
    }


    public static boolean getBoolean(Context context, String key, boolean defValue) {
        initSp(context);
        return sp.getBoolean(key, defValue);
    }

    public static void putString(Context context, String key, String value) {
        initSp(context);
        sp.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        initSp(context);
        return sp.getString(key, defValue);
    }


}
