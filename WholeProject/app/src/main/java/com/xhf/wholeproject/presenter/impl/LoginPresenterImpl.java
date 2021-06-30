package com.xhf.wholeproject.presenter.impl;

import android.content.Context;

import com.xhf.wholeproject.model.interactor.impl.LoginInteractorImpl;
import com.xhf.wholeproject.model.interactor.inf.LoginInteractor;
import com.xhf.wholeproject.presenter.inf.LoginPresenter;
import com.xhf.wholeproject.viewInterface.LoginView;

/***
 *Date：21-6-28
 *
 *author:Xu.Mr
 *
 *content:
 */
public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.CallBack {
    private Context context;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
        loginInteractor = new LoginInteractorImpl(context,this);
    }

    @Override
    public void onLogin(String account, String passWord) {
        loginInteractor.onLogin(account, passWord);
    }

    @Override
    public void onSuccess(int state) {
        loginView.onLogin(state);
    }

    @Override
    public void onFailure(int state, String value) {
        loginView.onShowToast(value);

    }
}