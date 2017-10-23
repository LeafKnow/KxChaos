package com.yq.base.ui.act;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        initData(savedInstanceState);
        setListener();
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
