package com.xhf.wholeproject.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xhf.wholeproject.R;
import com.xhf.wholeproject.base.BaseActivity;
import com.xhf.wholeproject.utils.SPManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/***
 *Date：2021/3/4
 *
 *author:Xu.Mr
 *
 *content:引导页
 */
public class GuidePageActivity extends BaseActivity {
    @BindView(R.id.guide_view_pager)
    ViewPager guideViewPager;
    @BindView(R.id.guide_action_bt)
    Button guideActionBt;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_guidepage;
    }

    int[] resourcesId = {R.drawable.guide_page01, R.drawable.guide_page02, R.drawable.guide_page03, R.drawable.guide_page04};

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        guideViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == resourcesId.length - 1) {
                    guideActionBt.setVisibility(View.VISIBLE);
                } else {
                    guideActionBt.setVisibility(View.GONE);
                }
            }
        });
        guideViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return resourcesId.length;
            }

            /**
             * 确认View与实例对象是否相互对应。ViewPager内部用于获取View对应的ItemInfo。
             * <p>
             * 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
             */
            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            /**
             * 为给定的位置创建相应的View。创建View之后,需要在该方法中自行添加到container中。
             * <p>
             * 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
             */
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(mContext);
                imageView.setImageResource(resourcesId[position]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                // 添加到ViewPager容器
                container.addView(imageView);
                return imageView;
            }

            /**
             * 为给定的位置移除相应的View。
             * <p>
             * PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
             */
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.guide_action_bt)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guide_action_bt:
                SPManager.setBoolean(GuidePageActivity.this, "isFirst", true);
                readyGoThenKill(MainActivity.class);
                break;
            default:
        }
    }
}
