package com.yq.base.ui.fmt.web;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.just.library.AgentWeb;
import com.just.library.AgentWebSettings;
import com.just.library.ChromeClientCallbackManager;
import com.just.library.DefaultMsgConfig;
import com.just.library.DownLoadResultListener;
import com.just.library.FragmentKeyDown;
import com.just.library.IWebLayout;
import com.just.library.PermissionInterceptor;
import com.just.library.WebDefaultSettingsManager;
import com.yq.base.R;
import com.yq.base.ui.fmt.KitBaseFmt;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class KitBaseWebFmt extends KitBaseFmt implements FragmentKeyDown {

    protected AgentWeb mAgentWeb;
    public static final String URL_KEY = "url_key";
    public static final String TAG = KitBaseWebFmt.class.getSimpleName();

    @Override
    public void initData(Bundle savedInstanceState) {
        buildAgentWeb();
    }

    protected void buildAgentWeb() {
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(getAgentWebParent(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//传入AgentWeb的父控件
                .setIndicatorColorWithHeight(-1, 2)//设置进度条颜色与高度-1为默认值，2单位为dp
                .setAgentWebWebSettings(getSettings())//设置 AgentWebSettings
                .setReceivedTitleCallback(getReceivedTitleCallback())
                .setWebChromeClient(getWebChromeClient())
                .setWebViewClient(getWebViewClient())
                .setWebView(getWebView())
                .setPermissionInterceptor(getPermissionInterceptor())
                .setWebLayout(getWebLayout())
                .addDownLoadResultListener(getDownLoadResultListener())
                .openParallelDownload()//打开并行下载 , 默认串行下载
                .setNotifyIcon(R.mipmap.download)
                .createAgentWeb()//创建AgentWeb
                .ready()//设置 WebSettings
                .go(getUrl()); //WebView载入该url地址的页面并显示。
        DefaultMsgConfig.DownLoadMsgConfig mDownLoadMsgConfig = mAgentWeb.getDefaultMsgConfig().getDownLoadMsgConfig();
        //  mDownLoadMsgConfig.setCancel("放弃");  // 修改下载提示信息，这里可以语言切换

        mAgentWeb.getWebCreator().get().setOverScrollMode(WebView.OVER_SCROLL_NEVER);
    }
    public AgentWebSettings getSettings() {
        return WebDefaultSettingsManager.getInstance();
    }

    protected @Nullable
    DownLoadResultListener getDownLoadResultListener() {
        return null;
    }

    private @Nullable ChromeClientCallbackManager.ReceivedTitleCallback getReceivedTitleCallback() {
        return new ChromeClientCallbackManager.ReceivedTitleCallback() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                setTitle( view,title);
            }
        };
    }

    protected void setTitle(WebView view,String title){

    }

    protected
    @Nullable
    String getUrl() {
        return null;
    }

    public @Nullable
    AgentWebSettings getAgentWebSettings() {
        return WebDefaultSettingsManager.getInstance();
    }

    protected abstract @NonNull
    ViewGroup getAgentWebParent();

    protected @Nullable
    WebChromeClient getWebChromeClient() {
        return null;
    }

    protected @ColorInt
    int getIndicatorColor() {
        return -1;
    }

    protected @Nullable
    WebViewClient getWebViewClient() {
        return null;
    }

    protected @Nullable WebView getWebView() {
        return null;
    }

    protected  @Nullable
    IWebLayout getWebLayout() {
        return null;
    }
    protected PermissionInterceptor getPermissionInterceptor() {
        return null;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mAgentWeb.uploadFileResult(requestCode, resultCode, data); //2.0.0开始 废弃该api ，没有api代替 ,使用 ActionActivity 绕过该方法 ,降低使用门槛
    }


    //清除 WebView 缓存
    private void toCleanWebCache() {

        if (this.mAgentWeb != null) {

            //清理所有跟WebView相关的缓存 ，数据库， 历史记录 等。
            this.mAgentWeb.clearWebCache();
            Toast.makeText(getActivity(), "已清理缓存", Toast.LENGTH_SHORT).show();
            //清空所有 AgentWeb 硬盘缓存，包括 WebView 的缓存 , AgentWeb 下载的图片 ，视频 ，apk 等文件。
//            AgentWebConfig.clearDiskCache(this.getContext());
        }

    }
    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public boolean onFragmentKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event);
    }

    @Override
    public void onDestroyView() {
//        toCleanWebCache();
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
    }
}
