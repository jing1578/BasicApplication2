package org.jing1578.basicapplication.urlconfig;

import android.content.Context;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件的工具类
 * Created by Administrator on 2019/7/10 19:57.
 */
public class PropUtil {

    public static Properties loadAssetsProperties(Context context,String arg){
        Properties prop = null;
        prop = new Properties();
        //first load default properties
        try{
            prop.load(context.getAssets().open(arg));
        }catch (IOException e){
            e.printStackTrace();
        }
        return prop;
    }

    public static Properties loadResProperties(Context context,int id){
        Properties prop = null;
        prop = new Properties();
        try {
            prop.load(context.getResources().openRawResource(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
