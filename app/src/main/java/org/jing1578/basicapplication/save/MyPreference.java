package org.jing1578.basicapplication.save;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferences工具类
 * SharedPreferences 文件都是存放在/data/data/<packagename>/shared_prefs/目录下的
 * 很多应用程序中的偏好设置功能  记住密码的功能
 */
public class MyPreference {

    private final String PREFERENCETAG = "basicapplication";
    private final SharedPreferences sharedPreferences;
    private Editor editor;

    public final String WIFI = "wifi";
    public final String MOBILE = "mobile";
    public final String NUM = "num";

    public MyPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCETAG, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


//    public void setNum(Context context, long l) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(NUM, Context.MODE_PRIVATE);
//        Editor editor = sharedPreferences.edit();
//        editor.putLong(NUM, l).commit();
//    }
//
//    public long getNum(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(NUM, Context.MODE_PRIVATE);
//        return sharedPreferences.getLong(NUM, 0);
//    }

//    public void setWifi(Context context, long l) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(WIFI, Context.MODE_PRIVATE);
//        Editor editor = sharedPreferences.edit();
//        editor.putLong(WIFI, l).commit();
//    }
//
//    public long getWifi(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(WIFI, Context.MODE_PRIVATE);
//        return sharedPreferences.getLong(WIFI, 0);
//    }

//    public void setMobile(Context context, long l) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(MOBILE, Context.MODE_PRIVATE);
//        Editor editor = sharedPreferences.edit();
//        editor.putLong(MOBILE, l).commit();
//    }
//
//    public long getMobile(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(MOBILE, Context.MODE_PRIVATE);
//        return sharedPreferences.getLong(MOBILE, 0);
//    }

    public void setClearMobile(String str, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public boolean saveIsFirstUse(boolean isfirstuse) {
        return editor.putBoolean("isfirstuse", isfirstuse).commit();
    }

    public boolean getIsFirstUse() {
        return sharedPreferences.getBoolean("isfirstuse", true);
    }


    public boolean saveIsAutoLogin(boolean isautologin) {
        return editor.putBoolean("isautologin", isautologin).commit();
    }

    public boolean getIsAutoLogin() {
        return sharedPreferences.getBoolean("isautologin", false);
    }


    public boolean saveLoginAccount(boolean b) {
        return editor.putBoolean("account", b).commit();
    }

    public boolean getLoginAccount() {
        return sharedPreferences.getBoolean("account", false);
    }

////记住密码功能onCreate ,检查是否要求记住密码,如果是,恢复密码
//    //登录按钮点击,检查是否要求记住密码,如果是,记住密码,否者清除
//    //密码保存结合一定的加密算法实现
//   boolean isRemember = pref.getBoolean("remember_password", false);
//    if (isRemember) {
//// 将账号和密码都设置到文本框中
//        String account = pref.getString("account", "");
//        String password = pref.getString("password", "");
//        accountEdit.setText(account);
//        passwordEdit.setText(password);
//        rememberPass.setChecked(true);
//    }

}
