package com.yq.action.ui.act.main;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yq.action.R;
import com.yq.action.ui.fmt.MainFtm;
import com.yq.base.ui.act.KitBaseAct;
import com.yq.common.config.RouteConfig;
//import com.yq.common.config.RouteConfig;

/**
 * 主 Act
 */
@Route(path = RouteConfig.ActConfig.MAIN_ACT)
public class MainAct extends KitBaseAct {

    @Override
    protected void setTitleBar() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (findFragment(MainFtm.class) == null) {
            loadRootFragment(R.id.fl_container, MainFtm.newInstance());
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


}
