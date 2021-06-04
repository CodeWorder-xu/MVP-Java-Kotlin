package com.xhf.wholeproject.presenter.impl;

import android.content.Context;
import android.graphics.Color;

import com.xhf.wholeproject.model.interactor.impl.SplashInteractroImpl;
import com.xhf.wholeproject.presenter.inf.SplashPresenter;
import com.xhf.wholeproject.viewInterface.SplashView;

/***
 *Date：2021/3/22
 *
 *author:Xu.Mr
 *
 *content:
 */
public class SplashPresenterImpl implements SplashPresenter {
    private Context context;
    private SplashView splashView;
    private SplashInteractroImpl splashInteractro;

    public SplashPresenterImpl(Context context, SplashView splashView) {
        this.context = context;
        this.splashView = splashView;
        splashInteractro = new SplashInteractroImpl(context);

    }

    @Override
    public void onGetSPValue() {
        boolean b = splashInteractro.onIsFirst();
        if (b) {
            splashView.onToMain();
        } else {
            splashView.onToNavigation();

        }
    }
}
