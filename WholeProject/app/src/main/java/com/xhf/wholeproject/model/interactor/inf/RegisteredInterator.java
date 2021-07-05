package com.xhf.wholeproject.model.interactor.inf;

/***
 *Date：21-6-30
 *
 *author:Xu.Mr
 *
 *content:
 */
public interface RegisteredInterator {
    boolean onRegistered(String name, String phone, String password);

    Boolean onVerify(String num);

    String getVerify();
}
