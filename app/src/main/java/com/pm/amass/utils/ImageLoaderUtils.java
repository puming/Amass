package com.pm.amass.utils;

import android.util.Log;
import android.widget.ImageView;

import com.common.imageloader.ImageLoader;
import com.common.imageloader.glide.GlideImageLoaderConfig;
import com.common.imageloader.glide.GlideImageLoaderStrategy;
import com.common.utils.DensityUtil;
import com.pm.amass.BuildConfig;
import com.pm.amass.MainApplication;
import com.pm.amass.R;

/**
 * @author pm
 * @date 2019/1/25
 * @email puming@zdsoft.cn
 */
public class ImageLoaderUtils {
    private static final String TAG = "ImageLoaderUtils";

    public static void bindImage(ImageView imageView, String url, int size) {
        ImageLoader imageLoader = MainApplication.getAppComponent().getImageLoader();
        imageLoader.setImageLoaderStrategy(new GlideImageLoaderStrategy());
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "bindImage: image url : " + url);
        }
        GlideImageLoaderConfig loaderConfig = GlideImageLoaderConfig.builder()
                .imageView(imageView)
                .url(url)
                .errorPic(R.mipmap.perloader_icon)
                .fallback(R.mipmap.perloader_icon)
                .placeholder(R.mipmap.perloader_icon)
                .resize(DensityUtil.dp2px(imageView.getContext(), size), DensityUtil.dp2px(imageView.getContext(), size))
                .build();
        imageLoader.loadImage(imageView.getContext(), loaderConfig);
    }

    public static void bindImage(ImageView imageView, String url) {
        ImageLoader imageLoader = MainApplication.getAppComponent().getImageLoader();
        imageLoader.setImageLoaderStrategy(new GlideImageLoaderStrategy());
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "bindImage: image url : " + url);
        }
        GlideImageLoaderConfig loaderConfig = GlideImageLoaderConfig.builder()
                .imageView(imageView)
                .url(url)
                .errorPic(R.mipmap.perloader_icon)
                .fallback(R.mipmap.perloader_icon)
                .placeholder(R.mipmap.perloader_icon)
                .build();
        imageLoader.loadImage(imageView.getContext(), loaderConfig);
    }

}
