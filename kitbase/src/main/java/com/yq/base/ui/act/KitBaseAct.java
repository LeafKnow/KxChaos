package com.yq.base.ui.act;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.aries.ui.view.title.TitleBarView;
import com.yq.base.R;
import com.yq.base.ui.kit.UiCallback;
import com.yq.base.ui.kit.UiDelegate;
import com.yq.base.ui.kit.UiDelegateBase;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;


/**
 * 公用BaseActivity
 */
public abstract class KitBaseAct extends SwipeBackActivity implements UiCallback {
    protected Activity context;
    protected UiDelegate uiDelegate;
    protected TitleBarView titleBar;
    protected int type = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        initData(savedInstanceState);
        initTitle();
        setListener();

    }
    protected void initTitle() {
        titleBar = (TitleBarView) findViewById(R.id.titleBar);
        if (titleBar == null) {
            return;
        }
        type = titleBar.getStatusBarModeType();
        if (type <= 0) {//无法设置白底黑字
            titleBar.setStatusAlpha(102);//5.0 半透明模式alpha-102
        }
        titleBar.setTitleMainText(this.getClass().getSimpleName());
        setTitleBar();
        titleBar.setOnLeftTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setTitleLine(isShowLine());
    }

    public void setTitleLine(boolean enable) {
        titleBar.setDividerVisible(enable);
    }


    protected abstract void setTitleBar();

    protected boolean isShowLine() {
        return true;
    }

    protected UiDelegate getUiDelegate() {
        if (uiDelegate == null) {
            uiDelegate = UiDelegateBase.create(this);
        }
        return uiDelegate;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getUiDelegate().destory();
    }
}
