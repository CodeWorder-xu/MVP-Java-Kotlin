package com.xhf.wholeproject.view.activity;


import android.content.Intent;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BaseActivity;
import com.xhf.wholeproject.constant.MyApplication;
import com.xhf.wholeproject.utils.PhoneInfoUtils;
import com.xhf.wholeproject.view.fragment.HomeFragment;
import com.xhf.wholeproject.view.fragment.ListFragment;
import com.xhf.wholeproject.view.fragment.MineFragment;
import com.xhf.wholeproject.view.fragment.MovieFragment;

import java.util.List;

import butterknife.BindView;


/***
 *Date：21-6-30
 *
 *author:Xu.Mr
 *
 * content:首页
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_home)
    FrameLayout flHome;
    @BindView(R.id.rb_Main)
    RadioButton rbMain;
    @BindView(R.id.rb_Syllabus)
    RadioButton rbSyllabus;
    @BindView(R.id.rb_pk)
    RadioButton rbPk;
    @BindView(R.id.rb_OpenClass)
    RadioButton rbOpenClass;
    @BindView(R.id.rb_Answer)
    RadioButton rbAnswer;
    @BindView(R.id.rg_radioGroup)
    RadioGroup rgRadioGroup;
    private Fragment presentFragment = new Fragment();
    private static long DOUBLE_CLICK_TIME = 0L;

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
        String uuid = PhoneInfoUtils.getUuid();
        String imei = PhoneInfoUtils.getIMEI(MainActivity.this);
        String serial = PhoneInfoUtils.getSERIAL();
        String wlanId = PhoneInfoUtils.getWlanId(MainActivity.this);
        String androidId = PhoneInfoUtils.getAndroidId(MainActivity.this);
        showToast("uuid" + uuid + "===imei" + imei + "-----serial" + serial + "----androidId" + androidId + "------wlan=" + wlanId);
    }

    public void setRadioClick() {
        rgRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
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
            transaction.add(R.id.fl_home, targetFragment, targetFragment.getClass().getName());
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
        //二维码权限,拨打电话权限,文件存储,未知来源,设置
        XXPermissions.with(this)
                .permission(Permission.CAMERA)
                .permission(Permission.READ_PHONE_STATE)
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                .permission(Permission.CALL_PHONE)
//                .permission(Permission.WRITE_SETTINGS)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            showToast("获取到全部权限");
                        } else {
                            showToast("获取到部分权限");
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            showToast("权限被永久拒绝,请手动授予权限");
                        } else {
                            showToast("获取权限失败");
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == XXPermissions.REQUEST_CODE) {
            if (XXPermissions.isGranted(this, Permission.RECORD_AUDIO) &&
                    XXPermissions.isGranted(this, Permission.Group.CALENDAR)) {
                showToast("用户已经在权限设置页授予了录音和日历权限");
            } else {
                showToast("用户没有在权限设置页授予权限");
            }
        }
    }
}
