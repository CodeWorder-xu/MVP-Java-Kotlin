package com.xhf.wholeproject.presenter.impl;

import android.content.Context;

import com.google.gson.internal.$Gson$Preconditions;
import com.xhf.wholeproject.R;
import com.xhf.wholeproject.model.interactor.impl.RegisteredInteractorImpl;
import com.xhf.wholeproject.model.interactor.inf.RegisteredInterator;
import com.xhf.wholeproject.presenter.inf.RegisteredPresenter;
import com.xhf.wholeproject.viewInterface.RegisteredView;

/***
 *Date：21-6-30
 *
 *author:Xu.Mr
 *
 *content:
 */
public class RegisteredPresenterImpl implements RegisteredPresenter {
    private Context context;
    private RegisteredView registeredView;
    private RegisteredInterator registeredInterator;

    public RegisteredPresenterImpl(Context context, RegisteredView registeredView) {
        this.context = context;
        this.registeredView = registeredView;
        registeredInterator = new RegisteredInteractorImpl(context);
    }


    @Override
    public void onReginstered(String num, String nickName, String phone, String password) {
        Boolean verify = registeredInterator.onVerify(num);
        if (verify) {
            boolean b = registeredInterator.onRegistered(nickName, phone, password);
            if(b)
            {
                registeredView.onRegisteredSuccess();
            }else{
                registeredView.onShowToast(context.getResources().getString(R.string.register_error));
            }
        } else {
            registeredView.onShowToast(context.getResources().getString(R.string.register_errornum)
            );
        }

    }

    @Override
    public void onVerifyNum() {
        registeredView.onVerifyNum(registeredInterator.getVerify());
    }
}