package com.xhf.wholeproject.model.interactor.impl

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.xhf.wholeproject.R
import com.xhf.wholeproject.model.interactor.inf.MineFInteractor
import com.xhf.wholeproject.utils.DataUtils
import com.xhf.wholeproject.utils.SPManager

/***
 *Date：21-7-2
 *
 *author:Xu.Mr
 *
 *content:
 */
class MineFInteractorImpl : MineFInteractor {
    var context: Context
    var callBack: MineFInteractor.CallBack

    constructor(context: Context, callBack: MineFInteractor.CallBack) {
        this.callBack = callBack
        this.context = context
    }


    override fun onPortrait() {
        var dataUtils = DataUtils(context)
        val queryData = dataUtils.queryData(SPManager.getString(context, "phone"), "phone")
        if (queryData.size != 0) {
            if (!TextUtils.isEmpty(queryData.get(0).imageUrl)) {
                callBack.OnPortritString(queryData.get(0).imageUrl)

            } else {
                var age = queryData.get(0).age
                if (TextUtils.isEmpty(age)) {//没有性别
                    callBack.onPortraitDefalut(context.resources.getDrawable(R.drawable.ic_default_portrait))

                } else if (age.equals("1")) {
                    //男性
                    callBack.onPortraitDefalut(context.resources.getDrawable(R.drawable.ic_default_boy))

                } else {//女性
                    callBack.onPortraitDefalut(context.resources.getDrawable(R.drawable.ic_default_girl))

                }
            }
        } else {
            callBack.onPortraitDefalut(context.resources.getDrawable(R.drawable.ic_default_portrait))
        }


    }
}