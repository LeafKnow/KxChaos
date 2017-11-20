package com.yq.mine.ui.act.register;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yq.base.ui.act.KitBaseAct;
import com.yq.common.config.RouteConfig;
import com.yq.mine.R;

/**
 * 用户注册
 */
@Route(path = RouteConfig.ActConfig.REGISTER_ACT)
public class RegisterAct<RegisterPresenter,RegisterModel> extends KitBaseAct {


    @Override
    protected void setTitleBar() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }
}
