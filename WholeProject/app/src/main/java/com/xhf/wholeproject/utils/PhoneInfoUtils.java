package com.xhf.wholeproject.utils;

import android.app.Application;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.xhf.wholeproject.constant.MyApplication;

import java.util.UUID;

/***
 *Date：21-6-25
 *
 *author:Xu.Mr
 *
 *content:获取手机相关信息
 */
public class PhoneInfoUtils {

    //需要获得READ_PHONE_STATE权限，>=6.0，默认返回null
    public static String getIMEI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备的AndroidId
     *
     * @param context 上下文
     * @return 设备的AndroidId
     */
    public static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备序列号（如：WTK7N16923005607）, 个别设备无法获取
     *
     * @return 设备序列号
     */
    public static String getSERIAL() {
        try {
            return Build.SERIAL;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getUuid() {
        return UUID.randomUUID().toString();
    }


    public static String getWlanId(Context context){
        WifiManager wm = (WifiManager)context.getSystemService(MyApplication.WIFI_SERVICE);
        String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
        return m_szWLANMAC;
    }
}