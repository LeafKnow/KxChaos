package com.yq.base.ui.fmt;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aries.ui.view.title.TitleBarView;
import com.yq.base.R;
import com.yq.base.ui.kit.UiCallback;
import com.yq.base.ui.kit.UiDelegate;
import com.yq.base.ui.kit.UiDelegateBase;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 公用BaseFmt父类
 */
public abstract class KitBaseFmt extends SwipeBackFragment implements UiCallback {

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
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
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
        initData(savedInstanceState);
        unbinder = ButterKnife.bind(this,rootView);
        initTitle();
        setListener();
        setSwipeBackEnable(false);
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
    }

}
