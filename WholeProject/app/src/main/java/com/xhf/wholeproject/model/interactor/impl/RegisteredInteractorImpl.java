package com.xhf.wholeproject.model.interactor.impl;

import android.content.Context;

import com.google.gson.internal.$Gson$Preconditions;
import com.xhf.wholeproject.model.entity.res.InfoMessageEntity;
import com.xhf.wholeproject.model.interactor.inf.RegisteredInterator;
import com.xhf.wholeproject.utils.DataUtils;
import com.xhf.wholeproject.utils.SPManager;
import com.xhf.wholeproject.viewInterface.RegisteredView;

import java.util.List;
import java.util.Random;

/***
 *Date：21-6-30
 *
 *author:Xu.Mr
 *
 *content:
 */
public class RegisteredInteractorImpl implements RegisteredInterator {
    private Context context;
    private DataUtils dataUtils;
    private String s = "";

    public RegisteredInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean onRegistered(String name, String phone, String password) {
        if (dataUtils == null) {
            dataUtils = new DataUtils(context);
        }
        InfoMessageEntity infoMessageEntity = new InfoMessageEntity();
        infoMessageEntity.setNickName(name);
        infoMessageEntity.setPhone(phone);
        infoMessageEntity.setPassWord(password);
        Random random = new Random();
        String token = "";
        for (int i = 0; i < 6; i++) {
            token += (random.nextInt(10));
        }
        infoMessageEntity.setToken(token);
        dataUtils.insertStudent(infoMessageEntity);
        List<InfoMessageEntity> phone1 = dataUtils.queryData(phone, "phone");
        if (phone1.size() != 0) {
            SPManager.setString(context,"token",token);
            SPManager.setString(context,"phone",phone);
            return true;

        } else {
            return false;
        }

    }

    @Override
    public Boolean onVerify(String num) {
        if (num.equals(s)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String getVerify() {
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            s += (random.nextInt(10));
        }
        return s;
    }
}