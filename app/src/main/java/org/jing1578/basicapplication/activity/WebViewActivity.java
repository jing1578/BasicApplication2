package org.jing1578.basicapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.webkit.CookieManager;


import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import androidx.annotation.Nullable;


import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.jing1578.baselibrary.utils.LogUtil;
import org.jing1578.basicapplication.R;
import org.jing1578.basicapplication.applicattion.ActivitySupport;

import java.lang.reflect.Method;


/**
 * Created by sgll on 2019/7/3.
 */
public class WebViewActivity extends ActivitySupport implements View.OnClickListener {
    private String uuid = "";


    private RelativeLayout layout_head;
    private WebView webview;
    /**
     * webviewurl地址
     **/
    private String webViewUrl;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();

    }



    private void initView() {

//        webViewUrl = "https://mp.weixin.qq.com/s/7Imcnypps9_oWZp-FqbtIg";
        webViewUrl = "https://oa.dongyang.gov.cn:8443/risen-preview-adobe/public/risen/core/resrc/view/pdf_view.html.do?strMap.ext=pdf&strMap.remote_url=http%253A%252F%252F172.16.99.84%253A8088%252Foa%252Fpublic%252Fapi%252FloadDocument.do%253FCMD%253DDF%2526uuid%253DB85B518D278E4EA98B2D2EC8C2F4BA2A&markText=%E9%83%AD%E9%94%A6%E6%98%8E6788&strMap.upt=";
        layout_head = findViewById(R.id.layout_head);
        LinearLayout llback = findViewById(R.id.back);
        progressBar = findViewById(R.id.progressBar);
        webview = findViewById(R.id.webview);
        initWebView(webview);

        llback.setOnClickListener(this);
    }





    /**
     * 初始化WebView
     *
     * @param webview 网页
     */
    private void initWebView(WebView webview) {
        WebSettings webSettings = webview.getSettings();
        // 设置可以支持缩放
        webSettings.setSupportZoom(false);
        // 设置出现缩放工具
        webSettings.setBuiltInZoomControls(false);
        // 扩大比例的缩放
        webSettings.setUseWideViewPort(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAllowFileAccess(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAppCacheEnabled(false);
        //支持javascript
        webSettings.setJavaScriptEnabled(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webview.setWebChromeClient(new CustomWebViewChromeClient());
        webview.setWebViewClient(new CustomWebClient());
        webview.loadUrl(webViewUrl);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.clearCache(true);
    }

    private class CustomWebViewChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress >= 100) {
                //加载完网页进度条消失
                progressBar.setVisibility(View.GONE);
            } else {
                //开始加载网页时显示进度条
                progressBar.setVisibility(View.VISIBLE);
                //设置进度值
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    private class CustomWebClient extends WebViewClient {
        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
            //普通网页
			/*//停止loading使用
			dismissDialog();*/
//            if (MyApplication.CONFIGCODE == 10039) {
//                dismissDialog();
//            } else {
//                progressBar.setVisibility(View.GONE);
//            }
        }

        long startTime;
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            startTime = System.currentTimeMillis();
//            if (isNextDialogShow) {
//				/*//停止loading使用
//				showMsgDialog("正在加载数据,请稍候...");*/
//            }else{
//                isNextDialogShow = true;
//            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("url", url);
            CookieManager cm = CookieManager.getInstance();
            String cookies = cm.getCookie(url);
            LogUtil.d("cookies", "cookies=" + cookies);

            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            long endTime = System.currentTimeMillis();
            LogUtil.e("==============耗时:"+(endTime-startTime));
        }
    }




    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.back) {
            finish();
        }
    }



    /**
     * 获取URL的域名
     */
    private String getDomain(String url) {
        url = url.replace("http://", "").replace("https://", "");
        if (url.contains("/")) {
            url = url.substring(0, url.indexOf('/'));
        }
        return url;
    }

    /**
     * 让 activity transition 动画过程中可以正常渲染页面
     */
    private void setDrawDuringWindowsAnimating(View view) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M
                || Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // 1 android n以上  & android 4.1以下不存在此问题，无须处理
            return;
        }
        // 4.2不存在setDrawDuringWindowsAnimating，需要特殊处理
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            handleDispatchDoneAnimating(view);
            return;
        }
        try {
            // 4.3及以上，反射setDrawDuringWindowsAnimating来实现动画过程中渲染
            ViewParent rootParent = view.getRootView().getParent();
            Method method = rootParent.getClass()
                    .getDeclaredMethod("setDrawDuringWindowsAnimating", boolean.class);
            method.setAccessible(true);
            method.invoke(rootParent, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * android4.2可以反射handleDispatchDoneAnimating来解决
     */
    private void handleDispatchDoneAnimating(View paramView) {
        try {
            ViewParent localViewParent = paramView.getRootView().getParent();
            Class localClass = localViewParent.getClass();
            Method localMethod = localClass.getDeclaredMethod("handleDispatchDoneAnimating");
            localMethod.setAccessible(true);
            localMethod.invoke(localViewParent);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }


}
