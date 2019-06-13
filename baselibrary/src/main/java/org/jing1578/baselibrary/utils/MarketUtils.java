package org.jing1578.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jing1578 on 2016/7/28.
 * 应用市场工具类
 */
public class MarketUtils {

    private String[] marketArr=new String[]{"com.tencent.android.qqdownloader",
            "com.qihoo.appstore",
            "com.baidu.appsearch",
            "com.xiaomi.market",
            "com.huawei.appmarket",
            "com.wandoujia.phoenix2",
            "com.dragon.android.pandaspace",
           "com.hiapk.marketpho",
            "com.yingyonghui.market",
           "com.tencent.qqpimsecure",
            "com.mappn.gfan",
            "com.pp.assistant",
            "com.oppo.market",
            "cn.goapk.market",
            "zte.com.market",
            "com.yulong.android.coolmart",
            "com.lenovo.leos.appstore",
            "com.coolapk.market"};

    /**
     * 获取已安装应用商店的包名列表
     *
     * @param context
     * @return
     */
    public static ArrayList<String> queryInstalledMarketPkgs(Context context) {
        ArrayList<String> pkgs = new ArrayList<String>();
        if (context == null) {
            return pkgs;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        if (infos == null || infos.size() == 0) {
            return pkgs;
        }
        int size = infos.size();
        for (int i = 0; i < size; i++) {
            String pkgName = "";
            try {
                ActivityInfo activityInfo = infos.get(i).activityInfo;
                pkgName = activityInfo.packageName;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(pkgName)) {
                pkgs.add(pkgName);
            }

        }
        return pkgs;
    }

    /**
     * 过滤出已经安装的包名集合
     *
     * @param context
     * @param pkgs
     *            待过滤包名集合
     * @return 已安装的包名集合
     */
    public static ArrayList<String> filterInstalledPkgs(Context context,
                                                        ArrayList<String> pkgs) {
        ArrayList<String> empty = new ArrayList<String>();
        if (context == null || pkgs == null || pkgs.size() == 0) {
            return empty;
        }
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> installedPkgs = pm.getInstalledPackages(0);
        int li = installedPkgs.size();
        int lj = pkgs.size();
        for (int j = 0; j < lj; j++) {
            for (int i = 0; i < li; i++) {
                String installPkg = "";
                String checkPkg = pkgs.get(j);
                try {
                    installPkg = installedPkgs.get(i).applicationInfo.packageName;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(installPkg)) {
                    continue;
                }
                if (installPkg.equals(checkPkg)) {
                    empty.add(installPkg);
                    break;
                }

            }
        }
        return empty;
    }

    /**
     * 启动到app详情界面
     *
     * @param appPkg
     *            App的包名
     * @param marketPkg
     *            应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 启动到app详情界面
     *
     * @param appPkg
     *            App的包名
     * @param marketPkg
     *            应用商店包名 ,如果未安装则由系统跳转浏览器,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail2(Context context, String appPkg, String marketPkg, String url) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
               if (queryInstalledMarketPkgs(context).contains(marketPkg)){
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     context.startActivity(intent);
               }else{
                   if (!TextUtils.isEmpty(url)){
                       Uri urlUri = Uri.parse(url);
                       Intent urlIntent = new Intent(Intent.ACTION_VIEW,urlUri);
                       context.startActivity(urlIntent);
                   }
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 启动到app详情界面
     *
     * @param appPkg
     *            App的包名
     *            如果未安装则由系统跳转浏览器,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail3(Context context, String appPkg, String url) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                intent.setPackage(marketPkg);
                if (queryInstalledMarketPkgs(context).size()>0){
                    intent.setPackage(queryInstalledMarketPkgs(context).get(0));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else{
                    if (!TextUtils.isEmpty(url)){
                        Uri urlUri = Uri.parse(url);
                        Intent urlIntent = new Intent(Intent.ACTION_VIEW,urlUri);
                        context.startActivity(urlIntent);
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取非系统应用信息列表
     * 获取相关信息：
     *PackageInfo mPackageInfo;
     *获取包名： mPackageInfo.packageName
     *获取icon： mPackageInfo.getApplicationIcon(applicationInfo);
     *获取应用名： mPackageInfo.getApplicationLabel(applicationInfo);
     *获取使用权限：  mPackageInfo.getPackageInfo(packageName,PackageManager.GET_PERMISSIONS).requestedPermissions;
     */
    private void getAppList(Context context) {
        PackageManager pm = context.getPackageManager();
        // Return a List of all packages that are installed on the device.
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            // 判断系统/非系统应用
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) // 非系统应用
            {


            } else {
                // 系统应用　　　　　　　　
            }

        }
    }
}
