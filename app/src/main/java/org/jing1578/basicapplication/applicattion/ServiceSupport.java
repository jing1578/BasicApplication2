package org.jing1578.basicapplication.applicattion;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

//import de.greenrobot.event.EventBus;

public class ServiceSupport extends Service {

	public MyApplication myApplication;
	public MyCore myCore;
	public MyPreference myPreference;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		initVar();
//		EventBus.getDefault().register(this);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
//		EventBus.getDefault().unregister(this);
	}
	
	private void initVar(){
		myApplication = (MyApplication) getApplication();
		myCore = myApplication.getMyCore();
		myPreference = myApplication.getMyPreference();
	}

	
}
