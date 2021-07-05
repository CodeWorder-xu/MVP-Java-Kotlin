package com.xhf.wholeproject.presenter.impl

import android.content.Context
import android.graphics.drawable.Drawable
import com.xhf.wholeproject.model.interactor.impl.MineFInteractorImpl
import com.xhf.wholeproject.model.interactor.inf.MineFInteractor
import com.xhf.wholeproject.presenter.inf.MineFPresenter
import com.xhf.wholeproject.viewInterface.MineFView

/***
 * Date：21-7-2
 *
 * author:Xu.Mr
 *
 * content:
 */
class MineFPresenterImpl : MineFPresenter, MineFInteractor.CallBack {
    var mineFInteractor: MineFInteractor
    var mineFView: MineFView
    var context: Context

    constructor(context: Context, mineFView: MineFView) {
        mineFInteractor = MineFInteractorImpl(context, this);
        this.mineFView = mineFView;
        this.context = context
    }

    override fun onPortraitDefalut(drawable: Drawable) {
        mineFView.onPortrait(drawable)
    }

    override fun OnPortritString(image: String) {
        mineFView.onPortrait(image)
    }

    override fun onPortraitUtil() {
        mineFInteractor.onPortrait()
    }
}