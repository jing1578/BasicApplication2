package com.tcmain.zxinglibrary.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by risen on 16/5/5.
 */
public class WindowsManagerUtil {
    private Context mContext;
    private WindowManager wm;
    private Display dy;
    private DisplayMetrics outMetrics;

    public WindowsManagerUtil(Context context) {
        this.mContext = context;
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        dy = wm.getDefaultDisplay();
        outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getWindowsWidth() {
//        return dy.getWidth();
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public int getWindowsHeight() {
//        return dy.getHeight();
        return outMetrics.heightPixels;
    }

    /**
     * 获取应用区域的高度
     *
     * @param activity
     * @return
     */
    public int getAppclitionHeight(Activity activity) {
        // 用户绘制区域
        Rect outRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        return outRect.height() - dip2px(75);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
