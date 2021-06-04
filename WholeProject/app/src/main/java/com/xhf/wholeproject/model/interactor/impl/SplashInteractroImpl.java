package com.xhf.wholeproject.model.interactor.impl;

import android.content.Context;

import com.xhf.wholeproject.model.interactor.inf.SplashInteractor;
import com.xhf.wholeproject.utils.SPManager;

/***
 *Date：2021/3/22
 *
 *author:Xu.Mr
 *
 *content:
 */
public class SplashInteractroImpl implements SplashInteractor {
    private Context context;

    public SplashInteractroImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean onIsFirst() {
        boolean isfirst = SPManager.getBoolean(context, "isFirst");
        return isfirst;
    }
}
