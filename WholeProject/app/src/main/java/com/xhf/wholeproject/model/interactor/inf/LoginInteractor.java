package com.xhf.wholeproject.model.interactor.inf;

/***
 *Date：21-6-28
 *
 *author:Xu.Mr
 *
 *content:
 */
public interface LoginInteractor {
    void onLogin(String account, String passWord);

    interface CallBack {
        void onSuccess(int state);

        void onFailure(int state, String value);
    }
}
