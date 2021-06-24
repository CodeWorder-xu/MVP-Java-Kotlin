package com.xhf.wholeproject.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import com.hjq.permissions.XXPermissions;
import com.xhf.wholeproject.model.interactor.impl.BookLabInteractorImpl;

/***
 *Date：21-6-15
 *
 *author:Xu.Mr
 *
 *content:调节亮度的方法
 */
public class ScreenBrightnessUtil {


    private static boolean autoBrightness;

    //设置系统屏幕亮度值,在设置之前先关闭亮度自动调节，设为手动模式
    public static void setBrightness(Context context, int value) {
        SPManager.setInter(context, "brigheness", value);
        if (isAutoBrightness(context)) {
            setAutoBrightness(false);
        }
        changeAppBrightness(context, value);
    }


    //获取当前系统屏幕亮度，获取失败返回-1
    public static int getBrightness(Context context) {
        int brightnessValue = -1;
        try {
            brightnessValue = Settings.System.
                    getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brightnessValue;
    }


    //判断系统是否打开了自动调节亮度
    public static boolean isAutoBrightness(Context context) {
        return autoBrightness;
    }


    //关闭自动调节亮度,设为手动模式
    public static void setAutoBrightness(Boolean autoBrigh) {
//修改当前页面的亮度
        autoBrightness = autoBrigh;
    }


    public static void changeAppBrightness(Context context, int brightness) {
        Window window = ((Activity) context).getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (brightness == -1) {
            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        } else {
            lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
        }
//        lp.screenBrightness = 0.6f;
        window.setAttributes(lp);
    }

    //打开自动调节亮度
    public static void openAutoBrightness(Context context) {
//获取当前屏幕的亮度
        setAutoBrightness(true);
        SPManager.setInter(context, "brigheness", -1);

        final WindowManager.LayoutParams attrs = ((Activity) context).getWindow().getAttributes();
        attrs.screenBrightness = -1.0f;//-1代表使用系统亮度
        ((Activity) context).getWindow().setAttributes(attrs);

    }
}
