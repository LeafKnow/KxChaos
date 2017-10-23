package com.yq.base.ui.fmt;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yq.base.R;
import com.yq.base.ui.kit.UiCallback;
import com.yq.base.ui.kit.UiDelegate;
import com.yq.base.ui.kit.UiDelegateBase;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 公用BaseFmt父类
 */
public abstract class KitBaseFmt extends SupportFragment implements UiCallback {

    protected View rootView;
    protected LayoutInflater layoutInflater;
    protected Activity context;
    protected UiDelegate uiDelegate;

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
        setListener();
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
    }
}
