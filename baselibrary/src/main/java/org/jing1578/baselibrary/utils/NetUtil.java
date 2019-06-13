package org.jing1578.baselibrary.utils;


import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;

import org.jing1578.baselibrary.R;


/**
 * 网络检测工具类
 * Created by Administrator on 2017/6/22.
 */

public class NetUtil {

    public static int mNetState;
    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 2;


    private static AlertDialog dialog;
    public static void onNetChange(Context context) {
        if (mNetState == NetUtil.NETWORN_NONE
                && !NetUtil.checkNetworkState(context)) {
//            ActivityHelper.showTips("网络不可用");
            if(dialog==null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomDialog);
                dialog = builder.create();
            }
            if (!dialog.isShowing()) {
                dialog.show();
                View view = LayoutInflater.from(context).inflate(R.layout.dialog, null);
                toNetwork(context,view);
//		        dialog.setCancelable(false);// 设置对话框不可取消
                dialog.setCanceledOnTouchOutside(false);// 阻断对话框的边缘触摸事件
                dialog.setContentView(view);
                dialog.getWindow().setLayout(500, 260);
            }
        } else if (mNetState == NetUtil.NETWORN_MOBILE) {
//            ActivityHelper.showTips("当前为手机网络");
        } else if (mNetState == NetUtil.NETWORN_WIFI) {
//            ActivityHelper.showTips("当前为wifi网络");
        }
    }


    @SuppressWarnings("deprecation")
    public static int getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo.State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return NETWORN_WIFI;
        }

        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState();
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return NETWORN_MOBILE;
        }
        return NETWORN_NONE;
    }

    /**
     * 创建设置对话框
     */

    private static void toNetwork(final Context context, View view) {
        view.findViewById(R.id.dialog_button_ok).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Intent intent = null;
                        /**
                         * 判断手机系统的版本！如果API大于10 就是3.0+ 因为3.0以上的版本的设置方法不同
                         */
                        if (android.os.Build.VERSION.SDK_INT > 10) {
                            intent = new Intent(
                                    android.provider.Settings.ACTION_WIFI_SETTINGS);
                        } else {
                            intent = new Intent();
                            ComponentName component = new ComponentName(
                                    "com.android.settings",
                                    "com.android.settings.WirelessSettings");
                            intent.setComponent(component);
                            intent.setAction("android.intent.action.VIEW");
                        }
                        context.startActivity(intent);
                    }
                });

    }

    /**
     * 检测网络是否连接 有些地方可单独使用
     */
    public static boolean checkNetworkState(Context context) {
        boolean flag = false;
        // 得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // 去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isConnected();
        }
        return flag;
    }


}
