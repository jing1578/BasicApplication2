package org.jing1578.basicapplication.urlconfig;

import org.jing1578.basicapplication.applicattion.MyApplication;

/**
 * 运行环境的管理切换类.
 * Created by Administrator on 2019/7/10 19:50.
 */
public class ApiManagerV2 {

    public static final String PROPERTY_PATH = "apiurl.properties";

    private static final String MEMBER_APP = "memberApp";

    private static final String DESIGN_APP = "designApp";

    private static final String VERSION_NAME = "versionName";

    private static final String LOGIN_URL = "loginUrl";

    public static final String LOGIN_PATH = getUrlPath(LOGIN_URL);

    public static final String MEMBER_PATH = getUrlPath(MEMBER_APP);

    public static final String DESIGN_PATH = getUrlPath(DESIGN_APP);

    private static String getUrlPath(String propKey){
        String urlPath = (String)PropUtil.loadAssetsProperties(MyApplication.getContext(),PROPERTY_PATH).getProperty(propKey);
        return urlPath;
    }
}
