package org.jing1578.basicapplication.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;


import org.jing1578.basicapplication.R;

import java.io.File;

/**
 * Created by jing1578 on 2016/12/8.
 */

public class Notificationutil {

    private Context context;

    public Notificationutil(Context context) {
        this.context=context;
    }

    public void init( ){
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder1 = new Notification.Builder(context);
        builder1.setSmallIcon(R.mipmap.ic_launcher); //设置图标
        builder1.setTicker("显示第二个通知");
        builder1.setContentTitle("通知"); //设置标题
        builder1.setContentText("点击查看详细内容"); //消息内容
        builder1.setWhen(System.currentTimeMillis()); //发送时间
        builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
//        Uri soundUri = Uri.fromFile(new File("/system/media/audio/ringtones/Basic_tone.ogg"));
//        builder1.setSound(soundUri);//播放音频
//        long[] vibrates = {0, 1000, 1000, 1000};
//        builder1.setVibrate(vibrates);//让手机在通知到来的时候立刻振动 1 秒，然后静止 1 秒，再振动 1 秒
//        builder1.setLights(Color.GREEN,1000,1000);//LED 灯以绿色的灯光一闪一闪

//        builder1.setAutoCancel(true);//打开程序后图标消失
//        Intent intent =new Intent (context,Center.class);
//        PendingIntent pendingIntent =PendingIntent.getActivity(context, 0, intent, 0);// getActivity()方法、getBroadcast()方法、还是 getService(),第二个参数一般设为0,第四个参数FLAG_ONE_SHOT、 FLAG_NO_CREATE、 FLAG_CANCEL_CURRENT 和 FLAG_UPDATE_CURRENT
//        builder1.setContentIntent(pendingIntent);
        Notification notification1;
        if (Build.VERSION.SDK_INT<16){
            notification1=builder1.getNotification();
        }else{
            notification1 = builder1.build();
        }
        notificationManager.notify(124, notification1); // 通过通知管理器发送通知,保证为每个通知所指定的 id 都是不同的
    }

    public void cancel(int id){
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(1);
    }


}
