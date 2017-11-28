package com.yq.mine.ui.fmt.mine;


import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yq.base.ui.fmt.KitBaseFmt;
import com.yq.common.config.RouteConfig;
import com.yq.mine.R;
import com.yq.mine.ui.fmt.change.ChangePwdFmt;

/**
 * 个人中心
 */
@Route(path = RouteConfig.FmtConfig.MINE_FMT)
public class MineFmt extends KitBaseFmt {


    public MineFmt() {
        // Required empty public constructor
    }
    public static MineFmt newInstance() {
        MineFmt fragment = new MineFmt();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void setTitleBar() {
        titleBar.setTitleMainText("");
        titleBar.setVisibility(View.GONE);

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
        return R.layout.fragment_mine_fmt;
    }

}
