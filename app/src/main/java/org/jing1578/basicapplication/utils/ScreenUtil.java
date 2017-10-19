
package org.jing1578.basicapplication.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtil {

    public static DisplayMetrics getScreenSize(Context context){
        WindowManager wm= (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
//        displayMetrics.widthPixels;
//        displayMetrics.heightPixels;
//        displayMetrics.density;
//        displayMetrics.densityDpi;
        return displayMetrics;
    }

    public static float getScreenDensity(Context context){
        WindowManager wm= (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        float density=displayMetrics.density;
        return density;
    }

    public static int dip2px(Context context, float var1) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(var1 * density + 0.5F);
    }

    public static int px2dip(Context context, float var1) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(var1 / density + 0.5F);
    }

    public static int sp2px(Context context, float var1) {
        float density = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(var1 * density + 0.5F);
    }

    public static int px2sp(Context context, float var1) {
        float density = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(var1 / density + 0.5F);
    }

    /**
     * 每英寸点数
     * @param var0
     * @return
     */
    public static float getDpi(Context var0){
        return var0.getResources().getDisplayMetrics().densityDpi;
    }

}
