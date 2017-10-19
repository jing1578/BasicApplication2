package org.jing1578.basicapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.jing1578.basicapplication.service.LongRunningService;

/**
 * Created by jing1578 on 2016/9/23.
 */

public class AlarmReceiver extends BroadcastReceiver {//处理定时任务

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=new Intent(context,LongRunningService.class);
        context.startService(i);
    }
}