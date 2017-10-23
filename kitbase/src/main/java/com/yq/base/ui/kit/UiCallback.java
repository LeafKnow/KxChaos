package com.yq.base.ui.kit;

import android.os.Bundle;

/**
 * Created by wanglei on 2016/12/1.
 */

public interface UiCallback {
    void initData(Bundle savedInstanceState);

    void setListener();

    int getLayoutId();

    boolean useEventBus();
}
