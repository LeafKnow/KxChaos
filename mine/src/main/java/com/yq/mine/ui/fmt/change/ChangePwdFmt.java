package com.yq.mine.ui.fmt.change;


import android.os.Bundle;

import com.yq.base.ui.fmt.KitBaseFmt;
import com.yq.mine.R;

/**
 * 修改密码
 */
public class ChangePwdFmt<ChangePwdPresenter,ChangePwdModel> extends KitBaseFmt implements ChangePwdContract.View{


    public ChangePwdFmt() {
        // Required empty public constructor
    }

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
        return R.layout.fragment_change_pwd;
    }
}
