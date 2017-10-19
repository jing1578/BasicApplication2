package org.jing1578.basicapplication.applicattion;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class MyPreference {

    private final String PREFERENCETAG = "zhiya";
    private final SharedPreferences sharedPreferences;
    private Editor editor;

    public final String WIFI = "wifi";
    public final String MOBILE = "mobile";
    public final String NUM = "num";

    public MyPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCETAG, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public void setNum(Context context, long l) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NUM, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putLong(NUM, l).commit();
    }

    public long getNum(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NUM, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(NUM, 0);
    }

    public void setWifi(Context context, long l) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(WIFI, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putLong(WIFI, l).commit();
    }

    public long getWifi(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(WIFI, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(WIFI, 0);
    }

    public void setMobile(Context context, long l) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MOBILE, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putLong(MOBILE, l).commit();
    }

    public long getMobile(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MOBILE, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(MOBILE, 0);
    }

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

    public boolean saveLoginData(String  str){ return editor.putString("logindata", str).commit(); }

    public  String getLoginData(){  return sharedPreferences.getString("logindata","");}

}
