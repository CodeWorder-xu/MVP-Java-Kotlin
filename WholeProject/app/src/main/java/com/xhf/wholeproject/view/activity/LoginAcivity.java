package com.xhf.wholeproject.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BaseActivity;
import com.xhf.wholeproject.presenter.impl.LoginPresenterImpl;
import com.xhf.wholeproject.utils.CommonUtils;
import com.xhf.wholeproject.utils.ILog;
import com.xhf.wholeproject.viewInterface.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 *Date：21-6-25
 *
 *author:Xu.Mr
 *
 *content:注册登录
 */
public class LoginAcivity extends BaseActivity implements LoginView {
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.but_login)
    Button butLogin;
    @BindView(R.id.tv_statment)
    TextView tvStatment;
    @BindView(R.id.tv_registered)
    TextView tvRegistered;
    @BindView(R.id.img_qq)
    ImageView imgQq;
    @BindView(R.id.img_weixin)
    ImageView imgWeixin;
    @BindView(R.id.img_weibo)
    ImageView imgWeibo;
    @BindView(R.id.img_more)
    ImageView imgMore;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.img_showpass)
    ImageView imgShowpass;
    private LoginPresenterImpl loginPresenter;
    private Boolean isLook = false;

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        llBack.setVisibility(View.GONE);
        tvTitle.setText("登录");
        CommonUtils.setHint(etAccount, getResources().getText(R.string.account_hint), 10);
        CommonUtils.setHint(etPassword, getResources().getText(R.string.password_hint), 10);
        CommonUtils.onStringWatcher(etAccount, 18);
        CommonUtils.onStringWatcher(etPassword, 10);

        loginPresenter = new LoginPresenterImpl(this, this);

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.but_login, R.id.tv_statment, R.id.tv_registered, R.id.img_qq, R.id.img_weixin, R.id.img_weibo, R.id.img_more, R.id.ll_back, R.id.img_showpass})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_login://登录
                String account = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    showToast("请输入账号");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    showToast("请输入密码");
                    return;
                }

                loginPresenter.onLogin(account, password);
                break;
            case R.id.tv_statment://静思说明
                break;
            case R.id.ll_back://返回
                finish();
                break;
            case R.id.img_showpass://密码
                if (!isLook) {
                    isLook = true;
                    imgShowpass.setBackground(getResources().getDrawable(R.drawable.ic_look));
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    isLook = false;
                    imgShowpass.setBackground(getResources().getDrawable(R.drawable.ic_nolook));
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.tv_registered://注册账号
                readyGo(RegisteredActivity.class);
                break;


        }

    }


    @Override
    public void onLogin(int state) {
        readyGoThenKill(MainActivity.class);
    }


    @Override
    public void onShowToast(String string) {
        showToast(string);
    }


}