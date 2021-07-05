package com.xhf.wholeproject.viewInterface

import com.xhf.wholeproject.model.entity.res.InfoMessageEntity
import com.xhf.wholeproject.view.activity.InfoMessage

/***
 * Date：21-7-5
 *
 * author:Xu.Mr
 *
 * content:
 */
interface InfoMessageView {
    fun onGetValue(queryData: List<InfoMessageEntity>);
    fun onSetValue(isSuccess: Boolean, mesage: String)
}