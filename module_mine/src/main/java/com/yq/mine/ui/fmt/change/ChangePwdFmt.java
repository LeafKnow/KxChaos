package com.yq.mine.ui.fmt.change;


import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yq.base.ui.fmt.KitBaseFmt;
import com.yq.common.config.RouteConfig;
import com.yq.mine.R;
/**
 * 修改密码
 */
@Route(path = RouteConfig.FmtConfig.ChangePwd_FMT)
public class ChangePwdFmt<ChangePwdPresenter,ChangePwdModel> extends KitBaseFmt implements ChangePwdContract.View{


    public ChangePwdFmt() {
        // Required empty public constructor
    }

    @Override
    protected void setTitleBar() {

    }

    @Override
    public void initPresenter() {
     mPresenter.setVM(this,mModel,this._mActivity);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_change_pwd;
    }
}
