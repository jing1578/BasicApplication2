package org.jing1578.basicapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by jing1578 on 2016/12/8.
 */

public class ToastUtil {

    public static void showToast(Context context, String msg) {//2s
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static Toast showToastToast(Context context, String msg) {//统一管理Toast
        return Toast.makeText(context, msg, Toast.LENGTH_SHORT);
    }


    public static void longToast(Context context, String msg) {//3.5s
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static Toast longToastToast(Context context, String msg) {//统一管理Toast
        return Toast.makeText(context, msg, Toast.LENGTH_LONG);
    }
    /**
     *
     * @param activity
     * @param word
     * @param time
     */
    public static void showToast(final Activity activity, final String word, final long time){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Toast toast = Toast.makeText(activity, word, Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }

    /**
     *
     * @param activity
     * @param word
     * @param time
     */
    public static void longToast(final Activity activity, final String word, final long time){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Toast toast = Toast.makeText(activity, word, Toast.LENGTH_LONG);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }

    private static Toast mToast,mToastNormal;
    /**
     * 普通的toast提示
     * */
    public static void showNOrmalToast(Context mContext,String message){

        ToastUtil.cancel();

        if(mToastNormal == null){
            mToastNormal = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        }else{
            mToastNormal.setText(message);
        }

        mToastNormal.show();

    }
    /**
     *toast取消
     */
    public static void cancel(){
        if(mToast != null){
            mToast.cancel();
            mToast = null;
        }

    }
}
