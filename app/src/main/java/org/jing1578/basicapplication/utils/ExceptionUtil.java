package org.jing1578.basicapplication.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;


import org.jing1578.basicapplication.applicattion.MyApplication;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;



/**
 * Created by Administrator on 2016/11/2 0002.
 * 异常处理类,将异常上传到友盟异常处理,待接入友盟统计
 */
public class ExceptionUtil implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "ExceptionUtil";

    // CrashHandler 实例
    private static ExceptionUtil INSTANCE = new ExceptionUtil();

    // 程序的 Context 对象
    private Context mContext;
// app对象
    private MyApplication app;

    // 系统默认的 UncaughtException 处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /** 保证只有一个 CrashHandler 实例 */
    private ExceptionUtil() {
    }

    /** 获取 CrashHandler 实例 ,单例模式 */
    public static ExceptionUtil getInstance() {
        return INSTANCE;
    }

    /**
     * @Title: init
     * @Description: 初始化
     * @param context
     * @param app
     *            传入的app
     * @throws
     */
    public void init(Context context, MyApplication app) {
        // 传入app对象，为完美终止app
        this.app = app;
        mContext = context;
        // 获取系统默认的 UncaughtException 处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该 CrashHandler 为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当 UncaughtException 发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            // 释放资源不能像常规的那样在activity的onDestroy方法里面执行，因为如果出现全局异常捕获，activity的关闭有时候是不会再走相关的生命周期函数的（onDesktroy,onStop,onPause等）。
            // 这里是博主在退出app之前需要释放掉的一些资源，通过之前讲的AppActivityManager来拿到对应的实例activity释放里面的资源，然后调用AppExit退出应用程序
//            ProductActivity activitys = (ProductActivity) AppActivityManager
//                    .getAppActivityManager().getActivity(ProductActivity.class);
//            activitys.offline();
//            MainActivity activity = (MainActivity) AppActivityManager
//                    .getAppActivityManager().getActivity(MainActivity.class);
//            activity.offline();
//            // 当执行这一句的时候，其实APP有时候并没有完美的退出（方法详情可以查看博主之前的写的activity管理的文章）
//            // 博主的项目里面有网络连接、有后台服务、多线程等各种。执行完这个方法之后，虽然能够闪退出去，但是，当再次进入APP的时候，是回出现ANR的，说明，这样还是没有的完美退出APP
//            AppActivityManager.getAppActivityManager().AppExit(mContext);
//            // 之前说application的时候说过，当app退出的时候，会执行onTerminate方法，但是有时候不会主动执行。那么，博主想，如果我们强制执行这个方法，能不能让app完美的终止呢?答案是肯定的。
//            app.onTerminate();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                LogUtil.e(TAG, "error : ", e);
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
     *
     * @param ex
     * @return true：如果处理了该异常信息；否则返回 false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 收集设备参数信息
        collectDeviceInfo(mContext);
        // 保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);

            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中 *
     *
     * @param ex
     * @return 返回文件名称,便于将文件传送到服务器
     */
    private void saveCrashInfo2File(Throwable ex) {
        //将错误信息发送到友盟统计
//        MobclickAgent.reportError(mContext,ex);
        ex.printStackTrace();
    }
}
