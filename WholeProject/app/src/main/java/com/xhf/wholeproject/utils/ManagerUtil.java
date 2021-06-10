package com.xhf.wholeproject.utils;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecycleView管理器的方法
 */
public class ManagerUtil {
    public static RecyclerView.LayoutManager getVerticalManager(Context context,Boolean b) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return b;
            }
        };

        return linearLayoutManager;

    }

    public static RecyclerView.LayoutManager getHorizonalManager(Context context,Boolean b) {
//1.上下文
//2.方向
//3.倒排  1->10   true  10 -->1

        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return b;
            }
        };
        return manager;

    }

    public static RecyclerView.LayoutManager getGridLayoutManager(Context context, int num) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, num);
        return gridLayoutManager;

    }
}
