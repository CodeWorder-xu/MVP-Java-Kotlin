package com.xhf.wholeproject.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BaseActivity;
import com.xhf.wholeproject.presenter.impl.LoginPresenterImpl;
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
    @BindView(R.id.ib_back)
    ImageButton ibBack;
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
        ibBack.setVisibility(View.GONE);
        tvTitle.setText("登录");
        onStringWatcher(etAccount);
        onStringWatcher(etPassword);
        int height = tvStatment.getHeight();
        int measuredHeight = tvStatment.getMeasuredHeight();


        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width1 = wm.getDefaultDisplay().getWidth();
        int height1 = wm.getDefaultDisplay().getHeight();

        ILog.d("--------"+width1+"+++++"+height1);
        tvStatment.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                tvStatment.getViewTreeObserver().removeOnPreDrawListener(this);
                int height = tvStatment.getMeasuredHeight();
                int width = tvStatment.getMeasuredWidth();
                ILog.d("-----000000000---"+height+"+++=======++"+width);

                return true;
            }
        });
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

    @OnClick({R.id.but_login, R.id.tv_statment, R.id.tv_registered, R.id.img_qq, R.id.img_weixin, R.id.img_weibo, R.id.img_more, R.id.ib_back, R.id.img_showpass})
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
            case R.id.tv_statment:
                break;
            case R.id.ib_back:
                finish();
                break;
            case R.id.img_showpass:
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


        }

    }


    @Override
    public void onLogin(int state) {
        readyGoThenKill(MainActivity.class);
    }

    @Override
    public void onRegistere() {
    }

    @Override
    public void onQQLogin() {

    }

    @Override
    public void onWeiXinLogin() {

    }

    @Override
    public void onWeiBoLogin() {

    }

    @Override
    public void onStatment() {

    }

    @Override
    public void onShowToast(String string) {
        showToast(string);
    }

    public void onStringWatcher(EditText e) {
        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    for (int i = 0; i < s.length(); i++) {
                        char c = s.charAt(i);
                        if (c >= 0x4e00 && c <= 0X9fff) {
                            s.delete(i, i + 1);
                        }
                    }
                }
            }
        });
    }


}