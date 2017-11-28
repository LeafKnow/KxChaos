package com.yq.base.ui.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.aries.ui.view.title.TitleBarView;
import com.jph.takephoto.model.TResult;
import com.yq.base.R;
import com.yq.base.common.AppManager;
import com.yq.base.common.camera.OpenPhoto;
import com.yq.base.ui.kit.UiCallback;
import com.yq.base.ui.kit.UiDelegate;
import com.yq.base.ui.kit.UiDelegateBase;
import com.yq.base.ui.mvp.BaseModel;
import com.yq.base.ui.mvp.BasePresenter;
import com.yq.base.ui.mvp.BaseView;
import com.yq.base.ui.mvp.util.TUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;


/**
 * 公用BaseActivity
 *  * 1 、相机/相册是否初始化  initPhoto
 * 2、eventbus是否进行注册 eventRegister
 */
public abstract class KitBaseAct<T extends BasePresenter, E extends BaseModel> extends SwipeBackActivity implements UiCallback, OpenPhoto.OnTakeListener, BaseView {
    //mvp
    public  T mPresenter;//UI控制层
    public E mModel;//数据操作层

    protected Activity context;
    protected UiDelegate uiDelegate;
    protected TitleBarView titleBar;
    protected int type = 0;
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            unbinder = ButterKnife.bind(this);
            mPresenter = TUtil.getT(this, 0);
            mModel = TUtil.getT(this, 1);
        }
        if (eventRegister()){
            EventBus.getDefault().register(this);
        }
        initData(savedInstanceState);

        initTitle();
        setListener();
        if (initPhoto()) {
            getUiDelegate().onCreate(savedInstanceState);
            getUiDelegate().getOpenPhoto().setOnTakeListener(this);
        }
        initPresenter();
        setSwipeBackEnable(false);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        if (initPhoto()) {
            getUiDelegate().onSaveInstanceState(outState);

        }
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (initPhoto()) {
            getUiDelegate().getOpenPhoto().onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (initPhoto()) {
            getUiDelegate().getOpenPhoto().onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
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
        if (null != unbinder) {
            unbinder.unbind();
        }
        if (null != mPresenter) {
            mPresenter.onDestroy();
        }
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void takeSuccess(TResult result, String path) {

    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    public boolean initPhoto() {
        return false;
    }

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    @Override
    public boolean eventRegister() {
        return false;
    }
}
