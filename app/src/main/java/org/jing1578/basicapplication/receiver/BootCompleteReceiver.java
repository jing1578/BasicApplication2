package org.jing1578.basicapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by jing1578 on 2016/12/16.
 */

//广播分为标准广播和有序广播,有序广播为同步广播,接收器接收有次序,优先级高接收器先接收.标准广播为异步广播,同时被接收.

//发送标准广播  Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");   sendBroadcast(intent);
//发送有序广播  Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST"); sendOrderedBroadcast(intent, null);

//动态注册 静态注册android:priority="100" 广播接收器注册可设置优先级,优先级高可打断有序广播  abortBroadcast();

//不要在 onReceive()方法中添加过多的逻辑或者进行任何的耗时操作，因为在广播接收
//器中是不允许开启线程的， 当 onReceive()方法运行了较长时间而没有结束时， 程序就会报错。
//因此广播接收器更多的是扮演一种打开程序其他组件的角色，比如创建一条状态栏通知，或
//者启动一个服务等


//本地广播 只能在本应用传递,只能在本应用被接收 无法静态注册
// LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
//LocalBroadcastManager.getInstance(this).registerReceiver(intentfilter,receiver);
// LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
//1. 可以明确地知道正在发送的广播不会离开我们的程序，因此不需要担心机密数据泄漏的问题。
//2. 其他的程序无法将广播发送到我们程序的内部，因此不需要担心会有安全漏洞的隐患。
//3. 发送本地广播比起发送系统全局广播将会更加高效。
public class BootCompleteReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_LONG).show();
    }
}
