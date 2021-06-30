package com.xhf.wholeproject.presenter.impl;

import android.content.Context;

import com.google.gson.internal.$Gson$Preconditions;
import com.xhf.wholeproject.presenter.inf.RegisteredPresenter;
import com.xhf.wholeproject.viewInterface.RegisteredView;

/***
 *Date：21-6-30
 *
 *author:Xu.Mr
 *
 *content:
 */
public class RegisteredPresenterImpl   implements RegisteredPresenter {
    private  Context context;
    private RegisteredView registeredView;
    public RegisteredPresenterImpl(Context context, RegisteredView registeredView)
    {
        this.context=context;
        this.registeredView=registeredView;

    }
}