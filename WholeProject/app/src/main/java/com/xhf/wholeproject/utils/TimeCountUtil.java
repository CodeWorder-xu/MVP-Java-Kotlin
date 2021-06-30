package com.xhf.wholeproject.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/***
 *Date：21-6-30
 *
 *author:Xu.Mr
 *
 *content:倒计时
 */
public class TimeCountUtil extends CountDownTimer {
    private TextView mTv;

    public TimeCountUtil(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.mTv = tv;
    }

    @Override
    public void onFinish() {
        mTv.setText("获取验证码");
        mTv.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTv.setClickable(false);
        mTv.setText(millisUntilFinished / 1000 + "秒");
    }

    public void onDestroy(){
        cancel();
    }
}