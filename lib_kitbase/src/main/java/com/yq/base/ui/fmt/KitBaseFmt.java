package com.yq.base.ui.fmt;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aries.ui.view.title.TitleBarView;
import com.jph.takephoto.model.TResult;
import com.yq.base.R;
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
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 公用BaseFmt父类
 * 1 、相机/相册是否初始化  initPhoto
 * 2、eventbus是否进行注册 eventRegister
 *
 */
public abstract class KitBaseFmt<T extends BasePresenter, E extends BaseModel> extends SwipeBackFragment implements UiCallback,OpenPhoto.OnTakeListener, BaseView {
    public  T mPresenter;//UI控制层
    public E mModel;//数据操作层
    protected View rootView;
    protected LayoutInflater layoutInflater;
    protected Activity context;
    protected UiDelegate uiDelegate;
    protected TitleBarView titleBar;
    protected int type = 0;
    Unbinder unbinder;
    public KitBaseFmt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layoutInflater = inflater;
        if (initPhoto()) {
            getUiDelegate().onCreate(savedInstanceState);
            getUiDelegate().getOpenPhoto().setOnTakeListener(this);
        }

        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
            mPresenter = TUtil.getT(this, 0);
            mModel = TUtil.getT(this, 1);
            initPresenter();
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        unbinder = ButterKnife.bind(this,rootView);
        initTitle();
        initData(savedInstanceState);
        setListener();
        setSwipeBackEnable(false);
        if (eventRegister()){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (initPhoto()) {
            getUiDelegate().onSaveInstanceState(outState);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (initPhoto()) {
            getUiDelegate().getOpenPhoto().onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (initPhoto()) {
            getUiDelegate().getOpenPhoto().onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    protected void initTitle() {
        titleBar = _mActivity.findViewById(R.id.titleBar);
        if (titleBar == null) {
            return;
        }
        type = titleBar.getStatusBarModeType();
        if (type <= 0) {//无法设置白底黑字
            titleBar.setStatusAlpha(102);//5.0 半透明模式alpha-102
        }
        titleBar.setStatusAlpha(10);
        titleBar.setTitleMainText(this.getClass().getSimpleName());
        setTitleBar();
        titleBar.setOnLeftTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
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
            uiDelegate = UiDelegateBase.create(getContext());
        }
        return uiDelegate;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getUiDelegate().destory();
        if (null!=unbinder){
            unbinder.unbind();
        }
        if (null != mPresenter) {
            mPresenter.onDestroy();
        }
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
