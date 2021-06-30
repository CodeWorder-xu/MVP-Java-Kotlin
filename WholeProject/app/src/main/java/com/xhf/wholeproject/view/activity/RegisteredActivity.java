package com.xhf.wholeproject.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BaseActivity;
import com.xhf.wholeproject.utils.CommonUtils;
import com.xhf.wholeproject.utils.TimeCountUtil;
import com.xhf.wholeproject.viewInterface.RegisteredView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***
 *Date：21-6-30
 *
 *author:Xu.Mr
 *
 *content:
 */
public class RegisteredActivity extends BaseActivity implements RegisteredView {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.tv_statment)
    TextView tvStatment;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_getNum)
    TextView tvGetNum;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_newpassword)
    EditText etNewpassword;
    @BindView(R.id.but_register)
    Button butRegister;
    private TimeCountUtil mTimeCount;
    private Boolean isGetCode = true;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        tvTitle.setText(getResources().getText(R.string.register));
        CommonUtils.onStringWatcher(etNewpassword, 10);
        CommonUtils.onStringWatcher(etPassword, 10);

        CommonUtils.setHint(etNewpassword, getResources().getText(R.string.register_newpassword), 10);
        CommonUtils.setHint(etPassword, getResources().getText(R.string.register_password), 10);
        CommonUtils.setHint(etNum, getResources().getText(R.string.register_num), 10);
        CommonUtils.setHint(etPhone, getResources().getText(R.string.register_phonenum), 10);
        CommonUtils.setHint(etNickname, getResources().getText(R.string.register_name), 10);

    }


    @Override
    public void onRegisteredSuccess() {
        readyGoCleanAll(MainActivity.class);
    }

    @Override
    public void onErrorNum() {
        showToast(getResources().getString(R.string.register_errornum));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.but_register, R.id.tv_getNum, R.id.tv_statment, R.id.ll_back})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.but_register://注册
                String nickName = etNickname.getText().toString().trim();

                if (TextUtils.isEmpty(nickName)) {
                    showToast(getResources().getString(R.string.register_name));
                    return;
                }

                String phoneNum = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNum)) {
                    showToast(getResources().getString(R.string.register_phonenum));
                    return;
                }
                if (!CommonUtils.isChinaPhoneLegal(phoneNum)) {
                    showToast(getResources().getString(R.string.register_error_phonenum));
                    return;
                }

                String num = etNum.getText().toString().trim();
                if (TextUtils.isEmpty(num)) {
                    showToast(getResources().getString(R.string.register_num));
                    return;
                }


                String passWord = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(passWord)) {
                    showToast(getResources().getString(R.string.register_password));
                    return;
                }

                String passNewWord = etNewpassword.getText().toString().trim();
                if (TextUtils.isEmpty(passNewWord)) {
                    showToast(getResources().getString(R.string.register_newpassword));
                    return;
                }
                if (!passNewWord.equals(passWord)) {
                    showToast(getResources().getString(R.string.register_errorPassword));
                }



                break;
            case R.id.tv_getNum://获取验证码
                String phone = etPhone.getText().toString().trim();
                if (!TextUtils.isEmpty(phone) && CommonUtils.isChinaPhoneLegal(phone)) {
                    startTime();
                }
                break;
            case R.id.tv_statment://说明
                break;
            case R.id.ll_back:
                finish();
                break;

        }
    }

    public void startTime() {
        mTimeCount = new TimeCountUtil(60000, 1000, tvGetNum) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (tvGetNum != null) {
                    tvGetNum.setEnabled(false);
                    tvGetNum.setTextColor(getResources().getColor(R.color.gray));
                    tvGetNum.setBackgroundResource(R.drawable.shape_booktitle);
                    tvGetNum.setText(millisUntilFinished / 1000 + "S");
                    isGetCode = false;
                }
            }

            @Override
            public void onFinish() {
                if (tvGetNum != null) {
                    isGetCode = true;
                    tvGetNum.setText(getResources().getText(R.string.register_getnum));
                    tvGetNum.setTextColor(getResources().getColor(R.color.white));
                    tvGetNum.setBackgroundResource(R.drawable.shape_login_radius_10);
                    tvGetNum.setEnabled(true);
                }
            }
        };
        if (isGetCode)
            mTimeCount.start();
    }


}

