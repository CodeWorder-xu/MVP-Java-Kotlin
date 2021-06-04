package com.xhf.wholeproject.utils;

import android.text.TextUtils;

import com.xhf.wholeproject.constant.MyApplication;


/***
 *Date：2021/3/4
 *
 *author:Xu.Mr
 *
 *content:log
 */
public class ILog {
    private static boolean isOn = true;

    public static void d(String massage) {
        if (isOn) {
            if (TextUtils.isEmpty(massage)) {
                MyApplication.getsLogger().build().d("数值为空");
            } else {
                MyApplication.getsLogger().build().d(massage);
            }
        }
    }

    public static void err(String message) {
        if (isOn) {
            MyApplication.getsLogger().build().e(message);
        }
    }

}
