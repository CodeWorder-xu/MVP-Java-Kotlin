package com.xhf.wholeproject.presenter.inf

import com.xhf.wholeproject.model.entity.res.InfoMessageEntity

/***
 *Date：21-7-6
 *
 *author:Xu.Mr
 *
 *content:
 */
interface InfoMessagePresenter {
    fun onSetInfoMessage(infomessage: InfoMessageEntity);
    fun onGetInFoMessage()
}