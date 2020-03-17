package org.jing1578.basicapplication.applicattion;


import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.MutableContextWrapper;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.telephony.TelephonyManager;


import com.tencent.smtt.sdk.QbSdk;

import org.jing1578.baselibrary.utils.ExceptionUtil;
import org.jing1578.basicapplication.LoadUtil;


import java.util.Iterator;
import java.util.List;



public class MyApplication extends Application {

    public static Context applicationContext;
    public static int mNetState;

    public static String currentUserNick = "";

    private MyCore myCore;
    private MyPreference myPreference;

    /*//宿主代码
    private Resources resources;*/


    public static Context getContext() {
        return applicationContext;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        applicationContext=getApplicationContext();
        /*//获取新建的resources资源
        resources = LoadUtil.loadResource(this);*/
        //初始化腾讯x5服务
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        });
        initVar();
        ExceptionUtil exceptionUtil=ExceptionUtil.getInstance();
        exceptionUtil.init(this,MyApplication.this);


    }

    //判断当前的手机是否为wifi链接
    private boolean isWifiorMobile() {
        ConnectivityManager mConnectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mTelephony = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            return info.isConnected();
        } else {
            return false;
        }
    }

    private void initVar() {
        myCore = MyCore.instance();
        if (null == myPreference) {
            myPreference = new MyPreference(getApplicationContext());
        }
    }




    private String getAppName(int pid) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> l = am.getRunningAppProcesses();
        Iterator<ActivityManager.RunningAppProcessInfo> i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = i.next();
            if (info.pid == pid) {
                processName = info.processName;
                return processName;
            }
        }
        return processName;
    }

    public MyCore getMyCore() {
        return myCore;
    }

    public MyPreference getMyPreference() {
        return myPreference;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    /*// 重写该方法，当 resources 为空时，相当于没有重写，不为空时，返回新建的 resources 对象
    @Override
    public Resources getResources() {
        return resources == null?super.getResources():resources;
    }*/
}
