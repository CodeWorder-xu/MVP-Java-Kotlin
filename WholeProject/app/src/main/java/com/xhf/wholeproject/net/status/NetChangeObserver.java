package com.xhf.wholeproject.net.status;

import java.io.Serializable;

/***
 *Date：6/11/21
 *
 *author:Xu.Mr
 *
 *content:
 */
public class NetChangeObserver implements Serializable {

    /**
     * 网络连接回调
     */
    public void onNetConnected(NetUtils.NetType type) {

    }

    /**
     * 网络断开回调
     */
    public void onNetDisConnect() {

    }
}