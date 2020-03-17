package org.jing1578.basicapplication.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jing1578.basicapplication.R;
import org.jing1578.basicapplication.applicattion.ActivitySupport;


/**
 * Created by sgll on 2019/7/3.
 */
public class WebView2Activity extends ActivitySupport implements View.OnClickListener {
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
        setContentView(R.layout.activity_webview2);
        initView();

    }



    private void initView() {

        webViewUrl = "https://mp.weixin.qq.com/s/7Imcnypps9_oWZp-FqbtIg";

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
            Log.d("cookies", "cookies=" + cookies);

            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            long endTime = System.currentTimeMillis();
            Log.e("tag","==============耗时:"+(endTime-startTime));
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




}
