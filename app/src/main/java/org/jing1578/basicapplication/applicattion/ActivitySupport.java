package org.jing1578.basicapplication.applicattion;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.jing1578.basicapplication.R;
import org.jing1578.basicapplication.ui.activity.ActivityCollector;
import org.jing1578.basicapplication.utils.ScreenSwitchUtils;
import org.jing1578.basicapplication.utils.ScreenUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//import de.greenrobot.event.EventBus;

/**
 *  Android:theme="@android:style/Theme.Dialog"对话框风格activity
 */

public class ActivitySupport extends AppCompatActivity {

    public int width;
    public int height;

    public List<TextView> listText;

    public MyApplication myApplication;
    public MyCore myCore;
    public MyPreference myPreference;

    public ProgressDialog mDialog;
    private ScreenSwitchUtils instance;
    private View videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Log.d("ActivitySupport", getClass().getSimpleName());
        SwitchLanguage(SharedPre.getInstance(ActivitySupport.this).getLanguage());
        listText = new ArrayList<>();
//        EventBus.getDefault().register(this);
        initVar();
        getWindowSize();
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
        ActivityCollector.removeActivity(this);
    }

    private void initVar() {
        myApplication = (MyApplication) getApplication();
        myCore = myApplication.getMyCore();
        myPreference = myApplication.getMyPreference();
    }


    public void getWindowSize() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
    }

    public void dismissProgresDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    public void startProgressDialog(String title, String message) {
        mDialog = ProgressDialog.show(this, title, message);
    }

    public void SwitchLanguage(String language) {
        Resources resource = getResources();
        Configuration configuration = resource.getConfiguration();
        DisplayMetrics displayMetrics = resource.getDisplayMetrics();
        String en="en";
        String zh="zh";
        if (en.equals(language)) {
            configuration.locale = Locale.ENGLISH;
        } else if (zh.equals(language)) {
            configuration.locale = Locale.CHINESE;
        }
        resource.updateConfiguration(configuration, displayMetrics);
        // 保存设置语言的类型
        SharedPre.getInstance(ActivitySupport.this).setLanguage(language);

    }

    @Override
    protected void onResume() {
        //如果为true则为横屏
        if (SharedPre.getInstance(ActivitySupport.this).getOrientation() == true) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//            instance = ScreenSwitchUtils.init(this.getApplicationContext());
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }
        super.onResume();
        //判断当前横竖屏状态
//        Configuration configuration = getResources().getConfiguration();
//        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            SharedPre.getInstance(ActivitySupport.this).setOrientation(true);
//            Log.e("TAG", "横屏");
//        } else if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            SharedPre.getInstance(ActivitySupport.this).setOrientation(false);
//            Log.e("TAG", "竖屏");
//        }


    }

    public void setTextSize(TextView tv) {
        if (SharedPre.getInstance(ActivitySupport.this).getSize(SharedPre.FOURTEEN)) {
            if (tv != null) {
                tv.setText(getString(R.string.trumpet_s));
            }
            for (int i = 0; i < listText.size(); i++) {
                listText.get(i).setTextSize(14);
            }
            return;
        }
        if (SharedPre.getInstance(ActivitySupport.this).getSize(SharedPre.SIXTEEN)) {
            if (tv != null) {
                tv.setText(getString(R.string.standard_s));
            }
            for (int i = 0; i < listText.size(); i++) {
                listText.get(i).setTextSize(16);
            }
            return;
        }
        if (SharedPre.getInstance(ActivitySupport.this).getSize(SharedPre.EIGHTTEEN)) {
            if (tv != null) {
                tv.setText(getString(R.string.large_s));
            }
            for (int i = 0; i < listText.size(); i++) {
                listText.get(i).setTextSize(18);
            }
            return;
        }
        if (SharedPre.getInstance(ActivitySupport.this).getSize(SharedPre.TWENTY)) {
            if (tv != null) {
                tv.setText(getString(R.string.especiallybig_s));
            }
            for (int i = 0; i < listText.size(); i++) {
                listText.get(i).setTextSize(20);
            }
            return;
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (instance != null && instance.isPortrait()) {
            // 切换成竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dip2px(this, 160));
            videoView.setLayoutParams(params1);
        } else if (instance != null && !instance.isPortrait()) {
            // 切换成横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }
    }

    /**
     * 计算文件的大小
     */
    public String FormetFileSize(long fileS) {// 转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public void startActivitys(Class<?> context, int i) {
        Intent intent = new Intent(this, context);
        startActivity(intent);
        if (i == 1) {
            finish();
        }

    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }



}
