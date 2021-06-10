package com.xhf.wholeproject.base;

import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.ViewCompat;

import com.xhf.wholeproject.rxbus.EventCenter;

/***
 *Date：2021/3/23
 *
 *author:Xu.Mr
 *
 *content:
 */
public class BaseFragment extends BaseLazyFragment {
    /**
     * 缩放空间的大小
     * @param view
     * @param scale
     */
    protected void scaleView(View view,float scale) {
        ViewCompat.animate(view).scaleX(scale).scaleY(scale).start();
        ViewGroup parent0 = (ViewGroup) view.getParent();
        if(parent0 != null) {
            parent0.requestLayout();
            parent0.invalidate();
        }
    }

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
