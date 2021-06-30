package com.xhf.wholeproject.model.interactor.impl;

import android.content.Context;

import com.google.gson.internal.$Gson$Preconditions;
import com.xhf.wholeproject.model.interactor.inf.RegisteredInterator;
import com.xhf.wholeproject.viewInterface.RegisteredView;

/***
 *Date：21-6-30
 *
 *author:Xu.Mr
 *
 *content:
 */
public class RegisteredInteractorImpl implements RegisteredInterator {
    private Context context;

    public RegisteredInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onRegistered(String name, String phone, String password) {

    }

    @Override
    public Boolean onVerify(String num) {
        if (num.length() > 4) {
            return true;
        } else {
            return false;
        }

    }
}