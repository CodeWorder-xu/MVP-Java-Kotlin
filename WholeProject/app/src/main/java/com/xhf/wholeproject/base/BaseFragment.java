package com.xhf.wholeproject.base;

import android.view.View;

import com.xhf.wholeproject.rxbus.EventCenter;

/***
 *Date：2021/3/23
 *
 *author:Xu.Mr
 *
 *content:
 */
public class BaseFragment extends BaseLazyFragment {


    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }
}
