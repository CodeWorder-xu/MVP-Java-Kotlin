package com.xhf.wholeproject.model.interactor.impl;

import android.content.Context;

import com.xhf.wholeproject.constant.UserManager;
import com.xhf.wholeproject.model.entity.res.InfoMessageEntity;
import com.xhf.wholeproject.model.interactor.inf.LoginInteractor;
import com.xhf.wholeproject.utils.DataUtils;
import com.xhf.wholeproject.view.activity.LoginAcivity;

import java.util.List;

/***
 *Date：21-6-28
 *
 *author:Xu.Mr
 *
 *content:
 */
public class LoginInteractorImpl implements LoginInteractor {
    private Context context;
    DataUtils dataUtils = null;
    private LoginInteractor.CallBack callBack;

    public LoginInteractorImpl(Context context, LoginInteractor.CallBack callBack) {
        this.callBack = callBack;
        this.context = context;

    }

    @Override
    public void onLogin(String account, String passWord) {

        if (dataUtils == null) {
            dataUtils = new DataUtils(context);
        }

        List<InfoMessageEntity> query = dataUtils.query(InfoMessageEntity.class, account);
        if (query.size() != 0) {
            String passWord1 = query.get(0).getPassWord();
            if (passWord.endsWith(passWord1)) {
                callBack.onSuccess(2); //密码正确
            } else {
                callBack.onFailure(1,"密码错误");//密码错误
            }

        } else {
            callBack.onFailure(0,"找不到账号");//找不到账号
        }


    }
}