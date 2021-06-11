package com.xhf.wholeproject.net.network;


import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.xhf.wholeproject.base.BaseAppManager;
import com.xhf.wholeproject.base.BaseResEntity;
import com.xhf.wholeproject.constant.MyApplication;
import com.xhf.wholeproject.constant.UserManager;
import com.xhf.wholeproject.model.entity.res.RefreshTokenEntity;
import com.xhf.wholeproject.net.service.MyService;
import com.xhf.wholeproject.net.status.NetUtils;
import com.xhf.wholeproject.utils.StringUtil;
import com.xhf.wholeproject.utils.TLog;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.xhf.wholeproject.constant.DB_Key.USER_TOKEN;
import static com.xhf.wholeproject.net.network.HttpCode.INTERNAL_SERVER_ERROR;
import static com.xhf.wholeproject.net.network.HttpCode.TOKEN_FALSE;

/**
 * 网络请求
 */
public class HttpUtil {
    private static final String TAG = HttpUtil.class.getName();
    private static final String TOKEN_TIMEOUT = "TokenTimeout";

    /**
     * 请求结果
     *
     * @param <N>
     */
    public interface OnResponseListener<N> {
        void onResponse(N response);
    }

    /**
     * 请求失败
     */
    public interface OnFailureListener {
        void onFailure(int code, Throwable t);
    }

    /**
     * 提供数据源
     *
     * @param <N>
     */
    public interface OnObservableLintener<N> {
        Observable<N> onObserver();
    }

    private Disposable mSubscription;
    private OnFailureListener mOnFailureListener;
    private OnResponseListener mOnResponseListener;
    private OnObservableLintener mOnObservableLintener;

    /**
     * 请求返回的Observable
     */
    private Observable mResObservable;

    private MyService mUserService;


    public HttpUtil() {

        Retrofit retrofit = new RetrofitUtil.Builder().build();
        mUserService = retrofit.create(MyService.class);
    }

    public HttpUtil setOnObservableLintener(OnObservableLintener onObservableLintener) {
        this.mOnObservableLintener = onObservableLintener;
        mResObservable = mOnObservableLintener.onObserver();
        return this;
    }

    public HttpUtil setOnFailureListener(OnFailureListener onFailureListener) {
        mOnFailureListener = onFailureListener;
        return this;
    }

    public <N> HttpUtil setOnResponseListener(OnResponseListener<N> onResponseListener) {
        mOnResponseListener = onResponseListener;
        return this;
    }

    //TODO 网络超时，待优化
    public <T> void start() {
        if (!NetUtils.isNetworkAvailable(MyApplication.getInstance())) {
            mOnFailureListener.onFailure(-1, new Throwable("网络不可用！"));
            return;
        }
        mResObservable.flatMap(new Function<BaseResEntity<T>, Observable<BaseResEntity<T>>>() {
            @Override
            public Observable<BaseResEntity<T>> apply(BaseResEntity<T> baseResEntity) throws Exception {
//                        Log.e(TAG, "apply:  ==  "+baseResEntity.toString());
                switch (baseResEntity.getCode()) {
                    case HttpCode.SUCCESS:
                        TLog.e(TAG, "zzzzz成功");
                        return Observable.just(baseResEntity);
                    case HttpCode.TOKENTIMEOUT:

                        TLog.e(TAG, "Token过期");
//                                return Observable.error(new NullPointerException(TOKEN_TIMEOUT));
                        return Observable.error(new Exception(String.valueOf(baseResEntity.getCode()), new Throwable(TOKEN_TIMEOUT)));
                    case 60000:
                        String refreshToken = UserManager.getInstance().getRefreshToken();
                        mUserService.refreshToken(StringUtil.makeNumbers(), refreshToken, String.valueOf(1))

                                .doOnNext(new Consumer<BaseResEntity<RefreshTokenEntity>>() {
                                    @Override
                                    public void accept(BaseResEntity<RefreshTokenEntity> entity) throws Exception {
                                        final RefreshTokenEntity tokenModelBean = entity.getResult();
                                        Hawk.put(USER_TOKEN, tokenModelBean.getToken());
                                    }
                                });
                        return Observable.just(baseResEntity);
                    default:
                        TLog.e(TAG, "zzzzzz" + String.valueOf(baseResEntity.getCode()));
                        return Observable.error(new Exception(String.valueOf(baseResEntity.getCode()), new Throwable(baseResEntity.getMessage())));
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }

    Observer<BaseResEntity<RefreshTokenEntity>> observer = new Observer<BaseResEntity<RefreshTokenEntity>>() {
        @Override
        public void onSubscribe(Disposable d) {

            mSubscription = d;
        }

        @Override
        public void onNext(BaseResEntity<RefreshTokenEntity> entity) {

            if (mOnResponseListener != null) {
                mOnResponseListener.onResponse(entity.getResult());
            }
        }

        @Override
        public void onError(Throwable e) {
            TLog.e(TAG, "onError  == " + e.getMessage());
            if (mOnFailureListener != null) {
                int errCode;
                Throwable throwable;
                if (e.getMessage() != null && StringUtil.isNum(e.getMessage())) {
                    errCode = Integer.valueOf(e.getMessage());
                    throwable = e.getCause() == null ? new Throwable("系统内部错误") : e.getCause();
                } else {
                    errCode = INTERNAL_SERVER_ERROR;
                    throwable = new Throwable(e.getMessage());
                }
                TLog.e(TAG, "errCode:" + errCode + " Message:" + throwable.getMessage());
                switch (errCode) {
                    case HttpCode.TOKENTIMEOUT:
                        String refreshToken = UserManager.getInstance().getRefreshToken();
                        mUserService.refreshToken(StringUtil.makeNumbers(), refreshToken, String.valueOf(1));
                        break;
                    case TOKEN_FALSE:
                        MyApplication.getInstance().logout(BaseAppManager.getInstance().getForwardActivity(), throwable.getMessage());
                        break;
                    default:
                        mOnFailureListener.onFailure(errCode, throwable);
                        break;

                }

            }
        }

        @Override
        public void onComplete() {

        }
    };

    public void cancel() {
        if (mSubscription != null && !mSubscription.isDisposed()) {
            mSubscription.dispose();
        }
    }

}
