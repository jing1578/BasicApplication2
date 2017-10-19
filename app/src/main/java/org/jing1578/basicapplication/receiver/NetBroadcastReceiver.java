package org.jing1578.basicapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.jing1578.basicapplication.applicattion.MyApplication;
import org.jing1578.basicapplication.utils.NetUtil;
import org.jing1578.basicapplication.listener.NetStateListener;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Administrator
 * @date 2017/6/22
 */

public class NetBroadcastReceiver extends BroadcastReceiver {
    public static HashSet<NetStateListener> mListeners = new HashSet<NetStateListener>();
    // 此广播监听的是网络状态是否改变
    public static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    static volatile int state = -1;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(NET_CHANGE_ACTION)) {
            MyApplication.mNetState = NetUtil.getNetworkState(context);
            Log.e("NetBroadcastReceiver", MyApplication.mNetState + "-netstate------------this-" + mListeners.size());
            if (mListeners.size() > 0 && MyApplication.mNetState != state) {// 通知接口加载完成
                Log.e("NetBroadcastReceiver",
                        "网络状态改变:" + intent.getExtras().toString());
                state = MyApplication.mNetState;
                for (NetStateListener netStateListener : mListeners) {
                    Log.e("NetBroadcastReceiver", "---------" + state);
                    netStateListener.onNetChange();
                }
            }
        }
    }
}
