package org.jing1578.basicapplication.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jing1578.basicapplication.applicattion.MsgType;
import org.jing1578.basicapplication.applicattion.ServiceSupport;
import org.jing1578.basicapplication.receiver.AlarmReceiver;

/**
 * Created by jing1578 on 2016/9/23.
 * 服务最佳实践:后台定时任务 Timer ,Alarm  一个长期在后台运行的定时任务
 */

public class LongRunningService extends ServiceSupport {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new  Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG","OnStartCommand"+System.currentTimeMillis());
            }
        }).start();
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        long triggerAtTime= SystemClock.elapsedRealtime()+10000;
        Intent currentIntent=new Intent(this,AlarmReceiver.class);//指定处理定时任务
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,currentIntent,0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        return new DownloadBinder();
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    public class DownloadBinder extends Binder {
//
//        public void startDownload(){
//            Log.d("TAG","startDownload");
//        }
//
//        public  int getProgress(){
//            Log.d("TAG","getProgress");
//            return 0;
//        }
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Message msg) {
        switch (MsgType.values()[msg.what]) {
            default:
                break;
        }
    }

}
