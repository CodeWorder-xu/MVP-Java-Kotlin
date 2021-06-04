package com.xhf.wholeproject.loading;

import android.content.Context;
import android.view.View;

/**
 * 处理网络不佳时的一些状况
 */
public interface IVaryViewHelper {

    public abstract View getCurrentLayout();

    public abstract void restoreView();

    public abstract void showLayout(View view);

    public abstract View inflate(int layoutId);

    public abstract Context getContext();

    public abstract View getView();

}