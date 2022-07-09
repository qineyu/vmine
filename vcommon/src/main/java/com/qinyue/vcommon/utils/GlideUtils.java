package com.qinyue.vcommon.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

/**
 * @ClassName: GlideUtils
 * @Description:
 * @Author: bodia
 * @Date: 2022/7/9
 */
public class GlideUtils {

    /**
     * 加载图片
     *
     * @param view
     * @param url
     * @param placeRes
     * @param errRes
     */
    public static void loadImage(ImageView view, String url, int placeRes, int errRes) {
        Glide.with(view.getContext().getApplicationContext())
                .load(url)
                .apply(options(placeRes, errRes))
                .into(view);
    }

    public static void loadImageCenter(ImageView view, String url, int placeRes, int errRes) {
        Glide.with(view.getContext().getApplicationContext())
                .load(url)
                .centerCrop()
                .apply(options(placeRes, errRes))
                .into(view);
    }

    public static void loadImageTransition(ImageView view, String url, int placeRes, int errRes) {
        Glide.with(view.getContext().getApplicationContext())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(350))
                .apply(options(placeRes, errRes))
                .into(view);
    }


    public static void loadImagePreload(String url) {
        Glide.with(XUtil.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.DATA).preload();
    }

    /**
     * 加载图片
     *
     * @param view
     * @param url
     */
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext().getApplicationContext())
                .load(url)
                .apply(options(0, 0))
                .into(view);
    }

    /**
     * 加载gif
     *
     * @param view
     * @param url
     * @param placeId
     * @param errorId
     */
    public static void loadGif(ImageView view, String url, int placeId, int errorId) {
        Glide.with(view.getContext().getApplicationContext())
                .asGif()
                .load(url)
                .apply(options(placeId, errorId))
                .into(view);
    }

    /**
     * 加载圆图gif
     *
     * @param view
     * @param url
     * @param placeId
     * @param errorId
     */
    public static void loadCircleGif(ImageView view, String url, int placeId, int errorId) {
        Glide.with(view.getContext().getApplicationContext())
                .asGif()
                .load(url)
                .skipMemoryCache(true)
                .dontAnimate()
                .apply(options(placeId, errorId))
                .transform(new CircleCrop())
                .into(view);
    }

    /**
     * 加载圆图gif
     *
     * @param view
     * @param url
     */
    public static void loadCircleGif(ImageView view, String url) {
        Glide.with(view.getContext().getApplicationContext())
                .asGif()
                .load(url)
                .skipMemoryCache(true)
                .dontAnimate()
                .transform(new CircleCrop())
                .into(view);
    }
    /**
     * 加载圆图gif
     *
     * @param view
     * @param url
     * @param placeId
     * @param errorId
     */
    public static void loadCircleGif(ImageView view, String url, int placeId, int errorId,int dp) {
        Glide.with(view.getContext().getApplicationContext())
                .asGif()
                .load(url)
                .apply(options(placeId, errorId))
                .transform(roundTransform(view,dp))
                .into(view);
    }

    /**
     * 兼容ImageView scaleType 为 CENTER_CROP
     *
     * @param view
     * @param dp
     * @return
     */
    private static BitmapTransformation[] roundTransform(ImageView view, int dp) {
        int radius = DensityUtils.dip2px(view.getContext(), dp);
        BitmapTransformation[] transforms = null;
        if (view.getScaleType() == ImageView.ScaleType.CENTER_CROP) {
            transforms = new BitmapTransformation[]{new CenterCrop(), new RoundedCorners(radius)};
            return transforms;
        } else {
            transforms = new BitmapTransformation[]{new RoundedCorners(radius)};
            return transforms;
        }
    }

    /**
     * 加载圆角图片 （圆角默认4dp）
     *
     * @param view
     * @param url
     * @param placeId
     * @param errorId
     */
    public static void roundImage(ImageView view, String url, int placeId, int errorId) {
        Glide.with(view.getContext().getApplicationContext())
                .load(url)
                .apply(options(placeId, errorId))
                .transform(roundTransform(view, 4))
                .into(view);
    }
    /**
     * 加载圆角图片
     *
     * @param view
     * @param url
     * @param placeId
     * @param errorId
     * @param dp      圆角
     */
    public static void roundImage(ImageView view, String url, int placeId, int errorId, int dp) {
        Glide.with(view.getContext().getApplicationContext())
                .load(url)
                .apply(options(placeId, errorId))
                .transform(roundTransform(view, dp))
                .into(view);
    }
    /**
     * 加载圆角图片
     *
     * @param view
     * @param url
     * @param placeId
     * @param errorId
     * @param dp      圆角
     */
    public static void roundImageCenterCrop(ImageView view, String url, int placeId, int errorId, int dp) {
        Glide.with(view.getContext().getApplicationContext())
                .load(url)
                .apply(options(placeId, errorId))
                .transform(new CenterCrop())
                .transform(roundTransform(view, dp))
                .into(view);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param placeId
     * @param errorId
     * @param callback
     */
    public static void loadImage(Context context, String url, int placeId, int errorId, SimpleTarget<Drawable> callback) {
        Glide.with(context)
                .load(url)
                .apply(options(placeId, errorId))
                .into(callback);
    }

    public static RequestOptions options(int placeId, int errorId) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeId)
                .error(errorId);
        return requestOptions;
    }

    /**
     * 清理磁盘缓存，需在子线程中执行
     *
     * @param context
     */
    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    /**
     * 清理内存缓存
     *
     * @param context
     */
    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }


}
