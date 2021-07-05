package com.xhf.wholeproject.model.interactor.inf

import android.graphics.drawable.Drawable

/***
 *Date：21-7-2
 *
 *author:Xu.Mr
 *
 *content:
 */
interface MineFInteractor {
    fun onPortrait()

    /**
     *
     *onPortraitDefalut()为没有上传头像时 的默认头像
     *
     * OnPortritString()为有头像的头像
     * */
    interface CallBack {
        fun onPortraitDefalut(drawable: Drawable);
        fun OnPortritString(image: String);
    }
}