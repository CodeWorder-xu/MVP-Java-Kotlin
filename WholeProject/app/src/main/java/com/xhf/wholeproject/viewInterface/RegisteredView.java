package com.xhf.wholeproject.viewInterface;

import com.xhf.wholeproject.base.BaseView;

/***
 *Date：21-6-30
 *
 *author:Xu.Mr
 *
 *content:注册
 */
public interface RegisteredView  extends BaseView {
    void onRegisteredSuccess();
    void onErrorNum();
    void onVerifyNum(String verifyNum);
}