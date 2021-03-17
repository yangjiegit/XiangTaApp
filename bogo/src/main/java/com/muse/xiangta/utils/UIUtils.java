package com.muse.xiangta.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.muse.xiangta.CuckooApplication;


/**
 * @author Created by WZ on 2015年12月30日
 */
public class UIUtils {
    private static Toast toast;
//	private static CToast toast;

    /**
     * 获取Context
     */
    public static Context getContext() {
        return CuckooApplication.getInstance();
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        return CuckooApplication.getMainHandler();
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
        // return getResources().getDimension(id);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }

    public static Thread getMainThread() {
        return CuckooApplication.getMainThread();
    }

    public static long getMainThreadId() {
        return CuckooApplication.getMainThreadId();
    }

    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }


    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight() {
        return getWindowManager().getDefaultDisplay().getHeight();
    }

    public static int getScreenWidth() {
        return getWindowManager().getDefaultDisplay().getWidth();
    }

    private static WindowManager getWindowManager() {
        return (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
    }

    /**
     * 线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final int resId) {
        showToastSafe(getString(resId));
    }

    /**
     * 线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final String str) {
        if (isRunInMainThread()) {
            showToast(str);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(str);
                }
            });
        }
    }

    /**
     * 显示吐司
     */
    public static void showToast(String str) {
        //之前的吐司
//        ToastUtils.showNormal(str);
        Toast.makeText(CuckooApplication.getInstance(), str, Toast.LENGTH_SHORT).show();
        //新的吐司,有些手机把NotificationManagerService权限关闭了,所以要根据情况自行选择吐司
//        ToastFactory.getInstance(getContext()).makeTextShow(str, Toast.LENGTH_SHORT);
    }
}
