package org.jing1578.basicapplication.applicattion;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class SharedPre {
    public static Context context;
    public static SharedPre sharedPre;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor = null;

    public final static String FOURTEEN = "14";
    public final static String SIXTEEN = "16";
    public final static String EIGHTTEEN = "18";
    public final static String TWENTY = "20";

    public final static String VIB = "wr";

    public final static String LANGUAGE = "language";

    private String isLanguage = "ISLANGUAGE";

    public SharedPre() {
    }

    public static SharedPre getInstance(Context context) {
        SharedPre.context = context;
        if (sharedPre == null) {
            synchronized (SharedPre.class) {
                if (sharedPre == null) {
                    sharedPre = new SharedPre();
                    sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();


                }
            }
        }
        return sharedPre;
    }


    public void setUrl(String url, String student) {
        editor.putString("url", url).commit();
        editor.putString("student", student).commit();
    }

    public String getUrl() {
        return sharedPreferences.getString("url","");
    }
    public String getStudent() {
        return sharedPreferences.getString("student","");
    }

    public void setDelete(boolean is) {
        editor.putBoolean("delete", is).commit();
    }

    public boolean getDelete() {
        return sharedPreferences.getBoolean("delete", false);
    }

    public void setIsuser(boolean is) {
        editor.putBoolean("isuser", is).commit();
    }

    public boolean getIsuser() {
        return sharedPreferences.getBoolean("isuser", false);
    }

    public void setIsstranger(boolean is) {
        editor.putBoolean("isstranger", is).commit();
    }

    public boolean getIsstranger() {
        return sharedPreferences.getBoolean("isstranger", false);
    }


    public void setVoice(boolean is) {
        editor.putBoolean("Voice", is).commit();
    }

    public boolean getVoice() {
        return sharedPreferences.getBoolean("Voice", true);
    }

    public void setVibrator(boolean is) {
        editor.putBoolean("vibrator", is).commit();
    }

    public boolean getVibrator() {
        return sharedPreferences.getBoolean("vibrator", true);
    }


    public boolean getSize(String size) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(size, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("size", false);
    }

    public void setSize(String size, boolean issize) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(size, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("size", issize);
        editor.commit();
    }

    public boolean getOrientation() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("islogin", false);
    }

    public void setOrientation(boolean isLogin) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("islogin", isLogin);
        editor.commit();

    }

    public void setUserAndPassword(String user, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mUser", user);
        editor.putString("mPassword", password);
        editor.commit();
    }

    public String getUser() {
        return sharedPreferences.getString("mUser", "");
    }

    public String getPassword() {
        return sharedPreferences.getString("mPassword", "");
    }

    public void setLanguage(String values) {
        editor.putString(LANGUAGE, values).commit();
    }

    public String getLanguage() {
        return sharedPreferences.getString(LANGUAGE, "zh");
    }

    public void setIsLanguage(boolean isLanguages) {
        editor.putBoolean(isLanguage, isLanguages).commit();
    }

    public boolean getIsLanguage() {
        return sharedPreferences.getBoolean(isLanguage, false);
    }
}
