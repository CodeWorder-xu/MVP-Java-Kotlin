package com.xhf.wholeproject.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xhf.wholeproject.constant.UserManager;
import com.xhf.wholeproject.view.activity.MainActivity;


/***
 *Date：2021/3/3
 *
 *author:Xu.Mr
 *
 *content:BaseActivity类
 */
public abstract class BaseActivity extends BaseAppCompatActivity {
    /**
     * 网络问题连接成功
     */
    @Override
    protected void onNetWorkConnect() {
    }

    /**
     * 网络问题连接失败
     */
    @Override
    protected void onNetWorkDisConnect() {
    }

    /**
     * 获取bundle的值
     *
     * @param extras
     */
    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * 绑定布局资源文件
     *
     * @return id of layout resource
     */
    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }


    /**
     * 显示异常时的视图
     */
    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    /**
     * 视图初始化
     */
    @Override
    protected void initViewsAndEvents() {

    }

    /**
     * 当Activity自动重新创建时调用(奔溃后自动重启)
     */
    @Override
    protected void onActivityAutoReCreate(Bundle savedInstanceState) {

    }


    /**
     * 当Activity自动销毁时调用(奔溃后自动重启)
     */
    @Override
    protected void onActivityAutoDestroy(Bundle savedInstanceState) {

    }

    /**
     * 开关页面切换动画
     */

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    /**
     * 获取页面切换动画样式
     */
    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }


    protected boolean isLogin() {
        boolean isLogin = true;
        if (UserManager.getInstance().getUserList().size() != 0) {
            readyGoThenKill(MainActivity.class);
            isLogin = false;
        }
        return isLogin;
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
