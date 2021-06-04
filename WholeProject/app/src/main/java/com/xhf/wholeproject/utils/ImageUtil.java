package com.xhf.wholeproject.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.xhf.wholeproject.R;

/***
 *Date：2021/3/4
 *
 *author:Xu.Mr
 *
 *content:图片加载方法
 */
public class ImageUtil {


    public static void oShowImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.image_error)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.image_error)
                .placeholder(R.drawable.image_placeholder)
                .transform(new CircleTransform(context, 2, Color.DKGRAY))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(url)
                .apply(options)

                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param radius
     */
    public static void loadRoundImage(Context context, String url, ImageView imageView, int radius) {
        RequestOptions options = new RequestOptions()
                .error(R.drawable.image_error)
                .placeholder(R.drawable.image_placeholder)
                .transform(new CircleBeadTransform(context, 2, Color.DKGRAY))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(url)
                .apply(options)//展位图
                .into(imageView);
    }


}
