package com.xhf.wholeproject.view.fragment;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BaseFragment;
import com.xhf.wholeproject.presenter.impl.HomeFPresenterImpl;
import com.xhf.wholeproject.utils.ImageUtil;
import com.xhf.wholeproject.utils.ManagerUtil;
import com.xhf.wholeproject.utils.MyTTS;
import com.xhf.wholeproject.utils.VoicePlayer;
import com.xhf.wholeproject.utils.VoiceUnit;
import com.xhf.wholeproject.view.adapter.HomeFocusListAdapter;
import com.xhf.wholeproject.viewInterface.HomeFView;

import java.util.ArrayList;
import java.util.List;

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
public class HomeFragment extends BaseFragment implements HomeFView {
    @BindView(R.id.tv_read)
    TextView tvRead;
    @BindView(R.id.tv_read2)
    TextView tvRead2;
    @BindView(R.id.rlv_Focuslist)
    ViewPager rlv_Focuslist;
    @BindView(R.id.xb_list)
    XBanner xb_list;
    private MyTTS tts;
    private VoicePlayer voicePlayer;
    private String testText = "这是另一种语音播放的方式";
    private String[] list = {"第一页", "第二页", "第三页"};
    private List imgesUrl;
    private HomeFPresenterImpl homeFPresenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();


        homeFPresenter = new HomeFPresenterImpl(getActivity(), this);
        homeFPresenter.onBannerValue();
        tts = MyTTS.getInstance(getActivity());
        voicePlayer = VoicePlayer.getInstance(getActivity());
        // 指定皮肤插件
        tvRead.setTextColor(getResources().getColor(R.color.sandybrown));


//        xb_list.stopAutoPlay();

//        rlv_Focuslist.setLayoutManager(ManagerUtil.getHorizonalManager(getActivity(), true));
//        HomeFocusListAdapter adapter = new HomeFocusListAdapter(getActivity(), list);
//        rlv_Focuslist.scrollToPosition(list.length / 2);
//        rlv_Focuslist.set

//
//        rlv_Focuslist.setAdapter(adapter);
//        if(rlv_Focuslist.isFocusable()){
//            adapter.setOnSetFocusListener(new HomeFocusListAdapter.OnSetFocusListener() {
//                @Override
//                public void addFocusListener() {
//
//                }
//            });
//        }
//        rlv_Focuslist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                adapter.getItemCount();
//
//            }
//        });

        rlv_Focuslist.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView = new ImageView(getActivity());
                // Glide.with(getActivity()).load(imgesUrl.get(position)).into(imageView);
                // 添加到ViewPager容器
                container.addView(imageView);

                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        rlv_Focuslist.setCurrentItem(1);
        rlv_Focuslist.setOffscreenPageLimit(3);
        rlv_Focuslist.setPageMargin(5);
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

    @Override
    public void onBanner(ArrayList<String> list) {
        xb_list.setIsClipChildrenMode(true);
        xb_list.setData(list, null);//第二个参数为提示文字资源集合
        xb_list.setmAdapter((banner, model, view, position) -> Glide.with(getActivity()).load(list.get(position)).into((ImageView) view));
    }
}
