package com.xhf.wholeproject.model.interactor.inf

import com.xhf.wholeproject.model.entity.res.InfoMessageEntity

/***
 *Date：21-7-6
 *
 *author:Xu.Mr
 *
 *content:
 */
interface InfoMessageInteractor {
    fun onGetInfoMessage(): List<InfoMessageEntity>
    fun onSetInfoMessage(infomessage: InfoMessageEntity)
    interface CallBack {
        fun onSetInfoSuccrss(): Boolean
        fun onSetInfoError(): String
    }


}