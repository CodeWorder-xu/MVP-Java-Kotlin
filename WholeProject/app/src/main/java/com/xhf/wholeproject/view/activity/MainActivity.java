package com.xhf.wholeproject.view.activity;


import android.Manifest;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BaseActivity;
import com.xhf.wholeproject.constant.MyApplication;
import com.xhf.wholeproject.view.fragment.HomeFragment;
import com.xhf.wholeproject.view.fragment.ListFragment;
import com.xhf.wholeproject.view.fragment.MineFragment;
import com.xhf.wholeproject.view.fragment.MovieFragment;

import butterknife.BindView;


/**
 *
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_home)
    FrameLayout vpHome;
    @BindView(R.id.rb_Main)
    RadioButton rbMain;
    @BindView(R.id.rb_Syllabus)
    RadioButton rbSyllabus;
    @BindView(R.id.tab_pk)
    RadioButton tabPk;
    @BindView(R.id.rb_OpenClass)
    RadioButton rbOpenClass;
    @BindView(R.id.rb_Answer)
    RadioButton rbAnswer;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private Fragment presentFragment = new Fragment();
    private static long DOUBLE_CLICK_TIME = 0L;
    private String s = null;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }


    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        rbMain.setChecked(true);
        setRadioClick();
        onSwitchFragment(new HomeFragment()).commit();
        codeSCan();
       /* int z = s.length();
        Toast.makeText(MainActivity.this, z + "", Toast.LENGTH_LONG).show();*/
    }

    public void setRadioClick() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_Main://首页
                    onSwitchFragment(new HomeFragment()).commit();
                    rbMain.setChecked(true);
                    break;
                case R.id.rb_Syllabus://列表
                    rbSyllabus.setChecked(true);
                    onSwitchFragment(new ListFragment()).commit();
                    break;
                case R.id.rb_Answer://我的
                    onSwitchFragment(new MineFragment()).commit();
                    rbAnswer.setChecked(true);
                    break;
                case R.id.rb_OpenClass://视频
                    onSwitchFragment(new MovieFragment()).commit();
                    rbOpenClass.setChecked(true);
                    break;
                default:
            }
        });
    }

    public FragmentTransaction onSwitchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (targetFragment.isAdded()) {
            transaction.hide(presentFragment).show(targetFragment);
        } else {
            if (null != presentFragment) {
                transaction.hide(presentFragment);
            }
            transaction.add(R.id.vp_home, targetFragment, targetFragment.getClass().getName());


        }
        presentFragment = targetFragment;
        return transaction;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
                showToast(getString(R.string.exit_hint));
                DOUBLE_CLICK_TIME = System.currentTimeMillis();
            } else {
                MyApplication.getInstance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void codeSCan() {

        //打开二维码扫描界??
        RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        //二维码扫描登录
                        showToast("申请到权限");
                    } else {
                        showToast("请打开此应用的摄像头权限！");
                    }
                });
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CALL_LOG)
                .subscribe(granted -> {
                    if (granted) {
                        //二维码扫描登录
                        showToast("申请到权限");
                    } else {
                        showToast("请打开此应用的拨打电话权限！");
                    }
                });

    }
}
