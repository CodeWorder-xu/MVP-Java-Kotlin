package com.xhf.wholeproject.view.activity;

import android.app.KeyguardManager;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BaseActivity;
import com.xhf.wholeproject.presenter.impl.SplashPresenterImpl;
import com.xhf.wholeproject.utils.SPManager;
import com.xhf.wholeproject.view.fragment.FingerprintDialogFragment;
import com.xhf.wholeproject.viewInterface.SplashView;

import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import butterknife.BindView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;

import static com.xhf.wholeproject.constant.Constant.PHONETYPE;

/***
 *Date：2021/3/22
 *
 *author:Xu.Mr
 *
 *content:闪屏页
 */
public class SplashActivity extends BaseActivity implements SplashView {
    @BindView(R.id.img_splash)
    ImageView imgSplash;
    private SplashPresenterImpl splashPresenter;
    private boolean isAnimationEnd = false; //动画是否结束
    private boolean isUpdate = false; //是否更新
    KeyStore keyStore;
    private static final String DEFAULT_KEY_NAME = "default_key";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        splashPresenter = new SplashPresenterImpl(this, this);
        if (PHONETYPE == 1) { //手机
            addDisposable(Flowable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(aLong -> {
                splashPresenter.onGetSPValue();
            }));
        } else {
            //电视
            setAnimationScale(imgSplash);
        }

    }

    @Override
    public void onToMain() {
        SPManager.setBoolean(this, "isgray", false);
//
        if (supportFingerprint()) {
            initKey();
            initCipher();
        } else {
            readyGoThenKill(MainActivity.class);
        }
    }

    @Override
    public void onToNavigation() {
        SPManager.setBoolean(this, "isgray", true);
//        readyGoThenKill(GuidePageActivity.class);
        readyGoThenKill(MainActivity.class);
    }


    /**
     * 设置缩放动画
     */
    public void setAnimationScale(ImageView v) {
        final ScaleAnimation animation = new ScaleAnimation(1.0f, 1.05f, 1.0f, 1.05f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(3000); //设置动画持续时间
        v.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimationEnd = true;
                splashPresenter.onGetSPValue();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation.startNow();
    }

    public boolean supportFingerprint() {
        if (Build.VERSION.SDK_INT < 23) {
            Toast.makeText(this, "您的系统版本过低，不支持指纹功能", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            KeyguardManager keyguardManager = getSystemService(KeyguardManager.class);
            FingerprintManager fingerprintManager = getSystemService(FingerprintManager.class);
            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(this, "您的手机不支持指纹功能", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!keyguardManager.isKeyguardSecure()) {
                Toast.makeText(this, "您还未设置锁屏，请先设置锁屏并添加一个指纹", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "您至少需要在系统设置中添加一个指纹", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void initKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(DEFAULT_KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);
            keyGenerator.init(builder.build());
            keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void initCipher() {
        try {
            SecretKey key = (SecretKey) keyStore.getKey(DEFAULT_KEY_NAME, null);
            Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            showFingerPrintDialog(cipher);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showFingerPrintDialog(Cipher cipher) {
        FingerprintDialogFragment fragment = new FingerprintDialogFragment();
        fragment.setCipher(cipher);
        fragment.show(getSupportFragmentManager(), "fingerprint");
    }

    public void onAuthenticated() {
        readyGoThenKill(MainActivity.class);

    }

}
