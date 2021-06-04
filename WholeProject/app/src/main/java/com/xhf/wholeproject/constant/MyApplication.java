package com.xhf.wholeproject.constant;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;


import com.jiongbull.jlog.Logger;
import com.jiongbull.jlog.constant.LogLevel;
import com.jiongbull.jlog.constant.LogSegment;
import com.jiongbull.jlog.util.TimeUtils;
import com.orhanobut.hawk.Hawk;
import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.AutoAdapterUtil;
import com.xhf.wholeproject.base.BaseAppManager;
import com.xhf.wholeproject.utils.CrashHandler;
import com.xhf.wholeproject.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/***
 *Date：2021/3/1
 *
 *author:Xu.Mr
 *
 *content:Application
 */
public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {
    private static List<Activity> activityList; // 存放每个类的集合
    private static MyApplication instance;

    public static Logger.Builder getsLogger() {
        return sLogger;
    }

    private static Logger.Builder sLogger;


    @Override
    public void onCreate() {
        super.onCreate();
        //保存数据
        Hawk.init(this).build();
        instance = this;
        //屏幕适配
        AutoAdapterUtil.initAppDensity(this);
        //Ilog初始化
        initLog();
//        // setAppend是否为追加模式, setSimple是否是简单的log信息,
//        CrashHandler.init(this, "CarshHandler").setAppend(true).setSimple(false);
//
//        Thread.setDefaultUncaughtExceptionHandler(this);

        SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(true)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(true)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();

        // 注册crashHandler
        // 异常处理，不需要处理时注释掉这两句即可！
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    public static void setInstance(MyApplication instance) {
        MyApplication.instance = instance;
    }

    /**
     * 方法说明:添加Activity到容器中
     */
    public void addActivity(Activity activity) {
        if (activityList != null) {
            activityList.add(activity);
        }
    }

    /**
     * 方法说明:移除Activity
     */
    public void removeActivity(Activity activity) {
        if (activityList != null) {
            activityList.remove(activity);
        }
    }

    /**
     * 方法说明:遍历所有Activity并finish
     */
    public void exit() {
        if (activityList != null) {
            for (Activity activity : activityList) {
                activity.finish();
            }
        }
    }

    /**
     * @return
     */
    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    public void exitApp() {
        BaseAppManager.getInstance().clear();
        System.gc();
//        android.os.Process.killProcess(android.os.Process.myPid());
    }

    //Ilog初始化
    private void initLog() {
        List<String> logLevels = new ArrayList<>();
        logLevels.add(LogLevel.WARN);
        logLevels.add(LogLevel.ERROR);
        logLevels.add(LogLevel.WTF);
        sLogger = Logger.Builder.newBuilder(getApplicationContext(), "jlog")
                .setDebug(true)//发布版本改为false
                .setWriteToFile(true)//写入文件
                .setLogSegment(LogSegment.ONE_HOUR)//将日志按时间切片放入不同文件，时间间隔一个小时
                .setLogDir(".a" + this.getString(R.string.app_name) + "V4")//日志目录名
                .setZoneOffset(TimeUtils.ZoneOffset.P0800)//时区选择//po800 北京时间
                .setTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")//
                .setLogLevelsForFile(logLevels)//哪些文件可以写入
                .setPackagedLevel(1); //封装的层级
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // 重启app ..上传日志等...
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
