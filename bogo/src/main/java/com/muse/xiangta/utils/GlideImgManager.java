package com.muse.xiangta.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.muse.xiangta.R;

import java.io.File;

/**
 * author：WangZhan
 * data: 2017/1/17 08:33
 * version: 1.0.0
 * explain:Glide操作工具类
 */

public class GlideImgManager {

    /**
     * load normal  for  circle or round img
     *
     * @param url
     * @param iv
     * @param tag 0圆形,1圆角
     */
    public static void glideLoader(Context context, String url, ImageView iv, int tag) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        if (tag == 0) {
            options.transform(new GlideCircleTransform(context));
        } else if (tag == 2) {
            options.transform(new GlideRoundTransform(context, 0));
        } else {
            options.transform(new GlideRoundTransform(context, 5));
        }
        Glide.with(context).load(url).apply(options).into(iv);
    }

    public static void glideLoaderFile(Context context, File file, ImageView iv, int tag) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        if (tag == 0) {
            options.transform(new GlideCircleTransform(context));
        } else if (tag == 100) {

        } else {
            options.transform(new GlideRoundTransform(context));
        }
        Glide.with(context).load(file).apply(options).into(iv);
    }

    public static void glideLoaderFile2(Context context, File file, ImageView iv, int tag) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
//        if (tag == 0) {
//            options.transform(new GlideCircleTransform(context));
//        } else if (tag == 100) {
//
//        } else {
//            options.transform(new GlideRoundTransform(context));
//        }
        Glide.with(context).load(file).apply(options).into(iv);
    }

    public static void glideLoaderBitmap(Context context, Bitmap bitmap, ImageView iv, int tag) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        if (tag == 0) {
            options.transform(new GlideCircleTransform(context));
        } else {
            options.transform(new GlideRoundTransform(context));
        }
        Glide.with(context).load(bitmap).apply(options).into(iv);
    }

    /**
     * load normal  for  circle or round img
     *
     * @param url
     * @param iv  圆形
     */
    public static void glideLoaderRoundBorder(Context context, String url, ImageView iv) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        options.transform(new GlideCircleTransform(context, 2, context.getResources().getColor(R.color.white)));
        Glide.with(context).load(url).apply(options).into(iv);
    }

    /**
     * load normal  for  circle or round img
     * 加载uri
     *
     * @param uri
     * @param iv
     * @param tag 0圆形,1圆角
     */
    public static void glideLoaderUri(Context context, Uri uri, ImageView iv, int tag) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        if (tag == 0) {
            options.transform(new GlideCircleTransform(context));
        } else {
            options.transform(new GlideRoundTransform(context));
        }
        Glide.with(context).load(uri).apply(options).into(iv);
    }

    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        RequestOptions options = RequestOptions.circleCropTransform()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        Glide.with(context).asBitmap().load(url).apply(options).into(imageView);
    }

    public static void loadHeadImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadBlurImage(Activity context, String url, ImageView imageView) {
//        RequestOptions options = RequestOptions.circleCropTransform();
//                .placeholder(R.mipmap.icon_place_holder)
//                .error(R.mipmap.icon_place_holder);
//        Glide.with(context).load(url).apply(options).into(imageView);
//        Glide.with(context).load(url)
//                .apply(bitmapTransform(new BlurTransformation(context, 25, 3)))
//                .into(imageView);
        Glide.with(context).load(url).into(imageView);
    }

    public static void loadImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadImage(Activity context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadImage(Fragment context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
