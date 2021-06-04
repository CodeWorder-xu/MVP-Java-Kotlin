package com.xhf.wholeproject.view.fragment;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BaseFragment;
import com.xhf.wholeproject.utils.MyTTS;
import com.xhf.wholeproject.utils.VoicePlayer;
import com.xhf.wholeproject.utils.VoiceUnit;

import butterknife.BindView;
import butterknife.OnClick;
import skin.support.SkinCompatManager;

/***
 *Date：2021/3/23
 *
 *author:Xu.Mr
 *
 *content:
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_read)
    TextView tvRead;
    @BindView(R.id.tv_read2)
    TextView tvRead2;
    private MyTTS tts;
    private VoicePlayer voicePlayer;
    private String testText = "这是另一种语音播放的方式";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        tts = MyTTS.getInstance(getActivity());
        voicePlayer = VoicePlayer.getInstance(getActivity());
        // 指定皮肤插件
        tvRead.setTextColor(getResources().getColor(R.color.sandybrown));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.tv_read, R.id.tv_read2})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_read:
                SkinCompatManager.getInstance().loadSkin("resSkin.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
                if (voicePlayer.isPlaying()) {
                    voicePlayer.stop();
                }
                if (tts.isSupportCN()) {
                    tts.getTts().speak("这是一条测试语音", TextToSpeech.QUEUE_ADD, null, null);
                }
                break;

            case R.id.tv_read2:
                boolean paly = tts.isPaly();
                if (paly) {
                    tts.isStop();
                }
                SkinCompatManager.getInstance().restoreDefaultTheme(); //恢复默认皮肤
                voicePlayer.addVoiceUnit(new VoiceUnit(new int[]{R.raw.app_src_main_res_raw_test}));
                voicePlayer.play();
                break;
            default:

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        tts.getTts().stop();
        voicePlayer.destory();
    }
}
