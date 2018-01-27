package com.example.lnnl.broswer;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        webView.loadUrl("http://192.168.1.1");

        webView.setWebViewClient(new WebViewClient() {
            // 当点击链接时,覆盖窗口
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);// 加载url
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);// 启用JS脚本
        // 这里可以有很多设置
        // webSettings.setSupportZoom(true); // 支持缩放
        // webSettings.setBuiltInZoomControls(true); // 启用内置缩放装置
        // web加载页面优先使用缓存加载
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 当打开页面时 显示进度条 页面打开完全时 隐藏进度条
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                setTitle("本页面已加载" + newProgress + "%");
                if (newProgress == 100) {
                    closeProgressBar();
                } else {
                    openProgressBar(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }
    // 打开进度条
    protected void openProgressBar(int x) {
        // TODO Auto-generated method stub
        setProgressBarIndeterminateVisibility(true);
        setProgress(x);
    }
    // 关闭进度条
    protected void closeProgressBar() {
        // TODO Auto-generated method stub
        setProgressBarIndeterminateVisibility(false);
    }
    // 改写物理按键 返回键的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                // 返回上一页面
                webView.goBack();
                return true;
            } else {
                // 退出程序
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}