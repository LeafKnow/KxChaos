package com.yq.mine.ui.fmt.mine;


import android.os.Bundle;
import android.view.View;

import com.yq.base.ui.fmt.KitBaseFmt;
import com.yq.mine.R;

/**
 * 个人中心
 */
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
