package com.xhf.wholeproject.utils;

import android.content.Context;
import android.text.TextUtils;

/**
 * sp存储信息
 * <p>
 * 为了方便存储，统一存在userinfo的xml下
 */
public class SPManager {
    //存储string信息
    public static String getString(Context context, String name) {

        return context.getSharedPreferences("userinfo", Context.MODE_PRIVATE).getString(name, "");
    }

    public static void setString(Context context, String name, String usreid) {
        if (!TextUtils.isEmpty(usreid)) {
            context.getSharedPreferences("userinfo", Context.MODE_PRIVATE).edit().putString(name, usreid).commit();
        }
    }

    //存储boolean信息
    public static boolean getBoolean(Context context, String name) {
        return context.getSharedPreferences("userinfo", Context.MODE_PRIVATE).getBoolean(name, false);
    }

    public static void setBoolean(Context context, String name, Boolean b) {
        context.getSharedPreferences("userinfo", Context.MODE_PRIVATE).edit().putBoolean(name, b).commit();

    }

    //存储Inter信息
    public static int getInter(Context context, String name) {
        return context.getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt(name, -1);
    }

    public static void setInter(Context context, String name, int b) {
        context.getSharedPreferences("userinfo", Context.MODE_PRIVATE).edit().putInt(name, b).commit();
    }


    public void deleteAll(Context context) {
        context.getSharedPreferences("userinfo", Context.MODE_PRIVATE).edit().clear().commit();
    }
}
