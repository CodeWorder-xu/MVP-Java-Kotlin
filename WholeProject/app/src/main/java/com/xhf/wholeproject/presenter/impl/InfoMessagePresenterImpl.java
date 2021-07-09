package com.xhf.wholeproject.presenter.impl;

import android.content.Context;

import com.xhf.wholeproject.model.entity.res.InfoMessageEntity;
import com.xhf.wholeproject.model.interactor.impl.InfoMessageInteractorImpl;
import com.xhf.wholeproject.model.interactor.inf.InfoMessageInteractor;
import com.xhf.wholeproject.presenter.inf.InfoMessagePresenter;
import com.xhf.wholeproject.viewInterface.InfoMessageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/***
 *Date：21-7-6
 *
 *author:Xu.Mr
 *
 *content:
 */
public class InfoMessagePresenterImpl implements InfoMessagePresenter, InfoMessageInteractor.CallBack {
    private Context context;
    private InfoMessageView infoMessageView;
    private InfoMessageInteractor infoMessageInteractor;

    public InfoMessagePresenterImpl(Context context, InfoMessageView infoMessageView) {
        this.context = context;
        this.infoMessageView = infoMessageView;
        infoMessageInteractor = new InfoMessageInteractorImpl(context, this);
    }


    @Override
    public void onSetInfoMessage(InfoMessageEntity infomessage) {
    }

    @Override
    public void onGetInFoMessage() {
        List<InfoMessageEntity> infoMessageEntities = infoMessageInteractor.onGetInfoMessage();

        infoMessageView.onGetValue(infoMessageEntities);
    }

    @Override
    public boolean onSetInfoSuccrss() {
        return false;
    }

    @NotNull
    @Override
    public String onSetInfoError() {
        return null;
    }
}