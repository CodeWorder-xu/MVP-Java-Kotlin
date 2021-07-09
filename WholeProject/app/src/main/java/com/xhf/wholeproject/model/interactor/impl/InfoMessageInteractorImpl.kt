package com.xhf.wholeproject.model.interactor.impl

import android.content.Context
import com.xhf.wholeproject.model.entity.res.InfoMessageEntity
import com.xhf.wholeproject.model.interactor.inf.InfoMessageInteractor
import com.xhf.wholeproject.utils.DataUtils
import com.xhf.wholeproject.utils.SPManager

/***
 *Date：21-7-6
 *
 *author:Xu.Mr
 *
 *content:
 */
class InfoMessageInteractorImpl : InfoMessageInteractor {
    var context: Context
    var callBack: InfoMessageInteractor.CallBack
    val dataUtils: DataUtils

    constructor(context: Context, callBack: InfoMessageInteractor.CallBack) {
        this.callBack = callBack;
        this.context = context
        dataUtils = DataUtils(context)
    }

    override fun onGetInfoMessage(): List<InfoMessageEntity> {
        var phone = SPManager.getString(context, "phone")
        val queryData = dataUtils.queryData(phone, "phone")
        return queryData
    }

    override fun onSetInfoMessage(infomessage: InfoMessageEntity) {

    }


}