/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xhf.wholeproject.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.SkinAppCompatDelegateImpl;

import com.google.android.material.snackbar.Snackbar;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.xhf.wholeproject.R;
import com.xhf.wholeproject.loading.VaryViewHelperController;
import com.xhf.wholeproject.utils.AndroidWorkaroundUtils;
import com.xhf.wholeproject.utils.CrashHandler;
import com.xhf.wholeproject.utils.SPManager;
import com.xhf.wholeproject.utils.StringUtil;
import com.xhf.wholeproject.utils.ToastUtil;
import com.xhf.wholeproject.widgets.CustomDialog;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 *
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
    /**
     * Log tag
     */
    protected static String TAG = null;
    protected CustomDialog dialog;
    /**
     * 屏幕信息
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;
    protected int mScreenDensityDpi = 0;

    /**
     * context
     */
    protected Context mContext = null;

    /**
     * 异常视图
     */
    private VaryViewHelperController mVaryViewHelperController = null;

    /**
     * ButterKnife相关
     */
    private Unbinder unbinder;

    /**
     * activity切换方式
     */
    public enum TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
    }

    private CompositeDisposable compositeDisposable;

    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    private void dispose() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
                default:
            }
        }

        super.onCreate(savedInstanceState);


//        AutoAdapterUtil.setDefault(this);
        mContext = this;
        TAG = this.getClass().getSimpleName();

        //隐藏状态栏
        if (AndroidWorkaroundUtils.checkDeviceHasNavigationBar(this)) {
            AndroidWorkaroundUtils.assistActivity(findViewById(android.R.id.content));
        }


        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());

        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        // base setup
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        BaseAppManager.getInstance().addActivity(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenDensity = displayMetrics.density;
        mScreenDensityDpi = displayMetrics.densityDpi;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
        initViewsAndEvents();
        if (savedInstanceState != null) {
            onActivityAutoReCreate(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        onActivityAutoDestroy(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
        if (null != getLoadingTargetView()) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
    }

    @Override
    protected void onPause() {
        hideFullScreenLoading();
        super.onPause();
    }

    @Override
    public void finish() {
        BaseAppManager.getInstance().removeActivity(this);
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
                default:
            }
        }
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        //接触RxBus的订阅关系
//        dispose();
        BaseAppManager.getInstance().removeActivity(this);
    }


    /**
     * 设置沉浸式状态栏颜色
     */
    //protected abstract int setSystemBarTintDrawableRes();

    /**
     * 获取传入的Bundle对象
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    /**
     * 绑定布局资源文件
     *
     * @return id of layout resource
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 显示异常时的视图
     */
    protected abstract View getLoadingTargetView();

    /**
     * 视图初始化
     */
    protected abstract void initViewsAndEvents();

    /**
     * 当Activity自动重新创建时调用(奔溃后自动重启)
     */
    protected abstract void onActivityAutoReCreate(Bundle savedInstanceState);

    /**
     * 当Activity自动销毁时调用(奔溃后自动重启)
     */
    protected abstract void onActivityAutoDestroy(Bundle savedInstanceState);

    /**
     * 开关页面切换动画
     *
     * @return
     */
    protected abstract boolean toggleOverridePendingTransition();

    /**
     * 获取页面切换动画样式
     */
    protected abstract TransitionMode getOverridePendingTransitionMode();

    /**
     * 网络连接
     */
    protected abstract void onNetWorkConnect();

    /**
     * 网络断开
     */
    protected abstract void onNetWorkDisConnect();

    /**
     * 打开Activity
     *
     * @param clazz
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 打开新的ctivity并且清空之前所有的堆栈
     *
     * @param clazz
     */
    protected void readyGoCleanAll(Class<?> clazz) {
        Intent intent = new Intent(this, clazz).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 打开Activity并且携带Bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGoCleanAll(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 打开Activity并且携带Bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 打开Activity并且携带Intent
     *
     * @param clazz
     * @param intentz
     */
    protected void readyGo(Class<?> clazz, Intent intentz) {
        Intent intent = new Intent(this, clazz);
        if (null != intentz) {
            intent.putExtras(intentz);
        }
        startActivity(intent);
    }

    /**
     * 打开Activity后销毁当前Activity
     *
     * @param clazz
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * 打开新的Activity并且携带Bundle后销毁当前Activity
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * 打开带返回值的Activity
     *
     * @param clazz
     * @param requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 打开带返回值的Activity并且携带Bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 沉浸式状态栏
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && on) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    /**
     * 设置沉浸式状态栏样式
     *
     * @param tintDrawable
     */
    protected void setSystemBarTintDrawable(Drawable tintDrawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            if (tintDrawable != null) {
                mTintManager.setStatusBarTintEnabled(true);
                mTintManager.setTintDrawable(tintDrawable);
            } else {
                mTintManager.setStatusBarTintEnabled(false);
                mTintManager.setTintDrawable(null);
            }
        }
    }

    /**
     * 显示提示框(Toast)
     *
     * @param msg
     */
    protected void showToast(String msg) {
        if (null != msg && !StringUtil.isEmpty(msg)) {
            Snackbar snackbar = Snackbar.make(((Activity) mContext).getWindow().getDecorView(), "", Snackbar.LENGTH_SHORT);
            View layout = View.inflate(mContext, R.layout.snackbar_layout, null);
            TextView tvHint = layout.findViewById(R.id.tv_hint);
            tvHint.setText(msg);
            ToastUtil.onSnackbarAddView(snackbar, layout);
            snackbar.show();
        }
    }


    /**
     * 显示自定义弹窗
     *
     * @param view
     */
    protected CustomDialog showDialog(View view) {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setContentView(view);
        CustomDialog dialog1 = builder.create();
        dialog1.show();
        return dialog1;
    }



 /*   @Subscribe
    public void onEventMainThread(EventCenter eventCenter) {
        if (null != eventCenter) {
            onEventComming(eventCenter);
        }
    }*/


    /**
     * 显示加载弹窗
     */
    public void showFullScreenLoading() {
        if (dialog == null) {
            View view = View.inflate(mContext, R.layout.loading, null);
            ProgressBar mProgressBar = view.findViewById(R.id.loading_progress);
            Rect bounds = mProgressBar.getIndeterminateDrawable().getBounds();
            mProgressBar.getIndeterminateDrawable().setBounds(bounds);
            CustomDialog.Builder builder = new CustomDialog.Builder(mContext);
            builder.setContentView(view);
            builder.canCancel(false); //设置点击外部不消失
            builder.setLayout(0, -150 * mScreenHeight / 1920);
            builder.setMeasure(200 * mScreenWidth / 1080, 200 * mScreenWidth / 1080);
            dialog = builder.create();
        }
        dialog.show();
    }

    /**
     * 隐藏加载弹窗
     */
    public void hideFullScreenLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 点击返回按钮加载窗消失
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //将页面图片等信息改为灰色
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        boolean isgray = SPManager.getBoolean(this, "isgray");
        if (isgray) {
            try {
                if ("FrameLayout".equals(name)) {
                    int count = attrs.getAttributeCount();
                    for (int i = 0; i < count; i++) {
                        String attributeName = attrs.getAttributeName(i);
                        String attributeValue = attrs.getAttributeValue(i);
                        if (attributeName.equals("id")) {
                            int id = Integer.parseInt(attributeValue.substring(1));
                            String idVal = getResources().getResourceName(id);
                            if ("android:id/content".equals(idVal)) {
                                //将页面所有颜色为灰色
                                GrayFrameLayout grayFrameLayout = new GrayFrameLayout(context, attrs);
//                            grayFrameLayout.setWindow(getWindow());
                                return grayFrameLayout;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onCreateView(name, context, attrs);
    }

    private void hideSystemBar() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }
}
