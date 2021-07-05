package com.xhf.wholeproject.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/*
*******一条重要的分割线*********
---------------------------------
******类创始人:xhf
---------------------------------
******时间:2020/7/7 20:58

******类功能:
----------------------------------
******一条假装是分割线的线条*********
*/public class MyDialog extends Dialog {
    private int width;
    private int height;
    //    style引用style样式
    public MyDialog(Context context, int width, int height, View layout, int style) {

        super(context, style);

        setContentView(layout);

        Window window = getWindow();

        WindowManager.LayoutParams params = window.getAttributes();
        this.width=width;
        this.height=height;
        params.width=this.width;
        params.height=this.height;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

}
