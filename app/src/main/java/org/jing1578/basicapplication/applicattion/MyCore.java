package org.jing1578.basicapplication.applicattion;

import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;


/**
 * @author Administrator
 */
public class MyCore {

    private static MyCore myCore;

    private MyCore() {
    }

    public static MyCore instance() {
        if (null == myCore) {
            myCore = new MyCore();
        }
        return myCore;
    }


    public static Gson gson = new Gson();

    public static final String NET_WEBSERVER = "";
    public static final String NET_TESTSERVER = "http://ziya.fengwoo.cn/";//120.76.155.46
//    public static final String NET_TESTSERVER = "http://120.76.155.46/";
//    public static final String NET_TESTSERVER = "http://192.168.1.102:8080/zhiya/";//192.168.1.198:8080/zhiya
//    public static final String NET_TESTSERVER = "http://120.24.85.182:8181/zhiya/";//192.168.1.198:8080/zhiya

    public static final String USERAPI = "userApiController.do?";
    public static final String LABELAPI = "labelApiController.do?";
    public static final String OPINIONAPI = "opinionApiController.do?";
    public static final String UPDATEDATAAPI = "userApiController.do?updateUser";
    public static final String PAYAPI = "payApiController.do?";

    //链接配置接口
    public static final String CONFIG = "opinionApiController.do?config";

    //登录接口
    public static final String USERLOGIN = "userApiController.do?login";

    //解除好友关系
    public static final String DELETEFRIEND= "userApiController.do?removeFriend";

    //登录结果,LOGIN_NET表示网络登录,LOGIN_HYPHE表示EM环信登录结果,LOGIN_UCOMS表示友盟微社区登陆结果,只有三个结果为true才能登录成功
    public static boolean LOGIN_NET = false;
    public static boolean LOGIN_HYPHE = false;
    public static boolean LOGIN_UCOMS = false;


//    //当前登录的用户信息
//    private User user;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public static final String LOCALFILE = Environment.
            getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "ZhiYa" + File.separator + "userPic" + File.separator;

    public static final String USERTEMPPIC = "userTemp.jpg";

    public static final String USERPIC = "user.jpg";

    public static final String IMAGE_CACHE_DIRECTORY = "images";

    //图片裁剪

    public static final int TAKEPICTURE = 0X1;

    public static final int SELECTPICTURE = 0X2;

    public static final int CROPPICTURE = 0X3;

}
