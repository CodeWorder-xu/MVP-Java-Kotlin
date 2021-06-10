package com.xhf.wholeproject.presenter.impl;

import android.content.Context;

import com.xhf.wholeproject.model.interactor.impl.HomeFInteractorImpl;
import com.xhf.wholeproject.presenter.inf.HomeFPresenter;
import com.xhf.wholeproject.viewInterface.HomeFView;

/***
 *Date：6/10/21
 *
 *author:Xu.Mr
 *
 *content:
 */
public class HomeFPresenterImpl implements HomeFPresenter {
    private Context context;
    private HomeFView homeFView;
    private final HomeFInteractorImpl homeFInteractor;

    public HomeFPresenterImpl(Context context, HomeFView homeFView) {
        this.context = context;
        this.homeFView = homeFView;
        homeFInteractor = new HomeFInteractorImpl(context);
    }


    @Override
    public void onBannerValue() {
        homeFView.onBanner( homeFInteractor.onBannerValue());
    }
}